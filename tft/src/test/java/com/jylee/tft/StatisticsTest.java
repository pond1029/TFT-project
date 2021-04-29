/**
  * @Package : com.jylee.tft
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.service.AccountFactory;
import com.jylee.tft.statistic.service.AccountVisitor;
import com.jylee.tft.statistic.service.AccountVisitorImpl;
import com.jylee.tft.statistic.service.PeriodStatistics;

/**
  * @Package : com.jylee.tft
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@SpringBootTest
public class StatisticsTest {
	
	@Autowired
	AccountVisitorImpl accountVisitor = new AccountVisitorImpl();
	
	@Disabled
	@Test
	@DisplayName("TFT 조회")
	public void tftPlaytimeSearch() {
		Account account = AccountFactory.getAccount("야 방금 무빙봤냐", "TFT");
		account.getPlayTimes(accountVisitor, new Period(2021,02));		
	}

	@Test
	@DisplayName("LOL 조회")
	public void lolPlaytimeSearch() {
		Account account = AccountFactory.getAccount("야 방금 무빙봤냐", "LOL");
		account.getPlayTimes(accountVisitor, new Period(2020,12));		
	}
	
}
