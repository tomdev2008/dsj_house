package com.dsj.data.maitian.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.data.maitian.biz.MaitianBiz;

@Controller
@RequestMapping("maitian")
public class MaitianController {
	
	@Autowired
	MaitianBiz maitianBiz;
	
	//根据页数
	@RequestMapping("runPage")
	@ResponseBody
	public void runPage(){
		maitianBiz.runPage(1, null);
	}
	
	//根据二手房id
	@RequestMapping("runEsfId")
	@ResponseBody
	public void runEsfId(String esfId){
		maitianBiz.runEsfId(esfId);
	}
	
	//下载二手房图片，上传，并删除
	@RequestMapping("downUpDelEsfImg")
	@ResponseBody
	public void downUpDelEsfImg(){
		maitianBiz.downUpDelEsfImg();
	}
	
	//下载小区图片，上传，并删除
	@RequestMapping("downUpDelXqImg")
	@ResponseBody
	public void downUpDelXqImg(){
		maitianBiz.downUpDelXqImg();
	}
	
}
