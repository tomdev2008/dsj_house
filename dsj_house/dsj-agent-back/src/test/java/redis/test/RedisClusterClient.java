package redis.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.junit.Test;

import com.dsj.common.utils.redis.serialize.SerializingUtil;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClusterClient {
	private String serverInfo = "211.159.146.93:16003,211.159.146.93:16002,211.159.146.93:16001,211.159.146.93:16004,211.159.146.93:16005,211.159.146.93:16006";  
	  
    private Set<HostAndPort> getClusterInfo(String serverInfo) {  
        Set<HostAndPort> set = new HashSet<HostAndPort>();  
        if(serverInfo==null||"".equals(serverInfo.length())) {  
            throw new RuntimeException("The serverInfo can not be empty");  
        }  
        String ipPort[] = serverInfo.split(",");  
        int len = ipPort.length;  
        for(int i=0;i<len;i++) {  
            String server[] = ipPort[i].split(":");  
            set.add(new HostAndPort(server[0], Integer.parseInt(server[1])));  
        }  
        return set;  
    }  
      
    @Test  
    public void test() {  
    	/*JedisCluster(HostAndPort node, int connectionTimeout, int soTimeout,
                int maxAttempts, String password, final GenericObjectPoolConfig poolConfig) */
        Set<HostAndPort> jedisClusterNodes = getClusterInfo(serverInfo);  
        // Jedis Cluster will attempt to discover cluster nodes automatically 
       GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
        
        JedisCluster jc = new JedisCluster(jedisClusterNodes,1000,6,poolConfig);
        byte[] b= {-84, -19, 0, 5, 115, 114, 0, 50, 111, 114, 103, 46, 97, 112, 97, 99, 104, 101, 46, 115, 104, 105, 114, 111, 46, 115, 117, 98, 106, 101, 99, 116, 46, 83, 105, 109, 112, 108, 101, 80, 114, 105, 110, 99, 105, 112, 97, 108, 67, 111, 108, 108, 101, 99, 116, 105, 111, 110, -88, 127, 88, 37, -58, -93, 8, 74, 3, 0, 1, 76, 0, 15, 114, 101, 97, 108, 109, 80, 114, 105, 110, 99, 105, 112, 97, 108, 115, 116, 0, 15, 76, 106, 97, 118, 97, 47, 117, 116, 105, 108, 47, 77, 97, 112, 59, 120, 112, 115, 114, 0, 23, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 76, 105, 110, 107, 101, 100, 72, 97, 115, 104, 77, 97, 112, 52, -64, 78, 92, 16, 108, -64, -5, 2, 0, 1, 90, 0, 11, 97, 99, 99, 101, 115, 115, 79, 114, 100, 101, 114, 120, 114, 0, 17, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 72, 97, 115, 104, 77, 97, 112, 5, 7, -38, -63, -61, 22, 96, -47, 3, 0, 2, 70, 0, 10, 108, 111, 97, 100, 70, 97, 99, 116, 111, 114, 73, 0, 9, 116, 104, 114, 101, 115, 104, 111, 108, 100, 120, 112, 63, 64, 0, 0, 0, 0, 0, 12, 119, 8, 0, 0, 0, 16, 0, 0, 0, 1, 116, 0, 34, 99, 111, 109, 46, 100, 115, 106, 46, 100, 97, 116, 97, 46, 115, 104, 105, 114, 111, 46, 114, 101, 97, 108, 109, 46, 77, 121, 82, 101, 97, 108, 109, 95, 48, 115, 114, 0, 23, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 76, 105, 110, 107, 101, 100, 72, 97, 115, 104, 83, 101, 116, -40, 108, -41, 90, -107, -35, 42, 30, 2, 0, 0, 120, 114, 0, 17, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 72, 97, 115, 104, 83, 101, 116, -70, 68, -123, -107, -106, -72, -73, 52, 3, 0, 0, 120, 112, 119, 12, 0, 0, 0, 16, 63, 64, 0, 0, 0, 0, 0, 1, 116, 0, 5, 97, 100, 109, 105, 110, 120, 120, 0, 119, 1, 1, 113, 0, 126, 0, 5, 120};
        // System.out.println(jc.del(b));
        
         System.out.println(jc.hkeys(SerializingUtil.serialize("shiro_redis_session:")));
      /*  System.out.println((AuthorizationInfo)(SerializingUtil.deserialize(jc.get(b))));
      SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
      info.addStringPermissions(new ArrayList<>());
      jc.set(b, SerializingUtil.serialize(info));
      System.out.println((AuthorizationInfo)(SerializingUtil.deserialize(jc.get(b))));*/
      
     ///  JedisCluster jc = new JedisCluster(jedisClusterNodes);
      //  System.out.println(SerializingUtil.deserialize(jc.get(SerializingUtil.serialize(JedisConstant.DIC_TYPE))));
    }  
}
