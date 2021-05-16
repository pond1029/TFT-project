/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTDataCollector.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jylee.tft.modules.account.domain.AccountType;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.domain.TFTAPIInformation;
import com.jylee.tft.modules.account.domain.TFTMatch;
import com.jylee.tft.modules.account.domain.TFTParticipant;
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
	
	private static final int RETRIEVE_COUNT = 5;
	private final ObjectMapper mapper = new ObjectMapper();
	private final TFTAPIInformation api;
		
	public Summoner retrieveSummoner(String summonerName) throws JsonProcessingException{	
		String response = this.send(api.getSummonerUrl(summonerName), null);
		
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
		
		return summoner;
	}

	
	public List<TFTMatch> retrieveMatches(String puuid) throws JsonMappingException, JsonProcessingException {
		List<TFTMatch> matchList = new ArrayList<TFTMatch>();

		Map<String, Object> parameter = new HashMap<String, Object>();		
		int count = RETRIEVE_COUNT;					
		parameter.put("count",count);
		
		String response = this.send(api.getMatchIdsUrl(puuid), parameter);		
		String[] responseMatchIds = mapper.readValue(response, String[].class);						
		
		for(String matchId : responseMatchIds) {
			TFTMatch match = new TFTMatch();
			match.setMatchId(matchId);
			matchList.add(match);
		}
		
		return matchList;
	}
	
	public TFTMatch retrieveMatchDetail(TFTMatch match, String puuid) throws JsonMappingException, JsonProcessingException {
		String response = this.send(api.getMatchesUrl(match.getMatchId()), null);	
				
		Map<String, Object> matchDetail = mapper.readValue(response, Map.class);
		Map<String, Object> metadata = (Map<String, Object>) matchDetail.get("metadata");
		Map<String, Object> info = (Map<String, Object>) matchDetail.get("info");
		Map<String, Object> participantData = 
				this.getParticipantByPuuid((List<Map<String, Object>>) info.get("participants"), puuid);

		TFTParticipant participant = TFTParticipant.builder()
				.goldLeft(PondUtils.getLong(participantData, "gold_left"))
				.lastRound(PondUtils.getLong(participantData, "lastRound"))
				.level(PondUtils.getLong(participantData, "level"))
				.placement(PondUtils.getLong(participantData, "placement"))
				.playersEliminated(PondUtils.getLong(participantData, "player_eliminated"))
				.totalDamageToPlayers(PondUtils.getLong(participantData, "total_damage_to_palyers"))
				.timeEliminated(PondUtils.longToTime(PondUtils.getLong(participantData, "time_eliminates")))
				.build();
		
		match.setDataVersion(PondUtils.getString(metadata,"data_version"));
		match.setGameDatetime(PondUtils.longToDateTime(PondUtils.getLong(info,"game_datetime")));
		match.setGameLength(PondUtils.longToTime(PondUtils.getLong(info,"game_length")));
		match.setGameVersion(PondUtils.getString(info,"gamea_version"));
		match.setQueueId(PondUtils.getLong(info,"queue_id"));
		match.setTftSetNumber(PondUtils.getLong(info,"tft_set_number"));		
		match.addParticipants(participant);		
			
		return match;					
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
	
	private Map<String, Object> getParticipantByPuuid(List<Map<String, Object>> participants, String puuid){
		for (Map<String, Object> participant : participants) {
			if(puuid.equals(PondUtils.getString(participant,"puuid"))) {
				return participant;
			}
		}
		return null;
	}
	
}
