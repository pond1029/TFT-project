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

	private String accountId;
	private AccountType accountType;
	
	public Account(String accountId, AccountType accountType) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
	}

	public abstract List<PlayTime> getPlayTimes(AccountVisitor visitor, Period period);

	public String getAccountId() {
		return this.accountId;
	}

	public AccountType getAccountType() {
		return this.accountType;
	}
}
