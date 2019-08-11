package com.sanqi.web.bean;

import org.apache.commons.lang3.StringUtils;

/**
*@author作者weilin
*@version 创建时间:2019年5月3日上午12:13:01
*类说明
*/
public class Item extends com.sanqi.common.bean.Item{
	
	public String[] getImages() {
		return StringUtils.split(super.getImage(),',');
	}

}
