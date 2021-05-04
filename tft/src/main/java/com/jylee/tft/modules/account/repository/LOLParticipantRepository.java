/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLParticipantRepository.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.modules.account.domain.LOLParticipant;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLParticipantRepository.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface LOLParticipantRepository extends JpaRepository<LOLParticipant, Long>{

	public abstract LOLParticipant save(LOLParticipant lolParticipant); 
}
