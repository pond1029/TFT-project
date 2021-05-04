/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.statistic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.modules.account.domain.Account;
import com.jylee.tft.modules.account.domain.AccountType;
import com.jylee.tft.modules.account.domain.PlayTime;
import com.jylee.tft.modules.account.service.AccountVisitorImpl;
import com.jylee.tft.modules.statistic.domain.Figure;
import com.jylee.tft.modules.statistic.domain.Period;
import com.jylee.tft.modules.statistic.domain.PeriodFigure;
import com.jylee.tft.modules.statistic.domain.StatisticCondition;

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
	
	private final AccountVisitorImpl visitor;	
	
	@Override
	public Figure getFigure(Account account, StatisticCondition condition) {
		Period period = (Period) condition;
		
		PeriodFigure figure = new PeriodFigure();
		
		List<PlayTime> playTimes = account.getPlayTimes(visitor, period.getFrom(), period.getTo());		
		
		for(PlayTime play : playTimes) {
			figure.setFigure(play.getPlayedDate(), play.getPlayTime().toSecondOfDay());
		}	
		
		return figure;
	}
	
}
