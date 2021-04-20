/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : Account.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForm {

	private AccountType type;
	private String accountId;
	private Period period;
}
