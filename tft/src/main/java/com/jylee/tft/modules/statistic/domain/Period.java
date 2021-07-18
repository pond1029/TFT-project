/**
  * @Package : com.jylee.tft.dao
  * @FileName : Period.java
  * @LocalDateTime : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.statistic.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : Period.java
  * @LocalDateTime : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 기간 데이터 (from ~ to)
  */

@Getter @Setter
@EqualsAndHashCode
public class Period implements StatisticCondition{
	
	private LocalDateTime from;
	private LocalDateTime to;
			
	public List<LocalDateTime> splitByDay() { 
		List<LocalDateTime> dayLists = new ArrayList<LocalDateTime>();		
		LocalDateTime day = LocalDateTime.of(from.toLocalDate(), from.toLocalTime());
		while(day.isBefore(to)) {
			dayLists.add(day);
			day = day.plusDays(1);
		}
		return dayLists;
	}

	public Period() {
		this.from = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),1,0,0);
		this.to = from.plusMonths(1);
	}
	
	public Period(LocalDateTime from, LocalDateTime to) {
		this.from = from;
		this.to = to;
	}

	public Period(int year) {
		LocalDateTime date = LocalDateTime.of(year,1,1,0,0);		
		this.from = date;		
		this.to = date.plusYears(1);
	}

	public Period(int year, int month) {
		LocalDateTime date = LocalDateTime.of(year,month,1,0,0);		
		this.from = date;		
		this.to = date.plusMonths(1);
	}
	
	public Period(int year, int month, int day) {
		LocalDateTime date = LocalDateTime.of(year,month,day,0,0);		
		this.from = date;		
		this.to = date.plusDays(1);		
	}

	public Period(String stringFrom, String stringTo) {
			this.from = LocalDateTime.parse(stringFrom, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			this.to = LocalDateTime.parse(stringTo, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
}
