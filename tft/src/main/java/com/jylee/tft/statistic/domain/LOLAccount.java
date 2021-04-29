/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
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
  * @FileName : LOLAccount.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public class LOLAccount extends RiotAccount{


	public LOLAccount(String accountId, AccountType accountType) {
		super(accountId, accountType);
	}

	@Override
	public List<PlayTime> getPlayTimes(AccountVisitor visitor, Period period) {
		return visitor.getPlayTimes(this, period);
	}


}
