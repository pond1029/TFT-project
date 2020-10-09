package com.jylee.ftf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jylee.tft.dao.MatchInfo;
import com.jylee.tft.dao.MatchesAndPuuids;

public interface MatchInfoRepository  extends JpaRepository<MatchInfo,Long> {


	@Query("select m from MatchInfo m where m.gameDatetime > :gameDatetime")
	public List<MatchInfo> findByGameDatetime(Long gameDatetime);
	public List<MatchInfo> findAll();
	public MatchInfo save(MatchInfo matchInfo);
	
}