/**
  * @Package : com.jylee.tft.service
  * @FileName : Statistic.java
  * @Date : 2020. 10. 23. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

import com.jylee.tft.dao.Period;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : Statistic.java
  * @Date : 2020. 10. 23. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 플레이 시간 통계
  */

@Slf4j
public class PlayTimeStatistics extends Statistics{
	
	/**
	 * @param period
	 * @param id
	 */
	public PlayTimeStatistics(String id, Period period) {
		super(id, period);	
		
	}
	
}
