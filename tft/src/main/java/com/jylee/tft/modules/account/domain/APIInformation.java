/**
  * @Package : com.jylee.tft.dao
  * @FileName : APIManager.java
  * @Date : 2020. 12. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.domain;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : APIManager.java
  * @Date : 2020. 12. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : API 정보
  */

public interface APIInformation {
	
	public abstract String getBaseUrl();
	
	public abstract String getKey();
}
