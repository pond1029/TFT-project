/**
  * @Package : com.jylee.tft.service
  * @FileName : DataManager.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.Participants;
import com.jylee.tft.util.PondUtil;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : DataManager.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
@Service
public class DataManager {


	@Autowired
	ParticipantsService participants;

	@Autowired
	MatchInfoService matchInfoService;
	
	public List<Object> insertMatchInfo(Map matchData) {
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
					.matchInfo(matchInfo)
					.build();

			participants.setparticipants(participant);

		}
		return null;
	}
	
}
