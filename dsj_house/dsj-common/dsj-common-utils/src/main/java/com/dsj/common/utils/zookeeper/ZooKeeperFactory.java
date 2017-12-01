package com.dsj.common.utils.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZooKeeperFactory {
	public static final String CONNECT_STRING = "127.0.0.1:2181";
    
    public static final int MAX_RETRIES = 3;
 
    public static final int BASE_SLEEP_TIMEMS = 3000;
 
    public static final String NAME_SPACE = "cfg";
 
    public static CuratorFramework get() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIMEMS, MAX_RETRIES);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STRING)
                .retryPolicy(retryPolicy)
                .namespace(NAME_SPACE)
                .build();
        client.start();
        return client;
    }
}
