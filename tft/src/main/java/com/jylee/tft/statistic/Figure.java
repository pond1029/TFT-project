/**
  * @Package : com.jylee.tft.statistic
  * @FileName : Graph.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

/**
  * @Package : com.jylee.tft.statistic
  * @FileName : Graph.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public interface Figure {

	public String[] gatLabel();
	public Long[] getData();
	public void setFigure(String label, Long data);
	
}
