/**
  * @Package : com.jylee.tft.service
  * @FileName : PeriodTest.java
  * @Date : 2020. 11. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jylee.tft.dao.Period;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : PeriodTest.java
  * @Date : 2020. 11. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
@ExtendWith(MockitoExtension.class)
public class PeriodTest {
	
	private Period period;
	
	@Test
	@DisplayName("기간 데이터 생성이 잘 되는지")
	void construnctor_test_period() {
		//given
		period = new Period(2020,9);
		List<String> list = period.splitByDay();
		for(int i = 0; i < list.size(); i++) {
			log.info(list.get(i));
		}
		//when
		//then
		
	}

}
