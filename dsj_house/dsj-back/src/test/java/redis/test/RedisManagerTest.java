package redis.test;

import org.junit.Test;

import com.dsj.common.utils.redis.serialize.SerializingUtil;
import com.dsj.data.shiro.RedisManager;

public class RedisManagerTest {
	
	@Test
	public void testSet(){
		RedisManager redisManager  = new RedisManager();
	byte[] b={1};
	redisManager.set(b, SerializingUtil.serialize(b));
	System.out.println(redisManager.get(b));
	}
}
