/**
  * @Package : com.jylee.tft.service
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.service;

import org.junit.jupiter.api.Test;

import com.jylee.tft.statistic.Figure;
import com.jylee.tft.statistic.Period;
import com.jylee.tft.statistic.TFTFigure;

/**
  * @Package : com.jylee.tft.service
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

public class StatisticsTest {

	@Test
	public void figureTest() {
		Figure f = new TFTFigure();
		
		f.setFigure("2020-01-01", 1L);
		f.setFigure("2020-01-02", 1L);
		f.setFigure("2020-01-03", 1L);
		f.setFigure("2020-01-09", 1L);
		f.setFigure("2020-01-05", 1L);
		f.setFigure("2020-01-06", 1L);
		f.setFigure("2020-01-06", 1L);
		
		String[] labels = f.gatLabel();
		Object[] datas = f.getData();
		
		for (String label : labels) {
			System.out.println(label);
		}
		
		for (Object object : datas) {
			System.out.println(object.toString());
		}
	}
	
	@Test
	public void periodTest() {
		Period p = new Period(2021,1);
		String[] d = p.splitByDay();
		
		for (String string : d) {
			System.out.println(string);
		}
	}
}
