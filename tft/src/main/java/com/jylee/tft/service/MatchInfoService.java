package com.jylee.tft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.repository.MatchInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchInfoService {

	final private MatchInfoRepository matchInfoRepository;
	
	public MatchInfo setMatchInfo(MatchInfo matchInfo) {
		return matchInfoRepository.save(matchInfo);
	}
	
	public List<MatchInfo> getMatchInfoListBetween(String from, String to){
		return matchInfoRepository.findAll();
	}
	
	public List<MatchInfo> getMatchInfo(){
		return matchInfoRepository.findAll();
	}
}
