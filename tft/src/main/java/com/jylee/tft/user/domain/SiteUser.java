/**
  * @Package : com.jylee.tft.user.domain
  * @FileName : User.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.user.domain;

import java.time.LocalDateTime;
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
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String nickname;
	private String password;
	private boolean emailVerified;
	private String emailToken;
	private LocalDateTime joinDate;
}
