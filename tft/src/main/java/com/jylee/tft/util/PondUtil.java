/**
  * @Package : com.jylee.tft.util
  * @FileName : PondUtil.java
  * @Date : 2020. 10. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
  * @Package : com.jylee.tft.util
  * @FileName : PondUtil.java
  * @Date : 2020. 10. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public class PondUtil {

	public String getUnixToString(Long timestamp, String format) {
		Date date = new Date(timestamp*1000L);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT")); 
		String stringDate = sdf.format(date);
		return stringDate;		
	}
	

	public String getUnixToString(Long timestamp) {
		Date date = new Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9")); 
		String stringDate = sdf.format(date);
		return stringDate;		
	}
}
