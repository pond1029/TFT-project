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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.service.LOLDataManager;
import com.jylee.tft.statistic.service.TFTDataManager;

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
	
	@Autowired
	TFTDataManager tftAccount;

	@Autowired
	LOLDataManager lolAccount;
	
	@Test
	@DisplayName("LOL 계정 정보 조회")
	public void lolAccount() {
		assertThat(lolAccount.getPlayTimes("야방금무빙봤냐", new Period(2020, 12)).size()).isEqualTo(9);
	}
	
	@Test
	@DisplayName("TFT 계정 정보 조회")
	public void tftAccount() {
		assertThat(tftAccount.getPlayTimes("야방금무빙봤냐", new Period(2021, 1))).isEmpty();
		
	}
}
