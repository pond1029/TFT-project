/**
  * @Package : com.jylee.tft.statistic
  * @FileName : Graph.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : Graph.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 통계 산출 수치
  */

public interface Figure {
	
	public abstract String[] gatLabel();
	public abstract long[] getData();
	
}
