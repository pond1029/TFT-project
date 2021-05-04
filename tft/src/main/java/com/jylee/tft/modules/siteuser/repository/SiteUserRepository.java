/**
  * @Package : com.jylee.tft.user
  * @FileName : SiteUserRepository.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.siteuser.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.modules.siteuser.domain.SiteUser;

/**
  * @Package : com.jylee.tft.user
  * @FileName : SiteUserRepository.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface SiteUserRepository extends JpaRepository<SiteUser, Long>{

	SiteUser save(SiteUser siteUser);
	
	boolean existsByEmail(String eamil);
	
	boolean existsByNickname(String nickname);
	
	Optional<SiteUser> findByEmail(String email);
}
