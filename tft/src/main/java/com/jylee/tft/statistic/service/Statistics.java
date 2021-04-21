/**
  * @Package : com.jylee.tft.service.statistic
  * @FileName : Statistic.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.List;

import com.jylee.tft.statistic.domain.SearchForm;
import com.jylee.tft.statistic.domain.Figure;
import com.jylee.tft.statistic.domain.Period;

/**
  * @Package : com.jylee.tft.service.statistic
  * @FileName : Statistic.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 통계
  */

public interface Statistics {
	/**
	 * 
	  * @Method Name : getStatistics
	  * @Date : 2021. 1. 4.
	  * @Author : "LeeJaeYeon"
	  * @Version : 
	  * @Information : 데이터 통계
	  * @param userId
	  * @return
	 */
	public abstract Figure getFigure(SearchForm searchForm);
}
