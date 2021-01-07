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

import com.jylee.tft.statistic.domain.TFTMatchDetail;
import com.jylee.tft.statistic.domain.Figure;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PeriodFigure;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTMatchDetailRepository;

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
	TFTMatchDetailRepository matchDetail;
	
	@Autowired
	SummonerRepository summoner;
	
	public Figure getStatistics(String userId, Period period) {	

		PeriodFigure figure = new PeriodFigure();	
		Optional<Summoner> user = summoner.findByName(userId);		
		
		Summoner userInfo = user.orElseGet(()->retrieveSummoner(userId));

		List<TFTMatchDetail> matchDetailList = matchDetail.findByPuuidAndBetweenGameDatetime(userInfo.getPuuid(), period.getFrom(), period.getTo());
		
		//TODO n+1 select가 일어나는지 확인
		for(TFTMatchDetail tFTMatchDetail : matchDetailList) {			
			Date gameDate = tFTMatchDetail.getMatch().getGameDatetime();
			Date timeEliminated = tFTMatchDetail.getTimeEliminated();
			figure.setFigure(gameDate, timeEliminated.getTime());
		}
		
		return figure;
	}
	
	private Summoner retrieveSummoner(String userId) {
		return null;
	}

}
