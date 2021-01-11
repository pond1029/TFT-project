/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLMatchDetail.java
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jylee.tft.statistic.domain.LOLMatch;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface LOLMatchRepository extends JpaRepository<LOLMatch, Long>{

	public abstract LOLMatch save(LOLMatch lolMatch);
		
	@Query("SELECT p.game FROM LOLParticipant p WHERE p.accountId = :accountId AND p.game IN(SELECT m FROM LOLMatch m WHERE m.gameCreation >= :from AND m.gameCreation <= :to)")
	public abstract Optional<List<LOLMatch>> findByAccountIdAndBetweenGameCreation(@Param("accountId") String accountId, @Param("from")LocalDateTime from, @Param("to")LocalDateTime to);
	
	public abstract Optional<LOLMatch> findByGameId(Long gameId);
	
	@Query("SELECT m FROM LOLMatch m LEFT JOIN m.participants LOLParticipant WHERE LOLParticipant.accountId = :accountId ORDER BY m.gameCreation")
	public abstract Page<LOLMatch> findRecent(@Param("accountId") String accountId, Pageable pageable);
	
}
