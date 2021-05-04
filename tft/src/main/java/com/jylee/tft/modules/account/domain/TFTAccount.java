/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : TFTAccount.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.jylee.tft.modules.account.service.AccountVisitor;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : TFTAccount.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public class TFTAccount extends RiotAccount{
	

	public TFTAccount(String accountName, AccountType accountType) {
		super(accountName, accountType);
	}

	@Override
	public List<PlayTime> getPlayTimes(AccountVisitor visitor, LocalDateTime from, LocalDateTime to) {
		return visitor.getPlayTimes(this, from, to);
	}


}
