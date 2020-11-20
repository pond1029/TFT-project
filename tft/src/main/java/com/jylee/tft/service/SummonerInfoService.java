package com.jylee.tft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jylee.tft.dao.SummonerInfo;
import com.jylee.tft.repository.SummonerInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SummonerInfoService {

	@Autowired
	ApiManager apiManager;

	final private SummonerInfoRepository summonerInfoRepository;

	public SummonerInfo getSummonerInfo(String summonerName) {
		SummonerInfo summonerInfo = summonerInfoRepository.findByName(summonerName);
		if (summonerInfo == null) {
			summonerInfo = apiManager.retrieveSummonerInfo(summonerName);
			summonerInfo = summonerInfoRepository.save(summonerInfo);
		}

		return summonerInfo;
	}
}
