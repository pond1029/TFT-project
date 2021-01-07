/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 조회할 계정에 대한 정보
  */

public interface Account {

	public abstract String getType();
	public abstract String getId();
	public abstract String getApiId();
	
}
