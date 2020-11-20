package com.jylee.tft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.dao.SummonerInfo;

public interface SummonerInfoRepository  extends JpaRepository<SummonerInfo,Long> {

	public SummonerInfo findByName(String name);
	public SummonerInfo save(SummonerInfo summonerInfo);
}