package com.jylee.tft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.dao.MatchesAndPuuids;

public interface MatchesAndPuuidsRepository  extends JpaRepository<MatchesAndPuuids,Long> {

	public List<MatchesAndPuuids> findAllByPuuid(String puuid);
	public List<MatchesAndPuuids> findAllByMatchId(String matchId);
	public MatchesAndPuuids findByMatchIdAndPuuid(String matchId, String puuid);
	
}