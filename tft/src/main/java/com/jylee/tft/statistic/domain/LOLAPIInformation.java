/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLAPIInformation.java
  * @Date : 2020. 12. 21. 
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
  * @FileName : LOLAPIInformation.java
  * @Date : 2020. 12. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Component
@Validated
@Configuration
@Getter @Setter
@ConfigurationProperties(prefix = "api.lol")
public class LOLAPIInformation implements APIInformation{

	private final String SUMMONER_URL = "/lol/summoner/v4/summoners/by-name/{summonerName}";
	private final String MATCHLIST_URL = "/lol/match/v4/matchlists/by-account/{encryptedAccountId}";
	private final String MATCHES_URL = "/lol/match/v4/matches/{matchId}";
	
	private String baseUrl;
	private String key;
	
	@Override
	public String getBaseUrl() {
		return this.baseUrl;
	}

	@Override
	public String getKey() {
		return this.key;
	}
	
	public String getSummonerUrl(String summonerName) {
		return getBaseUrl() + SUMMONER_URL.replace("{summonerName}", summonerName);
	}

	public String getMatchListsUrl(String encryptedAccountId){
		return getBaseUrl() + MATCHLIST_URL.replace("{encryptedAccountId}", encryptedAccountId);
	}
	
	public String getMatchesUrl(String matchId) {
		return getBaseUrl() + MATCHES_URL.replace("{matchId}", matchId);
	}
}
