/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountFactory.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import org.springframework.stereotype.Component;

import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.LOLAccount;
import com.jylee.tft.statistic.domain.TFTAccount;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountFactory.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Component
public class AccountFactory {
	
	public static Account getAccount(String accountId, AccountType accountType) {

		if(AccountType.TFT.equals(accountType)) {
			return new TFTAccount(accountId, accountType);
		}
		if(AccountType.LOL.equals(accountType)) {
			return new LOLAccount(accountId, accountType);
		}
		
		return null;
	}
	
	public static  Account getAccount(String accountId, String accountType) {
		
		if("TFT".equals(accountType)) {
			return new TFTAccount(accountId, AccountType.valueOf(accountType));
		}
		if("LOL".equals(accountType)) {
			return new LOLAccount(accountId, AccountType.valueOf(accountType));
		}
		
		return null;
	}
	
}
