/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : SummonerRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.modules.account.domain.AccountType;
import com.jylee.tft.modules.account.domain.Summoner;

/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : SummonerRepository.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface SummonerRepository extends JpaRepository<Summoner, Long>{
	
	public abstract Summoner save(Summoner summoner);
	
	public abstract Optional<Summoner> findByNameAndType(String name,AccountType type);
	
}
