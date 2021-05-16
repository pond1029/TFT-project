/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitor.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.time.LocalDateTime;
import java.util.List;

import com.jylee.tft.modules.account.domain.LOLAccount;
import com.jylee.tft.modules.account.domain.PlayTime;
import com.jylee.tft.modules.account.domain.TFTAccount;
import com.jylee.tft.modules.statistic.domain.Period;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitor.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface AccountVisitor {

	public abstract List<PlayTime> getPlayTimes(TFTAccount account, LocalDateTime from, LocalDateTime to);
	public abstract void update(TFTAccount account);
	
	public abstract List<PlayTime> getPlayTimes(LOLAccount account, LocalDateTime from, LocalDateTime to);
	public abstract void update(LOLAccount account);
}
