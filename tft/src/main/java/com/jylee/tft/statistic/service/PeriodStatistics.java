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
import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.Figure;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PeriodFigure;
import com.jylee.tft.statistic.domain.PlayTime;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTStatistics.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : TFT 통계
  */

public class PeriodStatistics implements Statistics{
	
	@Override
	public Figure getStatistics(List<Account> accounts, Period period) {	
		PeriodFigure figure = new PeriodFigure();	
		for(Account account : accounts) {
			List<PlayTime> playTimes = account.getPlayTimes(period);
			for(PlayTime play : playTimes) {
				figure.setFigure(play.getPlayedDate(), play.getPlayTime().toSecondOfDay());
			}
		}
				
		return figure;
	}
	
}
