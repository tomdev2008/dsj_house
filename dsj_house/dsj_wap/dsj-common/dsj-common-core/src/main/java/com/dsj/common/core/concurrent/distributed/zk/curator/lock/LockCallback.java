package com.dsj.common.core.concurrent.distributed.zk.curator.lock;

public interface LockCallback {

	  String  lockPath();
	  
	  void doWork();
	  
}
