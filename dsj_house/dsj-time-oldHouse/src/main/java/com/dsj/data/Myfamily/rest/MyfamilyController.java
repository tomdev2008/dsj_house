package com.dsj.data.Myfamily.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.data.Myfamily.biz.MyfamilyBiz;
@Controller
@RequestMapping("woaiwojia")
public class MyfamilyController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MyfamilyController.class);
    @Autowired
	private MyfamilyBiz myfamilyBiz;
	//二手房根据地区爬数据
		@RequestMapping("list")
		@ResponseBody
		public String dealErshoufang(){
			myfamilyBiz.jiexiLoupan(1);
			return "ok";
		}
    
	
}
