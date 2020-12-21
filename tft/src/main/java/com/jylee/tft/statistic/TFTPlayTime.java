/**
  * @Package : com.jylee.tft.dao
  * @FileName : PlayTime.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : PlayTime.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TFTPlayTime implements PlayTime{

	private String date;
	private Long seconds;
	
	public Long getMinute() {
		return seconds/60;
	}
		
	public boolean isSameDate(TFTPlayTime tFTPlayTime) {
		return date.equals(tFTPlayTime.getDate());
	}
}
