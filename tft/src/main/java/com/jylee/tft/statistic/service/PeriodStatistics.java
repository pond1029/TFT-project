/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.jylee.tft.statistic.domain.TFTParticipant;
import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.Figure;
import com.jylee.tft.statistic.domain.LOLMatchDetail;
import com.jylee.tft.statistic.domain.LOLParticipant;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PeriodFigure;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.repository.LOLMatchDetailRepository;
import com.jylee.tft.statistic.repository.LOLParticipantRepository;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTParticipantRepository;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : TFT 통계
  */

public class PeriodStatistics implements Statistics{

	@Autowired
	TFTParticipantRepository tft;

	@Autowired
	LOLMatchDetailRepository lol;
	
	public Figure getStatistics(Account account, Period period) {	
		PeriodFigure figure = new PeriodFigure();	
		
		if(account.getType().equals("TFT")) {
			List<TFTParticipant> playTimes = tft.findByPuuidAndBetweenGameDatetime(account.getApiId(), period.getFrom(), period.getTo());
			//TODO n+1 select가 일어나는지 확인
			for(TFTParticipant participant : playTimes) {			
				Date date = participant.getMatch().getGameDatetime();
				Date time = participant.getTimeEliminated();
				figure.setFigure(date, time.getTime());
			}
		}
		if(account.getType().equals("LOL")) {
			List<LOLMatchDetail> playTimes = lol.findByAccountIdAndBetweenGameCreation(account.getApiId(), period.getFrom(), period.getTo());
			//TODO n+1 select가 일어나는지 확인
			for(LOLMatchDetail detail : playTimes) {			
				Date date = detail.getGameCreation();
				Date time = detail.getGameDuration();
				figure.setFigure(date, time.getTime());
			}
		}
		
		
		
		return figure;
	}
	
	private void findByType(Account account) {
		
	}
	

}
