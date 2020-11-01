/**
  * @Package : com.jylee.tft.service
  * @FileName : Statistic.java
  * @Date : 2020. 10. 23. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.Participants;
import com.jylee.tft.dao.PlayStatistics;
import com.jylee.tft.dao.PlayTime;
import com.jylee.tft.util.PondUtil;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : Statistic.java
  * @Date : 2020. 10. 23. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 분석
  */

@Slf4j
@Service
public class Statistic {

	@Autowired
	ParticipantsService participants;
		
	public PlayStatistics getPlayTimeStatistics(int year, int month, String puuid) {
		
		PlayStatistics playStatistics = new PlayStatistics(year, month);

		List<Participants> participantLists = participants.getParticipants(puuid);
		
		for(Participants participants : participantLists) {
			
			String gameDate = participants.getGameDate();
			Long timeEliminated = participants.getTimeEliminated();
			PlayTime playTime = new PlayTime(gameDate, timeEliminated);
			
			playStatistics.addPlayTime(playTime);
		}
		playStatistics.sort();
		
		return playStatistics;
	}

}
