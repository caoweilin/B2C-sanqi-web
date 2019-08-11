package com.sanqi.web.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
*@author作者weilin
*@version 创建时间:2019年5月2日下午1:25:26
*类说明
*/
@Service
public class IndexService {
	
	@Autowired
	private ApiService apiService;
	
	@Value("${MANAGE_SANQI_URL}")
	private String MANAGE_SANQI_URL;
	
	@Value("${SANQI_AD1}")
	private String SANQI_AD1;
	
	@Value("${SANQI_AD2}")
	private String SANQI_AD2;
	
	private static final ObjectMapper mapper = new ObjectMapper();

	public String queryIndexAD1() {
		try {
			String url = MANAGE_SANQI_URL+SANQI_AD1;
			String jsonData = this.apiService.doGet(url);
			if(null == jsonData) {
				return null;
			}
			
			JsonNode jsonNode = mapper.readTree(jsonData);
			
			ArrayNode rows = (ArrayNode) jsonNode.get("rows");
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (JsonNode row : rows) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("srcB", rows.get("pic").asText());
				map.put("height", 240);
				map.put("alt", row.get("title").asText());
				map.put("width", 670);
				map.put("src", rows.get("pic").asText());
				map.put("widthB", 550);
				map.put("href", rows.get("url").asText());
				map.put("hegihtB", 240);
				result.add(map);
			}
			return mapper.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String queryIndexAD2() {
		try {
			String url = MANAGE_SANQI_URL+SANQI_AD2;
			String jsonData = this.apiService.doGet(url);
			if(null == jsonData) {
				return null;
			}
			
			JsonNode jsonNode = mapper.readTree(jsonData);
			
			ArrayNode rows = (ArrayNode) jsonNode.get("rows");
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (JsonNode row : rows) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("width", 310);
				map.put("height", 70);
				map.put("src", rows.get("pic").asText());
				map.put("href", rows.get("url").asText());
				map.put("alt", row.get("title").asText());
				map.put("hegihtB", 210);
				map.put("widthB", 70);
				map.put("srcB", rows.get("pic").asText());
				result.add(map);
			}
			return mapper.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
