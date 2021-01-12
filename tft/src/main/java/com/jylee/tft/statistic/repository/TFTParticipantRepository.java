/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : TFTparticipantsRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	@Query("SELECT p FROM TFTParticipant p WHERE p.puuid = :puuid AND p.match IN (SELECT m FROM TFTMatch m WHERE m.gameDatetime >= :from AND m.gameDatetime <= :to)")
	public abstract Optional<List<TFTParticipant>> findByPuuidAndBetweenGameDatetime(@Param("puuid")String puuid, @Param("from")LocalDateTime from, @Param("to")LocalDateTime to);

	public abstract TFTParticipant save(TFTParticipant tftParticipant);
	
	public abstract Page<TFTParticipant> findByPuuid(String puuid, Pageable pageable);
	
}
