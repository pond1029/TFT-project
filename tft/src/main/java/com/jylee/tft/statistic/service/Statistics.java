/**
  * @Package : com.jylee.tft.service.statistic
  * @FileName : Statistic.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.List;

import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.Figure;
import com.jylee.tft.statistic.domain.StatisticCondition;
import com.jylee.tft.statistic.domain.Period;

/**
  * @Package : com.jylee.tft.service.statistic
  * @FileName : Statistic.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 통계
  */

public interface Statistics {
	
	public abstract Figure getFigure(Account account, StatisticCondition condition);
}
