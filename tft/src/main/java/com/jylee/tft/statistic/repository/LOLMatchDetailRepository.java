/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jylee.tft.statistic.domain.LOLMatchDetail;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface LOLMatchDetailRepository extends JpaRepository<LOLMatchDetail, Long>{

	public abstract LOLMatchDetail save(LOLMatchDetail lolMatchDetail);
	
	@Query("SELECT p.game FROM LOLParticipant p WHERE p.accountId = ?1 AND p.game IN(SELECT m FROM LOLMatchDetail m WHERE m.gameCreation >= ?2 AND m.gameCreation <= ?3)")
	public abstract List<LOLMatchDetail> findByAccountIdAndBetweenGameCreation(String accountId, Date from, Date to);
	
}
