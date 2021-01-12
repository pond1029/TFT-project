/**
  * @Package : com.jylee.tft
  * @FileName : DataCollectTest.java
  * @Date : 2021. 1. 8. 
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

import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.service.LOLDataCollector;


/**
  * @Package : com.jylee.tft
  * @FileName : DataCollectTest.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@SpringBootTest
public class DataCollectTest {

	@Autowired
	LOLDataCollector lolDataCollector;
	
	@Test
	@DisplayName("소환사 정보 조회")
	public void summonerRetrieve() {		
		Summoner summoner = lolDataCollector.getSummoner("야방금무빙봤냐");
		assertThat(summoner).isNotNull();
	}
	

}
