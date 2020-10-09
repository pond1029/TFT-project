package com.jylee.tft.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pond API 조회 관리
 */
@Service
@Slf4j
public class ApiRetrieveService {

	private static final String serviceUrl = "https://asia.api.riotgames.com";
	private static final String apiKey = "RGAPI-2aa891a6-f261-4684-ba62-f48e4078603f";

	public void update() throws Exception {

		Gson gson = new Gson();
		String puuid = "EbsbjdhoWzAvsnU_C8p9djSq_A9-_7pwXU0WaJ_EN_8SbWS0bR7djr5EVa99ZKq1QyT8yswhvDeeOg";
		Map<String, Object> parameters = new HashMap();
		try {
			ResponseEntity<String> userRsp = getMatchDetailInfo(puuid, parameters);
			if (userRsp != null) {
				String[] userMap = gson.fromJson(userRsp.getBody(), String[].class);
				for (String match : userMap) {
					log.info(match);
				}
			}

		} catch (Exception e) {
			log.error("조회 ERROR : {}", e.getMessage());
		}
	}

	public ResponseEntity<String> getMatchDetailInfo(String puuid, Map<String, Object> parameters) throws Exception {
		String apiUrl = "/tft/match/v1/matches/by-puuid/" + puuid + "/ids";
		apiUrl += "?count=100";
		return sendRest(serviceUrl, apiUrl, parameters);
	}

	public ResponseEntity<String> sendRest(String restUrl, String url, Map<String, Object> parameters) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36");
			headers.add("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Origin", "https://developer.riotgames.com");
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.add("X-Riot-Token", apiKey);
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(parameters, headers);
			ResponseEntity<String> response = restTemplate.exchange(restUrl + url, HttpMethod.GET, request,
					String.class);
			return response;
		} catch (Exception e) {
			log.error("error : {}", e.getMessage());
		}
		return null;
	}

	public ResponseEntity<String> sendRest2(String restUrl, String url, Map<String, Object> parameters) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
					"https://asia.api.riotgames.com/tft/match/v1/matches/KR_4610740297?api_key=RGAPI-2aa891a6-f261-4684-ba62-f48e4078603f");
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(parameters, headers);
			ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request,
					String.class);

			return response;
		} catch (Exception e) {
			log.error("error : {}", e.getMessage());
		}
		return null;
	}
}
