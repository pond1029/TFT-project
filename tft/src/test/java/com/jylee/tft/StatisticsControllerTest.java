/**
  * @Package : com.jylee.tft
  * @FileName : StatisticsControllerTest.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
  * @Package : com.jylee.tft
  * @FileName : StatisticsControllerTest.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@DisplayName("통계 화면 표출")
	@Test
	public void statistics() throws Exception {
		mockMvc.perform(get("/statistics"))
				.andExpect(status().isOk())
				.andExpect(view().name("statistic/statistics"));
	}
}
