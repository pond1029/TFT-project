/**
  * @Package : com.jylee.tft.service.retrieve
  * @FileName : MatchInfoRetrieve.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service.retrieve;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.jylee.tft.dao.ApiInformation;
import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.Participants;
import com.jylee.tft.util.PondUtil;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service.retrieve
  * @FileName : MatchInfoRetrieve.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
public class MatchInfoRetrieve implements Retrieve{

	private ApiInformation apiInformation = new ApiInformation();
	private String apiUrl = "/tft/match/v1/matches/";
	private String matchId;
	
	/**
	 * @param matchId
	 */
	public MatchInfoRetrieve(String matchId) {
		super();
		this.matchId = matchId;
		this.apiUrl = this.apiUrl + matchId;
	}

	@Override
	public MatchInfo retrieve(Map<String, Object> parameters) {
		Gson gson = new Gson();
		String response = send(apiUrl, parameters);		
		Map<String, Object> bodyMap = gson.fromJson(response, Map.class);
		
		MatchInfo matchInfo = convertToMatchInfo(bodyMap);
		List<Participants> participants = convertToParticipantsLists(bodyMap);
		
		matchInfo.setParticipantLists(participants);
		
		return matchInfo;
	}
	
	public String send(String apiUrl, Map<String, Object> parameters) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeader();						
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);
			String url = getUrl(parameters);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request,
					String.class);			
			return response.getBody();
		} catch (Exception e) {
			log.error("error : {}", e.getMessage());
		}
		return null;
	}
	
	private HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();			
		headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("X-Riot-Token", apiInformation.getKey());
		return headers;
	}
	
	private String getUrl(Map<String, Object> parameters) {
		String resultUrl = apiInformation.getUrl() + apiUrl;
		String params = "";
		
		Set<String> keySet = parameters.keySet();			
		for(String key : keySet) {
			String param = "&" + key + "=" + parameters.get(key).toString();
		}
		if(!params.equals("")) {
			params = "/" + params.replace("&", "?");			
		}
		return resultUrl + params;
	}
	
	public MatchInfo convertToMatchInfo(Map map) {
		Map<String, Object> metaDataMap = (Map<String, Object>) map.get("metadata");
		Map<String, Object> infoMap = (Map<String, Object>) map.get("info");
		List participantLists = (List) infoMap.get("participants");
		
		MatchInfo matchInfo = MatchInfo.builder()
				.gameDatetime(((Double) infoMap.get("game_datetime")).longValue())
				.gameLength(((Double) infoMap.get("game_length")).longValue())
				.gameVariation((String) infoMap.get("game_variation"))
				.gameVersion((String) infoMap.get("game_version"))
				.gameDate(getUnixToString(((Double) infoMap.get("game_datetime")).longValue()))
				.matchId((String)metaDataMap.get("match_id"))
				.build();
		
		return matchInfo;
	}

	public List<Participants> convertToParticipantsLists(Map map){
		Map<String, Object> infoMap = (Map<String, Object>) map.get("info");
		PondUtil pondUtil = new PondUtil();

		List<Participants> participantLists = (List) infoMap.get("participants");
		Iterator i = participantLists.iterator();
		while (i.hasNext()) {
			Map<String, Object> m = (Map<String, Object>) i.next();
			Participants participants = convertToParticipants(m);
			participantLists.add(participants);
		}
		
		return participantLists;
	}
	
	public Participants convertToParticipants(Map map) {
		Participants participant = Participants.builder()
				.goldLeft(((Double) map.get("gold_left")).longValue())
				.lastRound(((Double) map.get("last_round")).longValue())
				.level(((Double) map.get("level")).longValue())
				.placement(((Double) map.get("placement")).longValue())
				.playersEliminated(((Double) map.get("players_eliminated")).longValue())
				.timeEliminated(((Double) map.get("time_eliminated")).longValue())
				.totalDamageToPlayers(((Double) map.get("total_damage_to_players")).longValue())
				.playersGameTime(getUnixToString(((Double) map.get("time_eliminated")).longValue(), "HH:mm:ss"))
				.puuid((String) map.get("puuid"))
				.build();
		return participant;
	}
	

	private String getUnixToString(Long timestamp, String format) {
		Date date = new Date(timestamp*1000L);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT")); 
		String stringDate = sdf.format(date);
		return stringDate;		
	}
	

	private String getUnixToString(Long timestamp) {
		Date date = new Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9")); 
		String stringDate = sdf.format(date);
		return stringDate;		
	}
}
