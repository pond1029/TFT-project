/**
  * @Package : com.jylee.tft.service
  * @FileName : SimulationTest.java
  * @Date : 2020. 12. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : SimulationTest.java
  * @Date : 2020. 12. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
@ExtendWith(MockitoExtension.class)
public class SimulationTest {
	
	@Test
	@DisplayName("사용자가 리롤을 함")
	void Given_level1_When_reroll_Then_print() {
		//given
		Map userInfo = new HashMap();
		userInfo.put("level", 1);
		//레벨은 4이고, 선받자는 없다.
		//when
		testReroll(userInfo);
		//reroll
		//then
		log.info("reroll");		
	}
	
	private Map testReroll(Map userInfo) {
		Map result = new HashMap();
		
		return null;
	}
	
	private Map testChampionsLevel1() {
		Map champions = new HashMap();
		champions.put("name","Diana");
		champions.put("cost",1);
		champions.put("name","Elise");
		champions.put("cost",1);
		champions.put("name","Fiora");
		champions.put("cost",1);
		champions.put("name","Garen");
		champions.put("cost",1);
		champions.put("name","Lissandra");
		champions.put("cost",1);
		champions.put("name","Maokai");
		champions.put("cost",1);
		champions.put("name","Nami");
		champions.put("cost",1);
		champions.put("name","Nidalee");
		champions.put("cost",1);
		champions.put("name","Sylas");
		champions.put("cost",1);
		champions.put("name","Tahm Kench");
		champions.put("cost",1);
		champions.put("name","Twisted Fate");
		champions.put("cost",1);
		champions.put("name","Vayne");
		champions.put("cost",1);
		champions.put("name","Yasuo");
		champions.put("cost",1);
		champions.put("name","Wukong");
		champions.put("cost",1);
		return null;
	}
}
