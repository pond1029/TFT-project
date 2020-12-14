/**
  * @Package : com.jylee.tft.service
  * @FileName : TFTService.java
  * @Date : 2020. 11. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.Participants;
import com.jylee.tft.dao.SummonerInfo;
import com.jylee.tft.service.ParticipantsService;
import com.jylee.tft.service.SummonerInfoService;

/**
  * @Package : com.jylee.tft.service
  * @FileName : TFTService.java
  * @Date : 2020. 11. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
public class StatisticsService {

	@Autowired
	ParticipantsService participantsService;
	
	@Autowired
	SummonerInfoService summonerInfoService;
	
	public PlayStatistics getPlayTimeStatistics(int year, int month, String summonerName) {
		
		SummonerInfo summonerInfo = summonerInfoService.getSummonerInfo(summonerName);
		
		PlayStatistics playStatistics = new PlayStatistics(year, month);

		List<Participants> participantLists = participantsService.getParticipants(summonerInfo.getPuuid());
		
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
