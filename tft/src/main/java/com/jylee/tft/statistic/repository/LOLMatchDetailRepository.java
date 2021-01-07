/**
  * @Package : com.jylee.tft.statistic.repository
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

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
	
}
