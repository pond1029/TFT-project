/**
  * @Package : com.jylee.tft.user.domain
  * @FileName : JoinForm.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.siteuser.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.user.domain
  * @FileName : JoinForm.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinForm {

	private String nickname;
	private String email;
	private String password;
	/**
	 * @param nickname
	 * @param email
	 * @param password
	 * @throws BindException 
	 */

	
}
