/**
  * @Package : com.jylee.tft.service
  * @FileName : Converter.java
  * @Date : 2020. 10. 30. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.Participants;
import com.jylee.tft.util.PondUtil;

/**
  * @Package : com.jylee.tft.service
  * @FileName : Converter.java
  * @Date : 2020. 10. 30. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 변환
  */

@Service
public class Converter {

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
