/**
  * @Package : com.jylee.tft.service.simulation
  * @FileName : ReRoll.java
  * @Date : 2020. 12. 9. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.simulation.service;

import java.util.List;
import java.util.Random;

/**
  * @Package : com.jylee.tft.service.simulation
  * @FileName : ReRoll.java
  * @Date : 2020. 12. 9. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public class ReRoll {

	private ResourceManager resourceManager;	
	private int userLevel;
	private boolean hasChoice;
	
	/**
	 * @param userLevel
	 * @param hasChoice
	 */
	public ReRoll(int userLevel, boolean hasChoice) {
		this.userLevel = userLevel;
		this.hasChoice = hasChoice;
		this.resourceManager = new ResourceManager();
	}

	
	public String[] roll() {
		String[] shop = new String[5];
		
		for (int i = 0; i < shop.length; i++) {
			int pickedLevel = levelPick();
			shop[i] = randomChampIn(pickedLevel);
		}
		
		return shop;
	}

	private int levelPick() {		
		int randomNumber = randomPick(99);
		List plate = resourceManager.getReRollPercent();
		return (int) plate.get(randomNumber);
	}
	
	private int randomPick(int range) {
		Random random = new Random();
		return random.nextInt(range);		
	}
	
	private String randomChampIn(int level) {
		List<String> champLists = resourceManager.getChampionList();
		int pickedChamp = randomPick(champLists.size());
		return champLists.get(pickedChamp);
	}
		
	
}
