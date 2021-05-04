/**
  * @Package : com.jylee.tft.statistic
  * @FileName : Graph.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.statistic.domain;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : Graph.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 통계 산출 수치
  */

public interface Figure {
	
	public abstract String[] getLabels();
	public abstract Long[] getDatas();
	public abstract Long getTotalData();
	
}
