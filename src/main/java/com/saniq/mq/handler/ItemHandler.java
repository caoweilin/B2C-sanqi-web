package com.saniq.mq.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanqi.common.service.RedisService;
import com.sanqi.web.service.ItemService;

/**
*@author作者weilin
*@version 创建时间:2019年7月9日下午7:26:11
*类说明
*/
public class ItemHandler {
	
	@Autowired
	private RedisService redisService;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public void execute(String msg) {
		try {
			JsonNode jsonNode = mapper.readTree(msg);
			Long itemId = jsonNode.get("itemId").asLong();
			String key = ItemService.REDIS_KEY + itemId;
			this.redisService.del(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
