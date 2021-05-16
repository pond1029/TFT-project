/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitorImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jylee.tft.modules.account.domain.LOLAccount;
import com.jylee.tft.modules.account.domain.LOLMatch;
import com.jylee.tft.modules.account.domain.PlayTime;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.domain.TFTAccount;
import com.jylee.tft.modules.account.domain.TFTParticipant;
import com.jylee.tft.modules.account.repository.LOLMatchRepository;
import com.jylee.tft.modules.account.repository.TFTParticipantRepository;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : AccountVisitorImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
@RequiredArgsConstructor
public class AccountVisitorImpl implements AccountVisitor{

	private final TFTDataManager tftDataManager;
	private final LOLDataManager lolDataManager;
	private final  SummonerImpl summonerService;	
	private final LOLMatchRepository lolMatch;
	private final  TFTParticipantRepository tftParticipant;
	
	@Override
	public List<PlayTime> getPlayTimes(TFTAccount account, LocalDateTime from, LocalDateTime to) {
		List<PlayTime> playTimes = new ArrayList<>();			
		Summoner summoner = summonerService.getSummoner(account);
		if(summoner == null) return playTimes;
		
		Optional<List<TFTParticipant>> participants = 
				tftParticipant.findByPuuidAndBetweenGameDatetime(summoner.getPuuid(), from, to);

		if(participants.isEmpty()) return playTimes;
		
		for(TFTParticipant participant : participants.get()) {		
			playTimes.add(new PlayTime(participant.getMatch().getGameDatetime().toLocalDate(), participant.getTimeEliminated()));
		}
		
		return playTimes;
	}

	@Override
	public void update(TFTAccount account){
		try {
			tftDataManager.update(account);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<PlayTime> getPlayTimes(LOLAccount account, LocalDateTime from, LocalDateTime to) {
		List<PlayTime> playTimes = new ArrayList<>();		
		Summoner summoner = summonerService.getSummoner(account);
		if(summoner == null) return playTimes;
		
		Optional<List<LOLMatch>> matches = 
				lolMatch.findByAccountIdAndBetweenGameCreation(summoner.getAccountId(), from, to);

		if(matches.isEmpty()) return playTimes;
		
		for(LOLMatch detail : matches.get()) {		
			playTimes.add(new PlayTime(detail.getGameCreation().toLocalDate(), detail.getGameDuration()));
		}
		
		return playTimes;
	}


	@Override
	public void update(LOLAccount account) {
		try {
			lolDataManager.update(account);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
