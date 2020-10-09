package com.jylee.tft.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Json 유틸
 * 
 * @author 김종현
 *
 */
public class JsonUtil {
	
	/**
	 * Json -> Map
	 * 
	 * @return
	 */
	public static Map<String, Object> convertMap(String jsonString) {
		
		JsonObject object = new JsonParser().parse(jsonString).getAsJsonObject();
		JsonObject map = object.getAsJsonObject().getAsJsonObject("data");

		Gson gson = new Gson();
		return gson.fromJson(map, Map.class);
	}
	
	/**
	 * Json -> Map
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> convertFeatureMap(String jsonString) {
		
		JsonObject object = new JsonParser().parse(jsonString).getAsJsonObject();
		
		JsonArray jsonArray = object.getAsJsonObject().get("features").getAsJsonArray();
		
		Gson gson = new Gson();
		return gson.fromJson(jsonArray.toString(), List.class);
	}
}
