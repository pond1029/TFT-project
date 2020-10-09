package com.jylee.tft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jylee.ftf.repository.MatchInfoRepository;
import com.jylee.tft.dao.MatchInfo;

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
