package com.jylee.tft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.TFTMatchInfo;
import com.jylee.tft.repository.MatchInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchInfoService {

	final private MatchInfoRepository matchInfoRepository;
	
	public TFTMatchInfo setMatchInfo(TFTMatchInfo tFTMatchInfo) {
		return matchInfoRepository.save(tFTMatchInfo);
	}
	
	public List<TFTMatchInfo> getMatchInfoListBetween(String from, String to){
		return matchInfoRepository.findAll();
	}
	
	public List<TFTMatchInfo> getMatchInfo(){
		return matchInfoRepository.findAll();
	}
}
