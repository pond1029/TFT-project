/**
  * @Package : com.jylee.tft.service
  * @FileName : ApiManager.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.jylee.tft.dao.MatchesAndPuuids;

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
	MatchesAndPuuidsService matchesAndPuuidsService;

	@Autowired
	DataManager datamanager;
	
	public void update(String puuid) {

		List<String> matchIdList = retrieveMatchId(puuid);

		if (!matchIdList.isEmpty()) {
			List<MatchesAndPuuids> matchList = matchesAndPuuidsService.getListMatchesAndPuuids(puuid);
			
			List<String> dbList = matchList.stream()
					.map(MatchesAndPuuids::getMatchId)
					.collect(Collectors.toCollection(ArrayList::new));
			
			List<String> insertList = new ArrayList<String>();
			
			for(int i = 0; i < matchIdList.size(); i++) {
				if(!dbList.contains(matchIdList.get(i))) {
					insertList.add(matchIdList.get(i));
				}
			}
			
			
			for (String matchId : insertList) {
				Map<String, Object> map = retrieveMatchInfo(matchId);
				matchesAndPuuidsService.setMatchesAndPuuids(matchId, puuid);
				datamanager.insertMatchInfo(map);
			}
			
		}
	}

	private List<String> retrieveMatchId(String puuid) {
		Gson gson = new Gson();
		Map<String, Object> parameters = new HashMap();
		String apiUrl = "/tft/match/v1/matches/by-puuid/" + puuid + "/ids?count=29";
		ResponseEntity<String> userRsp = sendRest(apiInformation.getUrl(), apiUrl, parameters);
		String[] body = gson.fromJson(userRsp.getBody(), String[].class);
		
		return Arrays.asList(body);
	}

	private Map<String, Object> retrieveMatchInfo(String matchId) {
		Gson gson = new Gson();
		Map<String, Object> parameters = new HashMap();
		String apiUrl = "/tft/match/v1/matches/" + matchId;
		ResponseEntity<String> response = sendRest(apiInformation.getUrl(), apiUrl, parameters);		
		Map<String, Object> bodyMap = gson.fromJson(response.getBody(), Map.class);
		return bodyMap;
	}
	
	public ResponseEntity<String> sendRest(String restUrl, String url, Map<String, Object> parameters) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.add("X-Riot-Token", apiInformation.getKey());
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(parameters, headers);
			ResponseEntity<String> response = restTemplate.exchange(restUrl + url, HttpMethod.GET, request,
					String.class);
			return response;
		} catch (Exception e) {
			log.error("error : {}", e.getMessage());
		}
		return null;
	}

}
