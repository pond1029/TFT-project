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
public class PlayTime {

	private String date;
	private Long seconds;
	
	public Long getMinute() {
		return seconds/60;
	}
	
	public int getDay() {
		
		return 0;
	}
	
	public boolean isSameDate(PlayTime playTime) {
		return date.equals(playTime.getDate());
	}
}
