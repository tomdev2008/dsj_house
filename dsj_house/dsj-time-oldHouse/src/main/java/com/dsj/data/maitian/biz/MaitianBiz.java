package com.dsj.data.maitian.biz;

public interface MaitianBiz {
	
	//根据页数
	public void runPage(Integer s,Integer e);
	
	//根据二手房id
	public void runEsfId(String esfId);
	
	//下载二手房图片，上传，并删除
	public void downUpDelEsfImg();
	
	//下载小区图片，上传，并删除
	public void downUpDelXqImg();
	
	/**
	 * 定时麦田
	 */
	public void runPageJob();
	
	
}
