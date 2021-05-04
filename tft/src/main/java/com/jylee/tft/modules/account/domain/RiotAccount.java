/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : RiotAccount.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.domain;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : RiotAccount.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public abstract class RiotAccount extends Account{

	public RiotAccount(String accountName, AccountType accountType) {
		super(accountName, accountType);
	}

}
