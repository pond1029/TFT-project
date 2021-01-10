/**
  * @Package : com.jylee.tft.util
  * @FileName : PondUtils.java
  * @Date : 2021. 1. 11. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Map;

/**
  * @Package : com.jylee.tft.util
  * @FileName : PondUtils.java
  * @Date : 2021. 1. 11. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface PondUtils {
	
	public static Integer getInteger(Map<String, Object> map, String key, Integer defaultValue) {
		Object value = map.get(key);
		if(value instanceof Integer) {
			return (Integer) value;
		}
		return defaultValue;		
	}

	public static Integer getInteger(Map<String, Object> map, String key) {
		Object value = map.get(key);
		if(value instanceof Integer) {
			return (Integer) value;
		}
		return 0;
	}	
	
	public static Long getLong(Map<String, Object> map, String key, Long defaultValue) {
		Object value = map.get(key);
		if(value instanceof Long) {
			return (Long) value;
		}
		if(value instanceof Integer) {
			return ((Integer) value).longValue();
		}
		return defaultValue;
	}

	public static Long getLong(Map<String, Object> map, String key) {
		Object value = map.get(key);
		if(value instanceof Long) {
			return (Long) value;
		}
		if(value instanceof Integer) {
			return ((Integer) value).longValue();
		}
		return 0L;
	}
	
	public static Double getDouble(Map<String, Object> map, String key, Double defaultValue) {
		Object value = map.get(key);
		if(value instanceof Double) {
			return (Double) value;
		}
		if(value instanceof Float) {
			return ((Float) value).doubleValue();
		}
		return defaultValue;
	}

	public static Double getDouble(Map<String, Object> map, String key) {
		Object value = map.get(key);
		if(value instanceof Double) {
			return (Double) value;
		}
		if(value instanceof Float) {
			return ((Float) value).doubleValue();
		}
		return 0.0d;
	}

	public static String getString(Map<String, Object> map, String key, String defaultValue) {
		Object value = map.get(key);
		if(value instanceof String) {
			return (String) value;
		}
		if(value instanceof Long || value instanceof Integer || value instanceof Character || value instanceof Double || value instanceof Float) {
			return value.toString();
		}
		return defaultValue;
	}	
	
	public static String getString(Map<String, Object> map, String key) {
		Object value = map.get(key);
		if(value instanceof String) {
			return (String) value;
		}
		if(value instanceof Long || value instanceof Integer || value instanceof Character || value instanceof Double || value instanceof Float) {
			return value.toString();
		}
		return "";
	}	
	
	public static LocalDateTime longToDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
	}
	
	public static LocalDate longToDate(Long timestamp) {
		return LocalDate.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());		
	}
	
	public static LocalTime intToTime(Integer seconds) {
		return LocalTime.of(0, 0, seconds);
	}

	



}
