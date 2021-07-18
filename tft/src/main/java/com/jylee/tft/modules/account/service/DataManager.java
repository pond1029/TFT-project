/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import com.jylee.tft.modules.account.domain.Account;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 데이터  관리
  */

public interface DataManager {
	
	public abstract Object initAccount(Account account) throws Exception;
	
	public abstract void update(Account account) throws Exception;
}
