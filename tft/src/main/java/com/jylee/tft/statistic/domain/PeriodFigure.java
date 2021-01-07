/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTFigure.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.postgresql.jdbc2.optional.SimpleDataSource;

import lombok.NoArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTFigure.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 특정 기간 산출 데이터, 일별 데이터 표출
  */

@NoArgsConstructor
public class PeriodFigure implements Figure{
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd");
	private Map<String, Long> figures = new HashMap<String, Long>();
	
	public void setFigure(Date label, long data) {		
		this.figures.put(sdf.format(label), figures.getOrDefault(label, 0L) + data);
	}
	
	public PeriodFigure(Period period) {
		//날짜 세팅
		List<Date> dayList = period.splitByDay();
		
		for(Date day : dayList) {
			this.figures.put(sdf.format(day), 0L);
		}
	}

	@Override
	public String[] gatLabel() {
		return figures.keySet().stream().sorted().toArray(String[]::new);
	}
	
	@Override
	public long[] getData() {
		String[] keyArray = this.gatLabel();
		long[] dataArray = new long[keyArray.length];
		
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = figures.get(keyArray[i]);
		}
		
		return dataArray;
	}
	
}
