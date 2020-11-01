/**
  * @Package : com.jylee.tft.service
  * @FileName : ApiManager.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.jylee.tft.dao.ApiInformation;
import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.Participants;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : ApiManager.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
@Service
public class ApiManager {

	@Autowired
	ApiInformation apiInformation;
	
	@Autowired
	Converter converter;

	public List<String> retrieveMatchId(String puuid) {
		Gson gson = new Gson();
		Map<String, Object> parameters = new HashMap();
		parameters.put("ids", 29);
		String apiUrl = "/tft/match/v1/matches/by-puuid/" + puuid;
		
		String result = send(apiUrl, parameters);
		String[] body = gson.fromJson(result, String[].class);
		
		return Arrays.asList(body);
	}

	public MatchInfo retrieveMatchInfo(String matchId) {
		Gson gson = new Gson();
		Map<String, Object> parameters = new HashMap();
		String apiUrl = "/tft/match/v1/matches/" + matchId;
		String result = send(apiUrl, parameters);
		
		Map<String, Object> bodyMap = gson.fromJson(result, Map.class);
		
		MatchInfo matchInfo = converter.convertToMatchInfo(bodyMap);
		List<Participants> participants = converter.convertToParticipantsLists(bodyMap);
		
		matchInfo.setParticipantLists(participants);
		
		return matchInfo;
	}
	
	public ResponseEntity<String> sendRest(String apiUrl, Map<String, Object> parameters) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.add("X-Riot-Token", apiInformation.getKey());
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(parameters, headers);
			ResponseEntity<String> response = restTemplate.exchange(apiInformation.getUrl() + apiUrl, HttpMethod.GET, request,
					String.class);
			return response;
		} catch (Exception e) {
			log.error("error : {}", e.getMessage());
		}
		return null;
	}


	public String send(String apiUrl, Map<String, Object> parameters) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();			
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.add("X-Riot-Token", apiInformation.getKey());
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(parameters, headers);
			String url = urlBuild(apiUrl, parameters);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request,
					String.class);			
			return response.getBody();
		} catch (Exception e) {
			log.error("error : {}", e.getMessage());
		}
		return null;
	}
	
	private String urlBuild(String apiUrl, Map<String, Object> parameters) {
		String resultUrl = apiInformation.getUrl() + apiUrl;
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
