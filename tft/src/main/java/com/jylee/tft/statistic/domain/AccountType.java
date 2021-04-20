/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : RiotType.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : RiotType.java
  * @Date : 2021. 1. 6. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public enum AccountType {

	LOL("League of Legends"), 
	TFT("Teamfight Tactics");
	
	private final String fullName;
	
	private AccountType(String fullName){
		this.fullName= fullName;
	}
	
	public String getFullName(){
		return this.fullName;
	}
	
}
