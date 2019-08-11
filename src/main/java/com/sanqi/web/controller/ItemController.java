package com.sanqi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sanqi.common.bean.ItemDesc;
import com.sanqi.web.bean.Item;
import com.sanqi.web.service.ItemService;

/**
*@author作者weilin
*@version 创建时间:2019年5月2日下午11:05:13
*类说明
*/
@RequestMapping("item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="{itemId}",method=RequestMethod.GET)
	public ModelAndView showDetail(@PathVariable("itemId")Long itemId) {
		ModelAndView mv = new ModelAndView("item");
		//商品数据
		Item item = this.itemService.queryById(itemId);
		mv.addObject("item",item);
		//商品描述数据
		ItemDesc itemDesc = this.itemService.queryItemDescById(itemId);
		mv.addObject("itemDesc",itemDesc);
		return mv;
	}
	
}
