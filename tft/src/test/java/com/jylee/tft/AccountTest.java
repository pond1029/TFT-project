/**
  * @Package : com.jylee.tft
  * @FileName : AccountTest.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.LOLAccount;
import com.jylee.tft.statistic.domain.Period;

/**
  * @Package : com.jylee.tft
  * @FileName : AccountTest.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@SpringBootTest
public class AccountTest {
	
	@Test
	@DisplayName("LOL 계정 정보 조회")
	public void lolAccount() {
		Account account = new LOLAccount("야방금무빙봤냐");
		assertThat(account.getPlayTimes(new Period(2021, 1))).isNotEmpty();
	}
}
