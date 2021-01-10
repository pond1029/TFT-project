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
	
	@Query("SELECT p.game FROM LOLParticipant p WHERE p.accountId = ?1 AND p.game IN(SELECT m FROM LOLMatch m WHERE m.gameCreation >= ?2 AND m.gameCreation <= ?3)")
	public abstract Optional<List<LOLMatch>> findByAccountIdAndBetweenGameCreation(String accountId, LocalDateTime from, LocalDateTime to);
	
	public abstract Optional<LOLMatch> findByGameId(Long gameId);
	
	public abstract Page<LOLMatch> findDescgameCreation(Pageable pageable);
	
	public abstract List<LOLMatch> saveAll(List<LOLMatch> lolMatches);
}
