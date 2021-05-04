/**
  * @Package : com.jylee.tft.user.domain
  * @FileName : User.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.siteuser.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.user.domain
  * @FileName : User.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteUser {
	@Id @GeneratedValue
	private Long siteUserId;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false, length = 20)
	private String nickname;
	@Column(nullable = false)
	private String password;
	private boolean emailVerified;
	private String emailToken;
	private LocalDateTime joinDate;
	/**
	  * @Method Name : generateToken
	  * @Date : 2021. 1. 12.
	  * @Author : "LeeJaeYeon"
	  * @Version : 
	  * @Information :
	  */
	
	public void generateToken() {
		this.emailToken = UUID.randomUUID().toString();		
	}
	
	public void confirmUser() {
		this.setEmailVerified(true);
		this.setJoinDate(LocalDateTime.now());
		
	}
	
	public boolean isValidToken(String token) {
		return this.emailToken.equals(token);
	}
}
