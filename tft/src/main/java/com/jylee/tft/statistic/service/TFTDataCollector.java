/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTDataCollector.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.domain.TFTAPIInformation;
import com.jylee.tft.statistic.domain.TFTMatch;
import com.jylee.tft.statistic.domain.TFTParticipant;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTMatchRepository;
import com.jylee.tft.statistic.repository.TFTParticipantRepository;
import com.jylee.tft.util.PondUtils;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTDataCollector.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
@RequiredArgsConstructor
public class TFTDataCollector{
	
	private int RETRIEVE_MAX = 10;
	private int RETRIEVE_COUNT = 50;
	private final TFTAPIInformation api;
	private final  SummonerRepository summonerRepository;	
	private final  TFTMatchRepository matchRepository;	
	private final  TFTParticipantRepository participantRepository;	
	private ObjectMapper mapper = new ObjectMapper();
		
	@Transactional
	public Summoner getSummoner(String summonerName){	
		String response = this.send(api.getSummonerUrl(summonerName), null);
		try {
			Map<String, Object> responseMap = mapper.readValue(response, Map.class);
			Summoner summoner = Summoner.builder()
					.id(PondUtils.getString(responseMap, "id"))		
					.accountId(PondUtils.getString(responseMap,"accountId"))
					.puuid(PondUtils.getString(responseMap,"puuid"))
					.name(PondUtils.getString(responseMap,"name"))
					.profileIconId(PondUtils.getLong(responseMap,"profileIconId"))
					.revisionDate(PondUtils.longToDateTime(PondUtils.getLong(responseMap,"revisionDate")))
					.summonerLevel(PondUtils.getLong(responseMap,"summonerLevel"))
					.type(AccountType.TFT)
					.build();	
			return summonerRepository.save(summoner);
		} catch (JsonProcessingException e) {
			
		}		
		return null;
	}

	private String send(String url, Map<String, Object> parameters){
		RestUtil restUtil = new RestUtil() {			
			@Override
			public HttpHeaders getHeader() {
				HttpHeaders header = new HttpHeaders();			
				header.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
				header.add("Accept", MediaType.APPLICATION_JSON_VALUE);
				header.add("X-Riot-Token", api.getKey());
				return header;
			}
		};
		return restUtil.send(url, parameters);
	}
	
	@Transactional
	public List<TFTMatch> update(String apiId){
		try {
			List<TFTMatch> matches = getMatches(apiId);
			for(TFTMatch tftmatch : matches) {
				fillDetail(tftmatch);
			}			
			return matchRepository.saveAll(matches);
		} catch (JsonProcessingException e) {
		}
		return null;		
	}
	
	private List<TFTMatch> getMatches(String apiId) throws JsonMappingException, JsonProcessingException {	
		List<TFTMatch> matches = new ArrayList<TFTMatch>();
		Set<String> inputMatchIds = inputMatchIds(apiId);
		
		for (String inputMatchId : inputMatchIds) {						
			TFTParticipant participant = new TFTParticipant();
			participant.setPuuid(apiId);
			
			TFTMatch TFTMatch = new TFTMatch();
			TFTMatch.setMatchId(inputMatchId);
			TFTMatch.addParticipants(participant);
			matches.add(TFTMatch);
		}
		
		return matches;
	}
	

	private Set<String> inputMatchIds(String apiId) throws JsonMappingException, JsonProcessingException {
		Set<String> matchIds = new HashSet<String>();
		int count = RETRIEVE_COUNT;		
		while(count <= RETRIEVE_MAX) {			
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("count",count);
			String response = this.send(api.getMatchIdsUrl(apiId), parameter);				
			String[] responseMatchIds = mapper.readValue(response, String[].class);						
			Page<TFTMatch> games = matchRepository.findRecent(apiId,PageRequest.of(0,count));
			for (String matchId : responseMatchIds) {
				if(games.get().parallel().anyMatch(m-> m.getMatchId().equals(matchId))) {
					return matchIds;
				}
				matchIds.add(matchId);
			}			
			if(responseMatchIds.length < count) break;
			count += RETRIEVE_COUNT;
		}
		
		
		return matchIds;
	}
	
	@Transactional
	private void fillDetail(TFTMatch TFTMatch) throws JsonMappingException, JsonProcessingException {		
		String response = this.send(api.getMatchesUrl(TFTMatch.getMatchId().toString()), null);	
		Map<String, Object> matchDetail = mapper.readValue(response, Map.class);
		Map<String, Object> metadata = (Map<String, Object>) matchDetail.get("metadata");
		Map<String, Object> info = (Map<String, Object>) matchDetail.get("info");
		
		for (TFTParticipant participant : TFTMatch.getParticipants()) {
			Map<String, Object> participantData = 
					this.parseParticipant((List<Map<String, Object>>) info.get("participants"), participant.getPuuid());
					
			participant.setGoldLeft(PondUtils.getLong(participantData, "gold_left"));
			participant.setLastRound(PondUtils.getLong(participantData, "lastRound"));
			participant.setLevel(PondUtils.getLong(participantData, "level"));
			participant.setPlacement(PondUtils.getLong(participantData, "placement"));
			participant.setPlayersEliminated(PondUtils.getLong(participantData, "player_eliminated"));
			participant.setTotalDamageToPlayers(PondUtils.getLong(participantData, "total_damage_to_palyers"));
			participant.setTimeEliminated(PondUtils.longToTime(PondUtils.getLong(participantData, "time_eliminates")));
			participant.setMatch(TFTMatch);
			
			participantRepository.save(participant);
		}
				
		TFTMatch.setDataVersion(PondUtils.getString(metadata,"data_version"));
		TFTMatch.setGameDatetime(PondUtils.longToDateTime(PondUtils.getLong(info,"game_datetime")));
		TFTMatch.setGameLength(PondUtils.longToTime(PondUtils.getLong(info,"game_length")));
		TFTMatch.setGameVersion(PondUtils.getString(info,"gamea_version"));
		TFTMatch.setQueueId(PondUtils.getLong(info,"queue_id"));
		TFTMatch.setTftSetNumber(PondUtils.getLong(info,"tft_set_number"));
					
	}
	
	private Map<String, Object> parseParticipant(List<Map<String, Object>> participants, String puuid){
		for (Map<String, Object> participant : participants) {
			if(puuid.equals(PondUtils.getString(participant,"puuid"))) {
				return participant;
			}
		}
		return null;
	}
	
	private String getSummoner(String summonerName, Map<String, Object> parameters) {
		return send(api.getSummonerUrl(summonerName), parameters);
	}
	
	private String getMatchIds(String puuid, Map<String, Object> parameters) {
		return send(api.getMatchIdsUrl(puuid), parameters);
	}
	
	private String getMatches(String matchId, Map<String, Object> parameters) {
		return send(api.getMatchesUrl(matchId), parameters);
	}
}
