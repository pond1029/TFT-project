/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : DataCollertor.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : DataCollertor.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
public interface RestUtil {

	public abstract HttpHeaders getHeader();
	
	public default String send(String url, Map<String, Object> parameters) throws HttpStatusCodeException{			
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(getHeader());	
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);	
		if(parameters != null){
			for (String key : parameters.keySet()) {
				builder.queryParam(key, parameters.get(key));
			}	
		}		
		URI responseUri =  builder.build().encode().toUri();
		ResponseEntity<String> response = restTemplate.exchange(
				responseUri, 
				HttpMethod.GET, 
				request,
				String.class);
		return response.getBody();
	}
}
