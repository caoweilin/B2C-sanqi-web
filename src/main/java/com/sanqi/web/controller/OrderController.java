package com.sanqi.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sanqi.web.bean.Item;
import com.sanqi.web.service.ItemService;

/**
*@author作者weilin
*@version 创建时间:2019年7月2日下午7:42:25
*类说明
*/
@RequestMapping("order")
@Controller
public class OrderController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="{itemId}",method = RequestMethod.GET)
	public ModelAndView toOrder(@PathVariable("itemId")Long itemId) {
		ModelAndView mv = new ModelAndView("order");
		Item item = itemService.queryById(itemId);
		mv.addObject("item",item);
		return mv;
	}
	
	@RequestMapping(value="submit",method = RequestMethod.POST)
	public Map<String, Object> submitOrder(){
		
	}
	
	
}
