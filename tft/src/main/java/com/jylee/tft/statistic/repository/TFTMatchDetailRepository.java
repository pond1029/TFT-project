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

import com.jylee.tft.statistic.domain.TFTMatchDetail;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : TFTparticipantsRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface TFTMatchDetailRepository extends JpaRepository<TFTMatchDetail, Long>{
	
	@EntityGraph(attributePaths = "match")
	@Query("SELECT t FROM TFTMatchDetail t WHERE t.puuid = ?1 AND t.match IN (SELECT m FROM TFTMatch m WHERE m.gameDatetime >= ?2 AND m.gameDatetime <= ?3)")
	public abstract List<TFTMatchDetail> findByPuuidAndBetweenGameDatetime(String puuid, Date st, Date end);

	public abstract TFTMatchDetail save(TFTMatchDetail tftMatchDetail);
	
	public abstract Page<TFTMatchDetail> findByPuuid(String puuid, Pageable pageable);
}
