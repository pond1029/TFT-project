/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : TFTparticipantsRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jylee.tft.statistic.domain.TFTParticipant;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : TFTparticipantsRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface TFTParticipantRepository extends JpaRepository<TFTParticipant, Long>{
	
	@EntityGraph(attributePaths = "match")
	@Query("SELECT p FROM TFTParticipant p WHERE p.puuid = ?1 AND p.match IN (SELECT m FROM TFTMatch m WHERE m.gameDatetime >= ?2 AND m.gameDatetime <= ?3)")
	public abstract List<TFTParticipant> findByPuuidAndBetweenGameDatetime(String puuid, Date from, Date to);

	public abstract TFTParticipant save(TFTParticipant tftParticipant);
	
	public abstract Page<TFTParticipant> findByPuuid(String puuid, Pageable pageable);
}
