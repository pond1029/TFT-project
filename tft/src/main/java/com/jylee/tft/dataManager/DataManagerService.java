/**
  * @Package : com.jylee.tft.service
  * @FileName : DataManager.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dataManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.MatchesAndPuuids;
import com.jylee.tft.dao.Participants;
import com.jylee.tft.dao.SummonerInfo;
import com.jylee.tft.service.ApiManager;
import com.jylee.tft.service.MatchInfoService;
import com.jylee.tft.service.MatchesAndPuuidsService;
import com.jylee.tft.service.ParticipantsService;
import com.jylee.tft.service.SummonerInfoService;
import com.jylee.tft.util.PondUtil;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : DataManager.java
  * @Date : 2020. 10. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 데이터 관리
  */

@Slf4j
@Service
public class DataManagerService implements DataManager{

	@Autowired
	ParticipantsService participantsService;

	@Autowired
	MatchInfoService matchInfoService;

	@Autowired
	MatchesAndPuuidsService matchesAndPuuidsService;

	@Autowired
	SummonerInfoService summonerInfoService;
	
	@Autowired
	ApiManager apiManager;

	@Override
	public boolean update(String summonerId) {

		SummonerInfo summonerInfo = summonerInfoService.getSummonerInfo(summonerId);
		
		List<String> matchIdList = apiManager.retrieveMatchId(summonerInfo.getPuuid());
		
		if (!matchIdList.isEmpty()) {

			List<MatchesAndPuuids> matchList = matchesAndPuuidsService.getListMatchesAndPuuids(summonerInfo.getPuuid());
			
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
				MatchInfo matchInfo = apiManager.retrieveMatchInfo(matchId);
				matchInfoService.setMatchInfo(matchInfo);
				participantsService.setparticipants(matchInfo.getParticipantLists());
				matchesAndPuuidsService.setMatchesAndPuuids(matchId, summonerInfo.getPuuid());
			}
			
		}
		return true;
	}
	
}
