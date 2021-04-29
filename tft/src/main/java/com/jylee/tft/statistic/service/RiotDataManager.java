/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : RiotDataManager.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import com.jylee.tft.statistic.domain.Account;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : RiotDataManager.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public abstract class RiotDataManager implements DataManager{

	@Override
	public void initAccount(Account account) {
		// TODO 소환사 정보 초기화 구현		
	}
	
}
