/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTFigure.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : TFTFigure.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : TFT 산출 데이터, 순서 유지
  */

@NoArgsConstructor
public class TFTFigure implements Figure{

	private Map<String, Long> figures = new HashMap<String, Long>();

	@Override
	public String[] gatLabel() {
		return figures.keySet().stream().sorted().toArray(String[]::new);
	}
	
	@Override
	public Long[] getData() {
		String[] keyArray = this.gatLabel();
		Long[] dataArray = new Long[keyArray.length];
		
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = figures.get(keyArray[i]);
		}
		
		return dataArray;
	}
	
	@Override
	public void setFigure(String label, Long data) {
		this.figures.put(label, figures.getOrDefault(label, 0L) + data);
	}
}
