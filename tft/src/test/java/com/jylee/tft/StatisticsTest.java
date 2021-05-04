/**
  * @Package : com.jylee.tft
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jylee.tft.modules.account.domain.Account;
import com.jylee.tft.modules.account.service.AccountFactory;
import com.jylee.tft.modules.account.service.AccountVisitor;
import com.jylee.tft.modules.account.service.AccountVisitorImpl;
import com.jylee.tft.modules.statistic.domain.Period;
import com.jylee.tft.modules.statistic.service.PeriodStatistics;

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
		Period p = new Period(2021,02);
		Account account = AccountFactory.getAccount("야 방금 무빙봤냐", "TFT");
		account.getPlayTimes(accountVisitor, p.getFrom(), p.getTo());		
	}

	@Test
	@DisplayName("LOL 조회")
	public void lolPlaytimeSearch() {
		Period p = new Period(2020,12);
		Account account = AccountFactory.getAccount("야 방금 무빙봤냐", "LOL");
		account.getPlayTimes(accountVisitor, p.getFrom(), p.getTo());		
	}
	
}
