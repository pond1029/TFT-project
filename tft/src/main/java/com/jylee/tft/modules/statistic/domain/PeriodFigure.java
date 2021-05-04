/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTFigure.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.statistic.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Map<String, Long> figures = new HashMap<String, Long>();
	
	public void setFigure(LocalDate label, long data) {		
		this.figures.put(label.toString(), figures.getOrDefault(label.toString(), 0L) + data);
	}

	public void setFigure(String label, long data) {		
		this.figures.put(label, figures.getOrDefault(label.toString(), 0L) + data);
	}
	
	public PeriodFigure(Period period) {
		//날짜 세팅
		List<LocalDateTime> dayList = period.splitByDay();
		
		for(LocalDateTime day : dayList) {
			this.figures.put(day.format(DateTimeFormatter.ofPattern("dd")), 0L);
		}
	}

	@Override
	public String[] getLabels() {
		return figures.keySet().stream().sorted().toArray(String[]::new);
	}
	
	@Override
	public Long[] getDatas() {
		String[] keyArray = this.getLabels();
		Long[] dataArray = new Long[keyArray.length];
		
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = figures.get(keyArray[i]);
		}
		
		return dataArray;
	}

	@Override
	public Long getTotalData() {
		Long[] datas = this.getDatas();
		return Arrays.stream(datas).reduce(0L, Long::sum);
	}
	
}
