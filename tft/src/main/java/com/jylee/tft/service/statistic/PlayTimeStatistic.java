/**
  * @Package : com.jylee.tft.service
  * @FileName : Statistic.java
  * @Date : 2020. 10. 23. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service.statistic;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.Participants;
import com.jylee.tft.dao.Period;
import com.jylee.tft.service.ParticipantsService;
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
public class PlayTimeStatistic implements Statistics{

	private Period period;
	private String id;
	private List<PlayTime> playTimeLists = new ArrayList<>();
	
	/**
	 * @param period
	 * @param id
	 */
	public PlayTimeStatistic(Period period, String id) {
		this.period = period;
		this.id = id;		
	}


	@Override
	public Object statistics() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public PlayStatistics getPlayTimeStatistics(int year, int month, String puuid) {
//		
//		PlayStatistics playStatistics = new PlayStatistics(year, month);
//
//		List<Participants> participantLists = participants.getParticipants(puuid);
//		
//		for(Participants participants : participantLists) {
//			
//			String gameDate = participants.getGameDate();
//			Long timeEliminated = participants.getTimeEliminated();
//			PlayTime playTime = new PlayTime(gameDate, timeEliminated);
//			
//			playStatistics.addPlayTime(playTime);
//		}
//		playStatistics.sort();
//		
//		return playStatistics;
//	}


}
