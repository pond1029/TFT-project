/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.util.List;

import com.jylee.tft.statistic.service.AccountVisitor;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public abstract class Account {

	private String accountName;
	private AccountType accountType;
	
	public Account(String accountName, AccountType accountType) {
		super();
		this.accountName = accountName;
		this.accountType = accountType;
	}

	public abstract List<PlayTime> getPlayTimes(AccountVisitor visitor, Period period);

	public String getAccountName() {
		return this.accountName;
	}

	public AccountType getAccountType() {
		return this.accountType;
	}
}
