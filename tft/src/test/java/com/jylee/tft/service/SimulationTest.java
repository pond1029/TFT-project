/**
  * @Package : com.jylee.tft.service
  * @FileName : SimulationTest.java
  * @Date : 2020. 12. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	void given_level2_When_reroll_Then_print() {
		//given
		Map userInfo = new HashMap();
		userInfo.put("level", 2);
		//레벨은 4이고, 선받자는 없다.
		//when
		String[] champs = testReroll(userInfo);
		//reroll
		//then
		log.info(champs[0]);
		log.info(champs[1]);
		log.info(champs[2]);
		log.info(champs[3]);
		log.info(champs[4]);
	}
	
	String[] testReroll(Map userInfo) {
		String[] shop = new String[5];
		int level = 2;
		for (int i = 0; i < shop.length; i++) {
			int randomNumber = testRandomPick(99);
			int pickedLevel = testGetLevel(level, randomNumber);
			String champ = testGetRandomChamp(pickedLevel);
			shop[i] = champ;
		}
		
		return shop;
	}
	
	String testGetRandomChamp(int level) {
		List<String> champLists = testGetChampList(level);
		int pickedChamp = testRandomPick(champLists.size());
		return champLists.get(pickedChamp);
	}
	
	List testGetChampList(int level) {
		List champLists = null;
		if(level == 1) {
			champLists = testChampionsLevel1();
		}
		if(level == 2) {
			champLists = testChampionsLevel2();
		}
		return champLists;
	}
	
	int testGetLevel(int level, int number) {
		int[] pickRate = testPickRate(level);
		int pickedLevel = -1;
		for (int i = 0; i < pickRate.length; i++) {
			if(number <= pickRate[i]) {
				pickedLevel = i;
				break;
			}
		}
		return pickedLevel + 1;
	}
	
	int[] testPickRate(int level) {
		int[] pickRange = new int[5];
		
		switch (level) {
		case 1:
			
			break;
		case 2:
			pickRange[0]=74;
			pickRange[1]=99;
			pickRange[2]=100;
			pickRange[3]=100;
			pickRange[4]=100;
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		case 7:
			
			break;
		case 8:
			
			break;
		case 9:
			
			break;
		}
		
		return pickRange;
		
	}
	
	int testRandomPick(int range) {
		Random random = new Random();
		return random.nextInt(range);		
	}
	
	List testChampionsLevel1() {
		List championsLists = new ArrayList();		
		championsLists.add("Diana");
		championsLists.add("Elise");
		championsLists.add("Fiora");
		championsLists.add("Garen");
		championsLists.add("Lissandra");
		championsLists.add("Maokai");
		championsLists.add("Nami");
		championsLists.add("Nidalee");
		championsLists.add("Sylas");
		championsLists.add("Tahm Kench");
		championsLists.add("Twisted Fate");
		championsLists.add("Vayne");
		championsLists.add("Yasuo");
		championsLists.add("Wukong");		
		return championsLists;
	}	

	List testChampionsLevel2() {
		List championsLists = new ArrayList();		
		championsLists.add("Annie");
		championsLists.add("Aphelios");
		championsLists.add("Hecarim");
		championsLists.add("Janna");
		championsLists.add("Jarvan IV");
		championsLists.add("Jax");
		championsLists.add("Lulu");
		championsLists.add("Pyke");
		championsLists.add("Teemo");
		championsLists.add("Thresh");
		championsLists.add("Vi");
		championsLists.add("Zed");	
		return championsLists;
	}
	
}
