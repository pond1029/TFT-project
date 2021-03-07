/**
  * @Package : com.jylee.tft
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.Figure;
import com.jylee.tft.statistic.domain.Period;
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
	PeriodStatistics statistics;
	
	@Test
	@DisplayName("통계 테스트")
	public void manyStatistics() {
		List<Account> accounts = List.of(new Account(AccountType.LOL, "야방금무빙봤냐"), new Account(AccountType.TFT, "야방금무빙봤냐"));
		Figure figure = statistics.getStatistics(accounts, new Period(2021,02));
		assertThat(figure.getTotalData()).isGreaterThan(0L);
	}
}
