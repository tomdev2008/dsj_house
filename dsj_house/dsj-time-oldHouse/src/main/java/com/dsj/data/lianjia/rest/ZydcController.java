package com.dsj.data.lianjia.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.data.lianjia.biz.ZydcBiz;

@Controller
@RequestMapping("zydc")
public class ZydcController {
	
	@Autowired
	ZydcBiz zydcBiz;
	
	//根据页数
	@RequestMapping("runPage")
	@ResponseBody
	public void runPage(){
		zydcBiz.list();
	}
	
	
}
