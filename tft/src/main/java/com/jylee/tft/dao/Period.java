/**
  * @Package : com.jylee.tft.dao
  * @FileName : Period.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : Period.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 기간 (yyyy-MM-dd);
  */

public class Period {

	private SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");	
	private Date from;
	private Date to;
	
	/**
	 * @return the from
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public Date geTto() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(Date to) {
		this.to = to;
	}
		
	public Period(Date from, Date to) {
		this.from = from;
		this.to = to;
	}

	public Period(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, 0, 1);
		this.from = cal.getTime();
		cal.set(year, 11, 31);
		this.to = cal.getTime();
	}

	public Period(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		this.from = cal.getTime();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		this.to = cal.getTime();		
	}
	
	public Period(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		this.from = cal.getTime();
		this.to = cal.getTime();
		
	}
	
	public String getFromByString() {
		return format.format(this.from);
	}

	public String getToByString() {
		return format.format(this.to);
	}
	
	public List<String> splitByDay() {
		List<String> dayLists = new ArrayList();		
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);	
		while(!to.before(cal.getTime())) {
			dayLists.add(format.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}
		return dayLists;
	}
}
