/**
  * @Package : com.jylee.tft.service.retrieve
  * @FileName : Retrieve.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service.retrieve;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
  * @Package : com.jylee.tft.service.retrieve
  * @FileName : Retrieve.java
  * @Date : 2020. 11. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface Retrieve {
	
	public Object retrieve(Map<String, Object> parameters);
		
}
