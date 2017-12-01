package com.dsj.common.utils.redis;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.dsj.common.utils.redis.constant.JedisConstant;
import com.dsj.common.utils.redis.serialize.SerializingUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**   
 * @作用：Jedis操作类
 * @功能：
 * @作者: wyt
 * @日期：2016-8-8 下午2:40:46 
 * @版本：V1.0   
 */
public class JedisProxy {
	private static Logger logger = Logger.getLogger(JedisProxy.class);
	private static JedisCluster jedisCluster;
	/**
	 * 
	 * @功能 获得jedis
	 * @作者: wyt
	 * 可使用********************
	 * @Autowired
	 * private JedisCluster jedis;
	 * 获得****************************
	 * @参数： @return
	 * @返回值：JedisCluster
	 * @日期: 2016-8-15 下午2:21:07
	 */
	 
	public static JedisCluster getJedis() {
		return jedisCluster;
	}
	/**
	 * 
	 * @功能：关闭
	 * @作者: wyt
	 * @参数： @param jedis
	 * @参数： @throws IOException
	 * @返回值：void
	 * @日期: 2016-8-8 下午2:42:27
	 */
	public static void returnJC(JedisCluster jedis) {
		/*if (null != jedis) {
			try {
				jedis.close();
			} catch (IOException e) {
				logger.error("redis close error:",e);
			}
		}*/
	}
	/**
	 * 
	 * @功能：设置默认过期时间的key
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param value
	 * @参数： @return
	 * @返回值：boolean
	 * @日期: 2016-8-8 下午6:31:21
	 */
	public static boolean setex(String key, Object value) {
		return setex(key, JedisConstant.EXPIRE, value);
	}
	/**
	 * 
	 * @功能：设置过期时间的key
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param value
	 * @参数： @return
	 * @返回值：boolean
	 * @日期: 2016-8-8 下午6:31:21
	 */
	public static boolean setex(String key, int seconds, Object value) {
		boolean result = false;
		
		if (value==null) {
			return result;
		}
		JedisCluster jedis = getJedis();

		if (value instanceof String) {
			result = (jedis.setex(key, seconds, "" + value) == "OK") ? true
					: false;
		} else {
			result = (jedis.setex(key.getBytes(), seconds,
					SerializingUtil.serialize(value)) == "OK") ? true : false;
		}
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：存入key,存在，则覆盖
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param value
	 * @参数： @return
	 * @返回值：boolean
	 * @日期: 2016-8-8 下午6:31:21
	 */
	public static boolean set(String key, Object value) {
		boolean result = false;
		
		if (value==null) {
			return result;
		}
		JedisCluster jedis = getJedis();

		result = ("OK".equals(jedis.set(key.getBytes(),
				SerializingUtil.serialize(value)))) ? true : false;
		returnJC(jedis);
		return result;
	}
	
	
	/**
	 * 
	 * @功能：存入key,存在，则覆盖
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param value
	 * @参数： @return
	 * @返回值：boolean
	 * @日期: 2016-8-8 下午6:31:21
	 */
	public static boolean setByte( byte[] key, Object value) {
		boolean result = false;
		
		if (value==null||key==null) {
			return result;
		}
		JedisCluster jedis = getJedis();
		result = (jedis.set(key,
					SerializingUtil.serialize(value)) == "OK") ? true : false;
		
		return result;
	}
	
	public static boolean setByte( byte[] key, byte[] value) {
		boolean result = false;
		
		if (value==null||key==null) {
			return result;
		}
		JedisCluster jedis = getJedis();
		
	
		result = (jedis.set(key,
					value) == "OK") ? true : false;
		
		return result;
	}
	
	/**
	 * 
	 * @功能：删除指定的key,也可以传入一个包含key的数组
	 * @作者: wyt
	 * @参数： @param keys
	 * @参数： @return 返回删除成功的个数 
	 * @返回值：Long
	 * @日期: 2016-8-15 下午2:50:02
	 */
	public static Long del(String...keys){
		JedisCluster jedis = getJedis();

		Long result = jedis.del(keys);
		
		returnJC(jedis);
		return result;
	}
	
	public static boolean del(String keys){
		JedisCluster jedis = getJedis();

		Long result = jedis.del(keys);
		
		return result > 0 ? true:false;
	}
	/**
	 * 
	 * @功能：判断key是否存在
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return
	 * @返回值：Boolean
	 * @日期: 2016-8-15 下午2:52:40
	 */
	public static boolean exists(String key){
		JedisCluster jedis = getJedis();

		boolean result = jedis.exists(key);
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：批量的设置key:value,可以一个
	 *  mset(new String[]{"key1","value1","key2","value2"})
	 * @作者: wyt
	 * @参数： @param keysvalues
	 * @参数： @return
	 * @返回值：boolean
	 * @日期: 2016-8-15 下午3:00:02
	 */
	public static boolean mset(String... keysvalues) {
		JedisCluster jedis = getJedis();

		boolean result = (jedis.mset(keysvalues) == "OK") ? true : false;

		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：对value值加1,key不存在会自动创建
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return 加后结果
	 * @返回值：Long
	 * @日期: 2016-8-15 下午3:04:08
	 */
	public static Long incr(String key) {
		JedisCluster jedis = getJedis();

		Long result = jedis.incr(key);

		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：对value值减1,key不存在会自动创建
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return 减后结果
	 * @返回值：Long
	 * @日期: 2016-8-15 下午3:04:08
	 */
	public static Long decr(String key) {
		JedisCluster jedis = getJedis();

		Long result = jedis.decr(key);

		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：对value值减value个
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return 减后结果
	 * @返回值：Long
	 * @日期: 2016-8-15 下午3:04:08
	 */
	public static Long decrBy(String key, Long i) {
		JedisCluster jedis = getJedis();

		Long result = jedis.decrBy(key, i);

		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：对key加value
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param integer
	 * @参数： @return
	 * @返回值：Long
	 * @日期: 2016-8-15 下午3:10:59
	 */
	public static Long incrBy(String key,Long i){
		JedisCluster jedis = getJedis();

		Long result = jedis.incrBy(key,i);

		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：通过key获得value
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return 失败返回null
	 * @返回值：String
	 * @日期: 2016-8-8 下午6:34:40
	 */
	public static Object get(String key) {
		JedisCluster jedis = getJedis();
		if(StringUtils.isBlank(key)){
			return null;
		}
		Object result= SerializingUtil.deserialize(jedis.get(SerializingUtil.serialize(key)));
		
		returnJC(jedis);
		return result;
	}
	public static byte[] getByte(byte[] key) {	
		JedisCluster jedis = getJedis();

		byte[] result = jedis.get(key);
		
		return result;
	}
	/**
	 * 
	 * @功能：通过key获得对象
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return 失败返回null
	 * @返回值：String
	 * @日期: 2016-8-8 下午6:34:40
	 */
	public static Object get(byte[] key) {	
		JedisCluster jedis = getJedis();

		Object result = SerializingUtil.deserialize(jedis.get(key));
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：使用key给field赋值，不存在创建
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param field
	 * @参数： @param value
	 * @参数： @return
	 * @返回值：boolean
	 * @日期: 2016-8-15 下午3:19:53
	 */
	public static boolean hset(String key,String field,String value) {
		JedisCluster jedis = getJedis();

		boolean result = (jedis.hset(key, field, value) == null) ? true : false;
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：设置 hash field
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param hash
	 * @参数： @return
	 * @返回值：boolean
	 * @日期: 2016-8-15 下午3:23:24
	 */
	public static boolean hmset(String key,Map<String, String> hash){
		JedisCluster jedis = getJedis();

		boolean result = (jedis.hmset(key, hash) == "OK") ? true : false;
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：获得value
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param field
	 * @参数： @return
	 * @返回值：String
	 * @日期: 2016-8-15 下午3:26:04
	 */
	public static String hget(String key, String field){
		JedisCluster jedis = getJedis();

		String result = jedis.hget(key, field);
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：获得value list
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param fields
	 * @参数： @return
	 * @返回值：List<String>
	 * @日期: 2016-8-15 下午3:28:34
	 */
	public static List<String> hmget(String key,String...fields){
		JedisCluster jedis = getJedis();

		List<String> result = jedis.hmget(key, fields);
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：删除 field,多个或1个
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @param fields
	 * @参数： @return
	 * @返回值：Long
	 * @日期: 2016-8-15 下午3:33:59
	 */
	public static Long hdel(String key ,String...fields){
		JedisCluster jedis = getJedis();

		Long result = jedis.hdel(key, fields);
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：获得所有field
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return
	 * @返回值：Set<String>
	 * @日期: 2016-8-15 下午3:36:04
	 */
	public static Set<String> hkeys(String key){
		JedisCluster jedis = getJedis();

		Set<String> result = jedis.hkeys(key);
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：获得所有H value
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return
	 * @返回值：Set<String>
	 * @日期: 2016-8-15 下午3:36:04
	 */
	public static List<String> hvals(String key){
		JedisCluster jedis = getJedis();

		List<String> result = jedis.hvals(key);
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：获取所有field and value
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return
	 * @返回值：Map<String,String>
	 * @日期: 2016-8-15 下午3:39:23
	 */
	public static Map<String, String> hgetall(String key){
		JedisCluster jedis = getJedis();

		Map<String, String> result = jedis.hgetAll(key);
		
		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：根据key获得value类型
	 * @作者: wyt
	 * @参数： @param key
	 * @参数： @return
	 * @返回值：String
	 * @日期: 2016-8-15 下午3:47:47
	 */
	public static String type(String key) {
		JedisCluster jedis = getJedis();

		String result = jedis.type(key);

		returnJC(jedis);
		return result;
	}
	/**
	 * 
	 * @功能：根据通配符获得集群中的值
	 * @作者: wyt
	 * @参数： @param jedisCluster
	 * @参数： @param pattern
	 * @参数： @return
	 * @返回值：TreeSet<String>
	 * @日期: 2016-8-8 下午2:57:15
	 */
	public static Set<String> keys(JedisCluster jedisCluster,String pattern){  
		Set<String> keys = new HashSet<>();  
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();  
        
        for(String k : clusterNodes.keySet()){  
            JedisPool jp = clusterNodes.get(k);  
            Jedis connection = jp.getResource();  
            try {  
                keys.addAll(connection.keys(pattern));  
            } catch(Exception e){  
            	e.printStackTrace();
            } finally{  
                connection.close();
            }  
        }  
        return keys;  
    } 
	/**
	 * 
	 * @功能：根据通配符获得集群中的值
	 * @作者: wyt
	 * @参数： @param pattern
	 * @参数： @return
	 * @返回值：TreeSet<String>
	 * @日期: 2016-8-8 下午2:57:15
	 */
	public static Set<String> keys(String pattern) {
		JedisCluster jedis = getJedis();

		return jedis.hkeys(pattern);
	}
	public static JedisCluster getJedisCluster() {
		return jedisCluster;
	}
	public static void setJedisCluster(JedisCluster jedisCluster) {
		JedisProxy.jedisCluster = jedisCluster;
	}
	
	public static void flushDB() {
		JedisCluster jedis = getJedis();
		jedis.flushDB();
		
	}
	public static Long dbSize() {
		JedisCluster jedis = getJedis();
		return jedis.dbSize();
	}
	public  static Long del(byte[] key) {
		JedisCluster jedis = getJedis();

		Long result = jedis.del(key);
		return result;
	}
	
	
}
