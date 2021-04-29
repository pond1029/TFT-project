/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitor.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.List;

import com.jylee.tft.statistic.domain.LOLAccount;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PlayTime;
import com.jylee.tft.statistic.domain.TFTAccount;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitor.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface AccountVisitor {

	public abstract List<PlayTime> getPlayTimes(TFTAccount account, Period period);
	public abstract List<PlayTime> getPlayTimes(LOLAccount account, Period period);
	
}
