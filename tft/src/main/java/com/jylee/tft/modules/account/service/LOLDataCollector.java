/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : LOLDataCollector.java
  * @Date : 2021. 1. 8. 
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
import com.jylee.tft.modules.account.domain.LOLAPIInformation;
import com.jylee.tft.modules.account.domain.LOLMatch;
import com.jylee.tft.modules.account.domain.LOLParticipant;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.util.PondUtils;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : LOLDataCollector.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
@RequiredArgsConstructor
public class LOLDataCollector{
	
	private static final int RETRIEVE_COUNT = 5;
	private final LOLAPIInformation api;		
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	public Summoner retrieveSummoner(String summonerName) throws JsonProcessingException{	
		String response = this.send(api.getSummonerUrl(summonerName), null);
		
		Map<String, Object> responseMap =  mapper.readValue(response, Map.class);
		
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
		
		return summoner;
	}
		
	public List<LOLMatch> retrieveMatches(String accountId) throws JsonMappingException, JsonProcessingException {
		List<LOLMatch> matchList = new ArrayList<LOLMatch>();

		Map<String, Object> parameter = new HashMap<String, Object>();		
		parameter.put("beginIndex", 0);
		parameter.put("endIndex", RETRIEVE_COUNT);
		
		String response = this.send(api.getMatchListsUrl(accountId), parameter);	
		
		Map<String, Object> responseMap = mapper.readValue(response, Map.class);
		List<Map<String, Object>> responseMatches = (List<Map<String, Object>>) responseMap.get("matches");

		for (Map<String, Object> map : responseMatches) {
			LOLMatch match = new LOLMatch();
			match.setGameId(PondUtils.getLong(map,"gameId"));			
			match.setSeason(PondUtils.getLong(map,"season"));
			
			matchList.add(match);
		}
		
		return matchList;
	}
	
	public LOLMatch retrieveMatchDetail(LOLMatch match, String accountId) throws JsonMappingException, JsonProcessingException{			
		String response = this.send(api.getMatchesUrl(match.getGameId().toString()), null);

		Map<String, Object> matchDetail = mapper.readValue(response, Map.class);
		List<Map<String, Object>> responseMatches = (List<Map<String, Object>>) matchDetail.get("matches");
		
		int participantId = 
				this.getParticipantIdByAccountId((List<Map<String, Object>>) matchDetail.get("participantIdentities"), accountId);
		
		Map<String, Object> participantData = 
				this.getParticipantByParticipantId((List<Map<String, Object>>) matchDetail.get("participants"), participantId);		
		String win = 
				this.getWinByTeamId((List<Map<String, Object>>) matchDetail.get("teams"), PondUtils.getInteger(participantData,"teamId"));
		
		Map<String, Object> stats = (Map<String, Object>) participantData.get("stats");				
		
		Map<String, Object> timeLine = (Map<String, Object>) participantData.get("stats");
		
		LOLParticipant participant = LOLParticipant.builder()
				.accountId(accountId)
				.role(PondUtils.getString(timeLine,"role"))
				.lane(PondUtils.getString(timeLine,"lane"))
				.championId(PondUtils.getLong(participantData,"championId"))
				.kills(PondUtils.getLong(stats,"kills"))
				.deaths(PondUtils.getLong(stats,"assists"))
				.assists(PondUtils.getLong(stats,"deaths"))
				.spell1(PondUtils.getLong(participantData,"spell1Id"))
				.spell2(PondUtils.getLong(participantData,"spell2Id"))
				.win(win)
				.build();
						
		match.setGameMode(PondUtils.getString(matchDetail,"gameMode"));
		match.setGameType(PondUtils.getString(matchDetail,"gameType"));
		match.setGameCreation(PondUtils.longToDateTime(PondUtils.getLong(matchDetail,"gameCreation")));		
		match.setGameDuration(PondUtils.longToTime(PondUtils.getLong(matchDetail,"gameDuration")));			
		match.setGameVersion(PondUtils.getString(matchDetail,"gameVersion"));
		match.setMapId(PondUtils.getLong(matchDetail,"mapId"));
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

	private String getWinByTeamId(List<Map<String, Object>> teams, int teamId) {
		for (Map<String, Object> team : teams) {
			if(teamId == PondUtils.getInteger(team,"teamId")){
				return PondUtils.getString(team,"win");
			}
		}
		return "";
	}
	
	private int getParticipantIdByAccountId(List<Map<String, Object>> participantIdentities, String accountId) {
		for (Map<String, Object> user : participantIdentities) {
			Map<String, Object> player = (Map<String, Object>) user.get("player");
			if(accountId.equals(PondUtils.getString(player,"accountId"))) {
				return PondUtils.getInteger(user,"participantId");				
			}
		}
		
		return 0;
	}

	private Map<String, Object> getParticipantByParticipantId(List<Map<String, Object>> participants, int participantId) {
		for (Map<String, Object> participant : participants) {
			if(participantId == PondUtils.getInteger(participant,"participantId")){
				return participant;
			}
		}
		return null;
	}

}
