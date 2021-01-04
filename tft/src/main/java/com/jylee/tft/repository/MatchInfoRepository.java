package com.jylee.tft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jylee.tft.dao.TFTMatchInfo;
import com.jylee.tft.dao.MatchesAndPuuids;

public interface MatchInfoRepository  extends JpaRepository<TFTMatchInfo,Long> {


	@Query("select m from MatchInfo m where m.gameDatetime > :gameDatetime")
	public List<TFTMatchInfo> findByGameDatetime(Long gameDatetime);
	public List<TFTMatchInfo> findAll();
	public TFTMatchInfo save(TFTMatchInfo tFTMatchInfo);
	
}