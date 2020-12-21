/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLAPIInformation.java
  * @Date : 2020. 12. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLAPIInformation.java
  * @Date : 2020. 12. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Configuration
@ConfigurationProperties(prefix = "api.riot")
@Component
@Validated
public class LOLAPIInformation implements APIInformation{

	private String urlKr;
	private String keyLol;
	
	@Override
	public String getBaseUrl() {
		return this.urlKr;
	}

	@Override
	public String getKey() {
		return this.keyLol;
	}

}
