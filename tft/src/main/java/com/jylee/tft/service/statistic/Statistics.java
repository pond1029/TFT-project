/**
  * @Package : com.jylee.tft.service.statistic
  * @FileName : Statistics.java
  * @Date : 2020. 11. 20. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service.statistic;

import com.jylee.tft.dao.Period;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.service.statistic
  * @FileName : Statistics.java
  * @Date : 2020. 11. 20. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Getter @Setter
@AllArgsConstructor
public abstract class Statistics {
	String id;
	Period period;
}
