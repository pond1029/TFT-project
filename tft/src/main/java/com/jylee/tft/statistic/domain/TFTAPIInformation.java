/**
  * @Package : com.jylee.tft.dao
  * @FileName : ApiInformation.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : ApiInformation.java
  * @Date : 2020. 10. 22. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Component
@Validated
@Configuration
@Getter @Setter
@ConfigurationProperties(prefix = "api.tft")
public class TFTAPIInformation implements APIInformation {

	private final String SUMMONER_URL = "/tft/summoner/v1/summoners/by-name/{summonerName}";
	private final String MATCHE_IDS_URL = "/tft/match/v1/matches/by-puuid/{puuid}/ids";
	private final String MATCHES_URL = "/tft/match/v1/matches/{matchId}";
	
	private String key;
	private String baseUrl;
	private String asiaUrl;
	
	@Override
	public String getBaseUrl() {
		return this.baseUrl;
	}
	
	@Override
	public String getKey() {
		return this.key;
	}
	
	public String getAsiaUrl() {
		return this.asiaUrl;
	}
	
	public String getSummonerUrl(String summonerName) {
		return this.getBaseUrl() + SUMMONER_URL.replace("{summonerName}", summonerName);
	}

	public String getMatchIdsUrl(String puuid) {
		return this.getAsiaUrl() + MATCHE_IDS_URL.replace("{puuid}", puuid);
	}
	public String getMatchesUrl(String matchId) {
		return this.getAsiaUrl() + MATCHES_URL.replace("{matchId}", matchId);
	}
}