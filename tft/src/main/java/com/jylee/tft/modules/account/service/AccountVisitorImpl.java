/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitorImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.modules.account.domain.LOLAccount;
import com.jylee.tft.modules.account.domain.PlayTime;
import com.jylee.tft.modules.account.domain.TFTAccount;
import com.jylee.tft.modules.statistic.domain.Period;

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
	public List<PlayTime> getPlayTimes(TFTAccount account, LocalDateTime from, LocalDateTime to) {
		return tftService.getPlayTimes(account, from, to);
	}

	@Override
	public List<PlayTime> getPlayTimes(LOLAccount account, LocalDateTime from, LocalDateTime to) {
		return lolService.getPlayTimes(account, from, to);
	}

}
