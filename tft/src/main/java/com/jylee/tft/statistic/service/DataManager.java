/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.List;

import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PlayTime;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 조회할 계정에 대한 정보
  */

public interface DataManager {
	public abstract List<PlayTime> getPlayTimes(String accountId, Period period);
}
