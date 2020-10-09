package com.jylee.tft.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.MatchesAndPuuids;
import com.jylee.tft.dao.Participants;
import com.jylee.tft.util.PondUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TftApiService {

	private static final String serviceUrl = "https://asia.api.riotgames.com";
	private static final String apiKey = "RGAPI-2aa891a6-f261-4684-ba62-f48e4078603f";
	@Autowired
	ApiRetrieveService apiRetrieveService;

	@Autowired
	MatchesAndPuuidsService matchesAndPuuidsService;

	@Autowired
	MatchInfoService matchInfoService;

	@Autowired
	ParticipantsService participants;

	public Map<String,Object> getPlayInfo(String puuid) {
		Map<String, Object> playInfo = new HashMap<String, Object>();
		
		List<Participants> participantLists = participants.getParticipants(puuid);
		playInfo.put("participantLists",participantLists);
		
		playInfo.put("playTimes",null);
		playInfo.put("totalPlayTime", null);
		
		return playInfo;
	}

	/**
	 * 
	  * @Method Name : update
	  * @Date : 2020. 10. 4.
	  * @Author : "LeeJaeYeon"
	  * @Version : 
	  * @Information : 데이터를 비교하여  갱신한다.
	  * @param puuid
	 */
	public void update(String puuid) {

		List<String> matchIdList = retrieveMatchId(puuid);

		if (!matchIdList.isEmpty()) {
			List<MatchesAndPuuids> matchList = matchesAndPuuidsService.getListMatchesAndPuuids(puuid);
			
			List<String> dbList = matchList.stream()
					.map(MatchesAndPuuids::getMatchId)
					.collect(Collectors.toCollection(ArrayList::new));
			
			List<String> insertList = new ArrayList<String>();
			
			for(int i = 0; i < matchIdList.size(); i++) {
				if(!dbList.contains(matchIdList.get(i))) {
					insertList.add(matchIdList.get(i));
				}
			}
			
			
			for (String matchId : insertList) {
				Map<String, Object> map = retrieveMatchInfo(matchId);
				matchesAndPuuidsService.setMatchesAndPuuids(matchId, puuid);
				insertMatchInfo(map);
			}
			
		}
	}

	private List<Object> insertMatchInfo(Map matchData) {
		Map<String, Object> metaDataMap = (Map<String, Object>) matchData.get("metadata");
		Map<String, Object> infoMap = (Map<String, Object>) matchData.get("info");
		List participantLists = (List) infoMap.get("participants");
		PondUtil pondUtil = new PondUtil();

		PondUtil pu = new PondUtil();
		
		MatchInfo matchInfo = MatchInfo.builder()
				.gameDatetime(((Double) infoMap.get("game_datetime")).longValue())
				.gameLength(((Double) infoMap.get("game_length")).longValue())
				.gameVariation((String) infoMap.get("game_variation"))
				.gameVersion((String) infoMap.get("game_version"))
				.gameDate(pondUtil.getUnixToString(((Double) infoMap.get("game_datetime")).longValue()))
				.matchId((String)metaDataMap.get("match_id"))
				.build();
		
		matchInfoService.setMatchInfo(matchInfo);

		Iterator i = participantLists.iterator();
		while (i.hasNext()) {
			Map<String, Object> m = (Map<String, Object>) i.next();
			Participants participant = Participants.builder().goldLeft(((Double) m.get("gold_left")).longValue())
					.lastRound(((Double) m.get("last_round")).longValue()).level(((Double) m.get("level")).longValue())
					.placement(((Double) m.get("placement")).longValue())
					.playersEliminated(((Double) m.get("players_eliminated")).longValue())
					.timeEliminated(((Double) m.get("time_eliminated")).longValue())
					.totalDamageToPlayers(((Double) m.get("total_damage_to_players")).longValue())
					.playersGameTime(pondUtil.getUnixToString(((Double) m.get("time_eliminated")).longValue(), "HH:mm:ss"))
					.puuid((String) m.get("puuid"))
					.matchId((String)metaDataMap.get("match_id"))
					.build();

			participants.setparticipants(participant);

		}
		return null;
	}

	private List<Object> getPlayTimes(String puuid) {
		Gson gson = new Gson();
		// 기존 데이터 조회
		List listMatchesAndPuuids = matchesAndPuuidsService.getListMatchesAndPuuids(puuid);

		// api 조회
		Map<String, Object> parameters = new HashMap();
		String apiUrl = "/tft/match/v1/matches/by-puuid/" + puuid + "/ids";
		apiUrl += "?count=100";

		ResponseEntity<String> userRsp = apiRetrieveService.sendRest(serviceUrl, apiUrl, parameters);

		if (userRsp != null) {
			String[] userMap = gson.fromJson(userRsp.getBody(), String[].class);
			for (String matchId : userMap) {
				MatchesAndPuuids matchesAndPuuids = new MatchesAndPuuids();
				matchesAndPuuids.setMatchId(matchId);
				matchesAndPuuids.setPuuid(puuid);
				if (!listMatchesAndPuuids.equals(matchesAndPuuids)) {
					// 추가
					// updateMatchInfo(matchId);
				}

			}
		}

		// 비교

		// update

		// 조회

		return null;
	}
	
	private List<String> retrieveMatchId(String puuid) {
		Gson gson = new Gson();
		Map<String, Object> parameters = new HashMap();
		String apiUrl = "/tft/match/v1/matches/by-puuid/" + puuid + "/ids?count=9999";
		ResponseEntity<String> userRsp = apiRetrieveService.sendRest(serviceUrl, apiUrl, parameters);
		String[] body = gson.fromJson(userRsp.getBody(), String[].class);
		
		return Arrays.asList(body);
	}
	
	private Map<String, Object> retrieveMatchInfo(String matchId) {
		Gson gson = new Gson();
		Map<String, Object> parameters = new HashMap();
		String apiUrl = "/tft/match/v1/matches/" + matchId;
		ResponseEntity<String> response = apiRetrieveService.sendRest(serviceUrl, apiUrl, parameters);		
		Map<String, Object> bodyMap = gson.fromJson(response.getBody(), Map.class);
		return bodyMap;
	}

}
