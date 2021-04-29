/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import com.jylee.tft.statistic.domain.Account;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 데이터  관리
  */

public interface DataManager {
	
	public abstract void initAccount(Account account);
	
	public abstract void update(Account account);
}
