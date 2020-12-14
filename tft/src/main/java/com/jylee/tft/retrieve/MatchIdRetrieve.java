/**
  * @Package : com.jylee.tft.service.retrieve
  * @FileName : MatchIdRetrieve.java
  * @Date : 2020. 11. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.retrieve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.jylee.tft.dao.ApiInformation;
import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.Participants;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service.retrieve
  * @FileName : MatchIdRetrieve.java
  * @Date : 2020. 11. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
public class MatchIdRetrieve implements Retrieve{

	private ApiInformation apiInformation = new ApiInformation();
	private String apiUrl = "/tft/match/v1/matches/by-puuid/";
	private String puuid;
	
	/**
	 * @param puuid
	 */
	public MatchIdRetrieve(String puuid) {
		this.puuid = puuid;
		this.apiUrl = this.apiUrl + puuid;
	}


	@Override
	public String[] retrieve(Map<String, Object> parameters) {
		Gson gson = new Gson();
		String response = send(apiUrl, parameters);		
		Map<String, Object> bodyMap = gson.fromJson(response, Map.class);
		
		String[] matchIdArrays = gson.fromJson(response, String[].class);
				
		return matchIdArrays;
	}

	public String send(String apiUrl, Map<String, Object> parameters) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = getHeader();						
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);
			String url = getUrl(parameters);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request,
					String.class);			
			return response.getBody();
		} catch (Exception e) {
			log.error("error : {}", e.getMessage());
		}
		return null;
	}
	
	private HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();			
		headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("X-Riot-Token", apiInformation.getKeyTft());
		return headers;
	}
	
	private String getUrl(Map<String, Object> parameters) {
		String resultUrl = apiInformation.getUrlAsia() + apiUrl;
		String params = "";
		
		Set<String> keySet = parameters.keySet();			
		for(String key : keySet) {
			String param = "&" + key + "=" + parameters.get(key).toString();
		}
		if(!params.equals("")) {
			params = "/" + params.replace("&", "?");			
		}
		return resultUrl + params;
	}
	
}
