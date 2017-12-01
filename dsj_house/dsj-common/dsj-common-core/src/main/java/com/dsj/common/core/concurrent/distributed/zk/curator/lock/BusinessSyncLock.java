package com.dsj.common.core.concurrent.distributed.zk.curator.lock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.NetUtils;

/**
 * 业务数据同步锁
 */
///@Component
public class BusinessSyncLock {
	
	private final Logger logger = LoggerFactory.getLogger(BusinessSyncLock.class);
	
	/**
	 * 共享锁，同步处理业务
	 */
	public void sync(LockCallback lockCallback){
		 CuratorFramework        curatorFramework =null;
		 InterProcessMutex lock = null;
	     try{
	    	 curatorFramework =CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
	    	 curatorFramework.start();
	    	 lock = new InterProcessMutex(curatorFramework, lockCallback.lockPath());
			 if ( !lock.acquire(60, TimeUnit.SECONDS) ){
		            throw new IllegalStateException(NetUtils.getLocalAddress().getHostAddress() + " could not acquire the lock");
		     }
	    	 logger.info(NetUtils.getLocalAddress().getHostAddress() + " has the lock");
	    	 
	         lockCallback.doWork();
	    	 
	     }catch(Exception e){
	    	 logger.error(e.getMessage(),e);
	     }finally{
	    	 // always release the lock in a finally block
	         try {
	        	if(lock!=null&&lock.isAcquiredInThisProcess()){//判断是否持有锁 进而进行锁是否释放的操作  
	        		lock.release();
	        		logger.info(NetUtils.getLocalAddress().getHostAddress() + " releasing the lock");
	        	}
			 } catch (Exception e) {
				 logger.error(e.getMessage(),e);
			 } 
	         CloseableUtils.closeQuietly(curatorFramework);
	     }
	}

}
