/**
  * @Package : com.jylee.tft.service
  * @FileName : Charting.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.Participants;
import com.jylee.tft.util.PondUtil;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : Charting.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
@Service
public class Charting {

	@Autowired
	ParticipantsService participants;
	
	public Map<String,Object> getPlayInfo(String puuid) {
		Map<String, Object> playInfo = new HashMap<String, Object>();
		PondUtil pu = new PondUtil();
		
		List<Participants> participantLists = participants.getParticipants(puuid);
		Map<String, Object> playTimes = new HashMap<String, Object>();
		Long totalPlayTime = new Long(0);
		List playTimeLists = new ArrayList();
		
		for(int i = 0; i < participantLists.size(); i++) {
			String gameDate = participantLists.get(i).getMatchInfo().getGameDate();
			Long playTime = participantLists.get(i).getTimeEliminated();
			playTimes.put(gameDate,(Long)playTimes.getOrDefault(gameDate, 0L) + playTime);
			totalPlayTime += playTime;
		}
		
		for(String key : playTimes.keySet()) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("date", key);
			m.put("seconds", playTimes.get(key));
			playTimeLists.add(m);
		}
		
		playTimeLists.sort(new Comparator<Map>() {
			@Override
			public int compare(Map m1, Map m2) {
				return m1.get("date").toString().compareTo(m2.get("date").toString());
			}
		});
		
		String tt = pu.getUnixToString(totalPlayTime, "yyyy년MM개월dd일HH시간mm분ss초");
		playInfo.put("playTimeLists",playTimeLists);
		playInfo.put("totalPlayTime", totalPlayTime);
		
		return playInfo;
	}
	
}
