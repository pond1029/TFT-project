/**
  * @Package : com.jylee.tft
  * @FileName : DataCollectTest.java
  * @Date : 2021. 7. 13. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jylee.tft.modules.account.domain.LOLMatch;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.service.LOLDataCollector;

/**
  * @Package : com.jylee.tft
  * @FileName : DataCollectTest.java
  * @Date : 2021. 7. 13. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LOLDataCollectTest {

	@Autowired
	LOLDataCollector lol;
	
	Summoner summoner;
	List<LOLMatch> matches;
	
	@Test
	@Order(1)
	@DisplayName("LOL API 소환사 검색 정상")
	public void LOL_Api_Summoner_Success() throws JsonProcessingException {
		summoner = lol.getSummoner("야방금무빙봤냐");
		assertThat(summoner)
		.isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("LOL API 매치 검색 정상")
	public void LOL_Api_Matches_Success() throws JsonMappingException, JsonProcessingException {		
		matches = lol.getMatches(summoner.getAccountId());
		
		assertThat(matches)
		.isNotNull()
		.hasSizeGreaterThan(1);
	}

	@Test
	@Order(3)
	@DisplayName("LOL API 매치 디테일 검색 정상")
	public void LIL_Api_MatchDetail_Success() throws JsonMappingException, JsonProcessingException {
		LOLMatch match = lol.getMatchDetail(matches.get(0), summoner.getAccountId());
		
		assertThat(match)
		.isNotNull();
		
	}
}
