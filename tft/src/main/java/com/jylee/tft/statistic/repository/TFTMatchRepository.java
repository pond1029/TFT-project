/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : TFTMatchRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jylee.tft.statistic.domain.TFTMatch;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : TFTMatchRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface TFTMatchRepository extends JpaRepository<TFTMatch, Long>{

	@EntityGraph(attributePaths = "participants")
	public abstract Page<TFTMatch> findByMatchId(String matchId, Pageable pageable);
		
	public abstract TFTMatch save(TFTMatch tftMatch);
	
	@Query("SELECT m FROM TFTMatch m LEFT JOIN m.participants TFTParticipant WHERE TFTParticipant.puuid = :puuid ORDER BY m.gameDatetime DESC")
	public abstract Page<TFTMatch> findRecent(@Param("puuid")String puuid,Pageable pageable);

}
