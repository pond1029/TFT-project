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

import com.jylee.tft.statistic.domain.Account;
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
	
	private final LOLAccountManager lol;	
	private final TFTAccountManager tft;
	
	@Override
	public Figure getStatistics(List<Account> accounts, Period period) {	
		PeriodFigure figure = new PeriodFigure();
		
		for(Account account : accounts) {
			
			if(account.getType().equals(AccountType.LOL)) {
				List<PlayTime> playTimes = lol.getPlayTimes(account.getId(), period);
				for(PlayTime play : playTimes) {
					figure.setFigure(play.getPlayedDate(), play.getPlayTime().toSecondOfDay());
				}				
			}
			if(account.getType().equals(AccountType.TFT)) {
				List<PlayTime> playTimes = tft.getPlayTimes(account.getId(), period);
				for(PlayTime play : playTimes) {
					figure.setFigure(play.getPlayedDate(), play.getPlayTime().toSecondOfDay());
				}				
				
			}
		}
				
		return figure;
	}
	
}
