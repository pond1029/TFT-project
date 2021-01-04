/**
  * @Package : com.jylee.tft.dataManager
  * @FileName : TFTDataManager.java
  * @Date : 2020. 12. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jylee.tft.dao.TFTMatchInfo;
import com.jylee.tft.dao.MatchesAndPuuids;
import com.jylee.tft.dao.SummonerInfo;
import com.jylee.tft.service.ApiManager;
import com.jylee.tft.service.MatchInfoService;
import com.jylee.tft.service.MatchesAndPuuidsService;
import com.jylee.tft.service.ParticipantsService;
import com.jylee.tft.service.SummonerInfoService;

/**
  * @Package : com.jylee.tft.dataManager
  * @FileName : TFTDataManager.java
  * @Date : 2020. 12. 21. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public class TFTDataManager implements DataManager{

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
				TFTMatchInfo tFTMatchInfo = apiManager.retrieveMatchInfo(matchId);
				matchInfoService.setMatchInfo(tFTMatchInfo);
				participantsService.setparticipants(tFTMatchInfo.getParticipantLists());
				matchesAndPuuidsService.setMatchesAndPuuids(matchId, summonerInfo.getPuuid());
			}
			
		}
		return true;
	}
	
}
