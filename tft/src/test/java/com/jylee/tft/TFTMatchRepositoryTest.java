/**
  * @Package : com.jylee.tft
  * @FileName : TFTMatch.java
  * @Date : 2021. 1. 11. 
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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.jylee.tft.statistic.domain.LOLMatch;
import com.jylee.tft.statistic.domain.TFTMatch;
import com.jylee.tft.statistic.repository.LOLMatchRepository;
import com.jylee.tft.statistic.repository.TFTMatchRepository;

/**
  * @Package : com.jylee.tft
  * @FileName : TFTMatch.java
  * @Date : 2021. 1. 11. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@DataJpaTest
public class TFTMatchRepositoryTest {

	@Autowired
	LOLMatchRepository match;
	
	@Test
	@DisplayName("데이터 조회")
	public void findData() {
		Page<LOLMatch> data = match.findRecent("ASD", PageRequest.of(0, 10));
		assertThat(data.get()).isEmpty();
	}
}
