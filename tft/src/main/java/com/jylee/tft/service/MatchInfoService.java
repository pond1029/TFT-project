package com.jylee.tft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.repository.MatchInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchInfoService {

	private final MatchInfoRepository matchInfoRepository;
	
	public MatchInfo setMatchInfo(MatchInfo matchInfo) {
		return matchInfoRepository.save(matchInfo);
	}
	
	public List<MatchInfo> getMatchInfo(){
		return matchInfoRepository.findAll();
	}
}
