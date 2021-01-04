/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jylee.tft.dao.Participants;
import com.jylee.tft.dao.SummonerInfo;
import com.jylee.tft.repository.ParticipantsRepository;
import com.jylee.tft.service.ParticipantsService;
import com.jylee.tft.service.SummonerInfoService;

import lombok.AllArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : TFT 통계
  */

public class TFTStatistics implements Statistics{

	@Autowired
	ParticipantsService participantsService;
	/**
	 * 전체 데이터, 데이터가 있는 날짜만 표출
	 */
	public Figure getStatistics(String userId) {		
		Figure figure = new TFTFigure();

		List<Participants> participantLists = participantsService.getParticipants(userId);
		
		for(Participants participants : participantLists) {			
			String gameDate = participants.getGameDate();
			Long timeEliminated = participants.getTimeEliminated();
			figure.setFigure(gameDate, timeEliminated);
		}
		
		return figure;		
	}
	
	/**
	 * 
	  * @Method Name : getStatistics
	  * @Date : 2021. 1. 4.
	  * @Author : "LeeJaeYeon"
	  * @Version : 
	  * @Information : 기간 데이터만 표출
	  * @param period
	  * @param userId
	  * @return
	 */
	public Figure getStatistics(Period period, String userId) {	
		Figure figure = new TFTFigure();
		String[] days = period.splitByDay();		
		
		for (String string : days) {
			figure.setFigure(string, 0L);
		}

		List<Participants> participantLists = participantsService.getParticipants(userId, period.getFromByString(), period.getToByString());
		
		for(Participants participants : participantLists) {			
			String gameDate = participants.getGameDate();
			Long timeEliminated = participants.getTimeEliminated();
			figure.setFigure(gameDate, timeEliminated);
		}
		
		return figure;
	}

}
