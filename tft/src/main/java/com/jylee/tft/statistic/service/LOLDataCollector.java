/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : LOLDataCollector.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.LOLAPIInformation;
import com.jylee.tft.statistic.domain.LOLMatch;
import com.jylee.tft.statistic.domain.LOLParticipant;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.repository.LOLMatchRepository;
import com.jylee.tft.statistic.repository.LOLParticipantRepository;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.util.PondUtils;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : LOLDataCollector.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
public class LOLDataCollector{

	@Autowired
	private SummonerRepository summonerRepository;	
	@Autowired
	private LOLMatchRepository matchRepository;	
	@Autowired
	private LOLParticipantRepository participantRepository;	
	@Autowired
	private LOLAPIInformation api;
		
	private final ObjectMapper mapper = new ObjectMapper();

	@Transactional
	public Summoner getSummoner(String summonerName){	
		String response = this.send(api.getSummonerUrl(summonerName), null);
		Map<String, Object> responseMap;
		try {
			responseMap = mapper.readValue(response, Map.class);
			Summoner summoner = Summoner.builder()
					.id(PondUtils.getString(responseMap, "id"))		
					.accountId(PondUtils.getString(responseMap,"accountId"))
					.puuid(PondUtils.getString(responseMap,"puuid"))
					.name(PondUtils.getString(responseMap,"name"))
					.profileIconId(PondUtils.getLong(responseMap,"profileIconId"))
					.revisionDate(PondUtils.longToDateTime(PondUtils.getLong(responseMap,"revisionDate")))
					.summonerLevel(PondUtils.getLong(responseMap,"summonerLevel"))
					.type(AccountType.LOL)
					.build();
			Summoner savedSummoner = summonerRepository.save(summoner);			
			return savedSummoner;
		} catch (JsonProcessingException e) {
			
		}		
		return null;
	}
	
	@Transactional
	public List<LOLMatch> update(String apiId) {
		List<LOLMatch> matches = getMatches(apiId);
		
		for (LOLMatch lolMatch : matches) {
			fillDetail(lolMatch);
		}
		
		return matchRepository.saveAll(matches);
	}

	public List<LOLMatch> getMatches(String apiId) {
		String response = this.send(api.getMatchListsUrl(apiId), null);		
		Map<String, Object> responseMap;
		try {
			List<LOLMatch> matches = new ArrayList<LOLMatch>();
			responseMap = mapper.readValue(response, Map.class);
			List<Map<String, Object>> responseMatches = (List<Map<String, Object>>) responseMap.get("matches");
			
			Page<LOLMatch> games = matchRepository.findDescgameCreation(PageRequest.of(0, responseMatches.size()));			
						
			for (Map<String, Object> map : responseMatches) {
				Long gameId = PondUtils.getLong(map,"gameId");				
				if(games.get().parallel().anyMatch(m-> m.getGameId().equals(gameId))) break;

				LOLParticipant participant = new LOLParticipant();
				participant.setRole(PondUtils.getString(map,"role"));
				participant.setLane(PondUtils.getString(map,"lane"));		
				participant.setAccountId(apiId);
				
				LOLMatch lolMatch = new LOLMatch();
				lolMatch.setPlatformId(PondUtils.getString(map,"platformId"));
				lolMatch.setGameId(gameId);
				lolMatch.setQueue(PondUtils.getLong(map,"queue"));
				lolMatch.setSeason(PondUtils.getLong(map,"season"));
				lolMatch.addParticipants(participant);
				
				matches.add(lolMatch);
			}
			
			return matches;
		} catch (JsonProcessingException e) {
			
		}
		
		return null;
	}
	
	private void fillDetail(LOLMatch lolMatch) {		
		String response = this.send(api.getMatchesUrl(lolMatch.getGameId().toString()), null);	
		try {
			Map<String, Object> matchDetail = mapper.readValue(response, Map.class);
			
			for (LOLParticipant participant : lolMatch.getParticipants()) {
				int participantId = 
						this.findParticipantId((List<Map<String, Object>>) matchDetail.get("participantIdentities"), participant.getAccountId());
				Map<String, Object> participantData = 
						this.getParticipant((List<Map<String, Object>>) matchDetail.get("participants"), participantId);
				String win = 
						this.getWin((List<Map<String, Object>>) matchDetail.get("teams"), PondUtils.getInteger(participantData,"teamId"));
				Map<String, Object> stats = (Map<String, Object>) participantData.get("stats");				
				participant.setKills(PondUtils.getLong(stats,"kills"));
				participant.setDeaths(PondUtils.getLong(stats,"assists"));
				participant.setAssists(PondUtils.getLong(stats,"deaths"));
				participant.setSpell1(PondUtils.getLong(participantData,"spell1Id"));
				participant.setSpell2(PondUtils.getLong(participantData,"spell2Id"));
				participant.setWin(win);
			}
					
			lolMatch.setGameMode(PondUtils.getString(matchDetail,"gameMode"));
			lolMatch.setGameType(PondUtils.getString(matchDetail,"gameType"));
			lolMatch.setGameCreation(PondUtils.longToDateTime(PondUtils.getLong(matchDetail,"gameCreation")));		
			lolMatch.setGameDuration(PondUtils.intToTime(PondUtils.getInteger(matchDetail,"gameDuration")));			
			lolMatch.setGameVersion(PondUtils.getString(matchDetail,"gameVersion"));
			lolMatch.setMapId(PondUtils.getLong(matchDetail,"mapId"));
			
		} catch (JsonProcessingException e) {
			
		}	
		
	}
	
	private String getWin(List<Map<String, Object>> teams, int teamId) {
		for (Map<String, Object> team : teams) {
			if(teamId == PondUtils.getInteger(team,"teamId")){
				return PondUtils.getString(team,"win");
			}
		}
		return "";
	}
	
	private Map<String, Object> getParticipant(List<Map<String, Object>> participants, int participantId) {
		for (Map<String, Object> participant : participants) {
			if(participantId == PondUtils.getInteger(participant,"participantId")){
				return participant;
			}
		}
		return null;
	}

	private int findParticipantId(List<Map<String, Object>> participantIdentities, String accountId) {
		for (Map<String, Object> user : participantIdentities) {
			Map<String, Object> player = (Map<String, Object>) user.get("player");
			if(accountId.equals(PondUtils.getString(player,"accountId"))) {
				return PondUtils.getInteger(user,"participantId");				
			}
		}
		
		return 0;
	}

	
	public String getParticipants(String apiId, Map<String, Object> parameters) {
		return this.send(api.getMatchesUrl(apiId), parameters);		
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

}
