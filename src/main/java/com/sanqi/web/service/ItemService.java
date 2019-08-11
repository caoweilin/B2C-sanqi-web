package com.sanqi.web.service;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanqi.common.bean.ItemDesc;
import com.sanqi.common.service.RedisService;
import com.sanqi.web.bean.Item;

@Service
public class ItemService {

	@Autowired
	private ApiService apiService;
	
	@Autowired
	private RedisService redisService;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static final String REDIS_KEY = "SANQI_WEB_ITEM_DETAIL";
	
	private static final Integer REDIS_TIME = 60 * 60 *3;
	
	@Value("${MANAGE_SANQI_URL}")
	private String MANAGE_SANQI_URL;
	
	public Item queryById(Long itemId) {
		
		String key = REDIS_KEY+itemId;
		try {
			String cacheData = this.redisService.get(key);
			if(StringUtils.isNotEmpty(cacheData)) {
				return mapper.readValue(cacheData, Item.class);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		try {
			String url = MANAGE_SANQI_URL + "/rest/api/item/" +itemId;
			String jsonData = this.apiService.doGet(url);
			
			try {
				this.redisService.set(key, jsonData,REDIS_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mapper.readValue(jsonData, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ItemDesc queryItemDescById(Long itemId) {
		try {
			String url = MANAGE_SANQI_URL + "/rest/api/item/desc" +itemId;
			String jsonData = this.apiService.doGet(url);
			return mapper.readValue(jsonData, ItemDesc.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
