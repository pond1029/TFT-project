/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jylee.tft.statistic.domain.SearchForm;
import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.Figure;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PeriodFigure;
import com.jylee.tft.statistic.domain.PlayTime;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : TFT 통계
  */

@Service
@RequiredArgsConstructor
public class PeriodStatistics implements Statistics{
	
	private final LOLDataManager lol;	
	private final TFTDataManager tft;
	
	@Override
	public Figure getFigure(SearchForm searchForm) {	
		PeriodFigure figure = new PeriodFigure(searchForm.getPeriod());
		
		//TODO if문 제거 필요
		if(searchForm.getType().equals(AccountType.LOL)) {
			List<PlayTime> playTimes = lol.getPlayTimes(searchForm.getAccountId(), searchForm.getPeriod());
			for(PlayTime play : playTimes) {
				figure.setFigure(play.getPlayedDate(), play.getPlayTime().toSecondOfDay());
			}				
		}
		
		if(searchForm.getType().equals(AccountType.TFT)) {
			List<PlayTime> playTimes = tft.getPlayTimes(searchForm.getAccountId(), searchForm.getPeriod());
			for(PlayTime play : playTimes) {
				figure.setFigure(play.getPlayedDate(), play.getPlayTime().toSecondOfDay());
			}				
			
		}
						
		return figure;
	}
	
}
