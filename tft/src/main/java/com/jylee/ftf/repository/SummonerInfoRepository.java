package com.jylee.ftf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.dao.SummonerInfo;

public interface SummonerInfoRepository  extends JpaRepository<SummonerInfo,Long> {

}