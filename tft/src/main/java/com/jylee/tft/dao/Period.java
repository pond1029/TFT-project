/**
  * @Package : com.jylee.tft.dao
  * @FileName : Period.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : Period.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 기간 (yyyy-MM-dd);
  */

public class Period {
	
	private Date startDate;
	private Date endDate;
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
		
	public Period(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Period(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, 0, 1);
		this.startDate = cal.getTime();
		cal.set(year, 11, 31);
		this.endDate = cal.getTime();
	}

	public Period(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		this.startDate = cal.getTime();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		this.endDate = cal.getTime();		
	}
	
	public Period(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		this.startDate = cal.getTime();
		this.endDate = cal.getTime();
		
	}
	
	public String getStartDateToString() {
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");	
		return format.format(this.startDate);
	}

	public String getEndDateToString() {
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");	
		return format.format(this.endDate);
	}
}
