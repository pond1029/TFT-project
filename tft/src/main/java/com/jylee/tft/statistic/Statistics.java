/**
  * @Package : com.jylee.tft.service.statistic
  * @FileName : Statistic.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

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
	  * @Information : 전체 데이터 통계
	  * @param userId
	  * @return
	 */
	public Figure getStatistics(String userId);
}
