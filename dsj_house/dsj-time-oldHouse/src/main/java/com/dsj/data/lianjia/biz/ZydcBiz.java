package com.dsj.data.lianjia.biz;

public interface ZydcBiz {

	void list();

	void jieXiUrl(int i, int x);
	
	boolean startJx(int i, int x) throws Exception;
	/*
	 *定时中原地产
	 */
	void listJob();
}
