/**
  * @Package : com.jylee.tft.dao
  * @FileName : PlayStatistics.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : PlayStatistics.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Getter @Setter
public class PlayStatistics {

	private List<PlayTime> playTimeLists;
	
	public PlayStatistics(int year, int month) {
		playTimeLists = new ArrayList<PlayTime>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		Calendar cal = Calendar.getInstance();		
		cal.set(year, month - 1 ,1);		
		int last = cal.getActualMaximum(Calendar.DATE);
		
		for(int i = 0; i < last; i++) {
			String date = sdf.format(cal.getTime());			
			PlayTime playtime = new PlayTime(date, new Long(0L));
			playTimeLists.add(playtime);		
			cal.add(Calendar.DATE, 1);
		}
	}
	
	public void addPlayTime(PlayTime playTime) {
		for(PlayTime inserted : playTimeLists) {
			if(inserted.isSameDate(playTime)) {
				inserted.setSeconds(inserted.getSeconds() + playTime.getSeconds());
			}
		}
	}
	
	
	public void sort() {
		playTimeLists.sort(new Comparator<PlayTime>() {
			@Override
			public int compare(PlayTime p1, PlayTime p2) {
				return p1.getDate().compareTo(p2.getDate());
			}
		});
		
	}
	
	public Long getTotalPlayTime() {
		Long totalPlayTime = new Long(0L);
		for(PlayTime playTime : playTimeLists) {
			totalPlayTime += playTime.getSeconds();
		}
		return totalPlayTime;
	}
}
