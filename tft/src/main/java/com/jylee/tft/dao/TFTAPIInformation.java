/**
  * @Package : com.jylee.tft.dao
  * @FileName : ApiInformation.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : ApiInformation.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Configuration
@ConfigurationProperties(prefix = "api.riot")
@Component
@Validated
public class TFTAPIInformation implements APIInformation{

	private String urlKr;
	private String urlAsia;
	private String keyTft;
	
	@Override
	public String getBaseUrl() {
		return this.urlKr;
	}
	
	@Override
	public String getKey() {
		return this.keyTft;
	}
	
	public String getMatchUrl() {
		return this.urlAsia;
	}
	
}