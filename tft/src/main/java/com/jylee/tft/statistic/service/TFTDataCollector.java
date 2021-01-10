/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTDataCollector.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.jylee.tft.statistic.domain.TFTAPIInformation;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTDataCollector.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
public class TFTDataCollector{

	@Autowired
	TFTAPIInformation api;
	
	public String getSummoner(String summonerName, Map<String, Object> parameters) {
		return send(api.getSummonerUrl(summonerName), parameters);
	}
	
	public String getMatchIds(String puuid, Map<String, Object> parameters) {
		return send(api.getMatchIdsUrl(puuid), parameters);
	}
	
	public String getMatches(String matchId, Map<String, Object> parameters) {
		return send(api.getMatchesUrl(matchId), parameters);
	}

	private String send(String url, Map<String, Object> parameters){
		RestUtil restUtil = new RestUtil() {			
			@Override
			public HttpHeaders getHeader() {
				HttpHeaders header = new HttpHeaders();			
				header.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
				header.add("Accept", MediaType.APPLICATION_JSON_VALUE);
				header.add("X-Riot-Token", api.getKey());
				return header;
			}
		};
		return restUtil.send(url, parameters);
	}
}
