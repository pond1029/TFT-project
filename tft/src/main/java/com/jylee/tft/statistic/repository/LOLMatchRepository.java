/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLMatchRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.statistic.domain.LOLMatch;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLMatchRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface LOLMatchRepository extends JpaRepository<LOLMatch, Long>{

	public abstract LOLMatch save(LOLMatch lolMatch);
	
}
