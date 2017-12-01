package com.dsj.common.utils.zookeeper;


public interface Config {
	 
	 byte[] getConfig(String path) throws Exception;
}

