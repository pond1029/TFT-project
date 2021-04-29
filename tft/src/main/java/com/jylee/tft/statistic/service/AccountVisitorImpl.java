/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitorImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.statistic.domain.LOLAccount;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PlayTime;
import com.jylee.tft.statistic.domain.TFTAccount;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitorImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
public class AccountVisitorImpl implements AccountVisitor{

	@Autowired
	TFTService tftService;

	@Autowired
	LOLService lolService;
	
	@Override
	public List<PlayTime> getPlayTimes(TFTAccount account, Period period) {
		return tftService.getPlayTimes(account, period);
	}

	@Override
	public List<PlayTime> getPlayTimes(LOLAccount account, Period period) {
		return lolService.getPlayTimes(account, period);
	}

}
