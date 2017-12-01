package com.dsj.common.utils.redis.one;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dsj.common.utils.redis.serialize.SerializingUtil;
import com.dsj.common.utils.spring.ConfigUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil implements  InitializingBean {
	private static JedisPool pool = null;
    /** 默认缓存时间 */
    private static final int DEFAULT_CACHE_SECONDS = 0;// 单位秒 设置成一个钟
    private static Logger LOGGER = Logger.getLogger(RedisPoolUtil.class);
    

    
    public static JedisPool getPool() {
        return pool;
    }
    
    @PostConstruct  
    public void init() { 
    	
    	if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(10000);
            config.setMaxIdle(1000);
            config.setMaxWaitMillis(1000*10);

            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            //new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            //pool = new JedisPool(config, "47.94.15.160", 6379, 10000, "Fanthful@123");
            if(StringUtils.isBlank( ConfigUtils.instance.getPassword())){
            	pool = new JedisPool(config, ConfigUtils.instance.getRedisIp(), ConfigUtils.instance.getPort(), 10000);
            }else{
            	pool = new JedisPool(config, ConfigUtils.instance.getRedisIp(), ConfigUtils.instance.getPort(), 10000, ConfigUtils.instance.getPassword());
            }
        }
    }  
	
    public synchronized static Jedis getResource() {
        if (pool == null) {
            pool = getPool();
        }
        return pool.getResource();
    }
    
    
    public static void closeRedis(Jedis redis) {
    	if (redis != null) {
            redis.close();
        }
    }
    
    public static void set(String key, String value) {
    	Jedis redis = getResource();
    	try {
    		redis.set(key, value);
		} finally {
			closeRedis(redis);
		}
    }
    
    public static void set(Object key, Object value) {
    	Jedis redis = getResource();
    	try {
    		redis.set(SerializingUtil.serialize(key),  SerializingUtil.serialize(value));
		} finally {
			closeRedis(redis);
		}
    }
    
    
    public static String get(String key) {
    	Jedis redis = getResource();
    	try {
    		return redis.get(key);
    	} finally {
    		closeRedis(redis);
    	}
    }

    /**
     * 根据缓存键获取Redis缓存中的值.<br/>
     *
     * @param key
     *            键.<br/>
     * @return Object .<br/>
     * @throws Exception
     */
    public static Object get(Object key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            byte[] obj = jedis.get(SerializingUtil.serialize(key));
            return obj == null ? null : SerializingUtil.deserialize(obj);
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return null;
        } finally {
            closeRedis(jedis);
        }
    }
    
    /**
     * 根据缓存键获取Redis缓存中的值.<br/>
     *
     * @param key
     *            键.<br/>
     * @return Object .<br/>
     * @throws Exception
     */
    public static Object getObj( String  key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            byte[] obj = jedis.get(SerializingUtil.serialize(key));
            return obj == null ? null : SerializingUtil.deserialize(obj);
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return null;
        } finally {
            closeRedis(jedis);
        }
    }
    
    /**
     * 根据缓存键获取Redis缓存中的值.<br/>
     *
     * @param key
     *            键.<br/>
     * @return Object .<br/>
     * @throws Exception
     */
    public static byte[] get( byte[] obj) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.get(obj);
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return null;
        } finally {
            closeRedis(jedis);
        }
    }

    /**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(Object key) {
        Jedis jedis = null;
        Boolean result = false;
        try {
            jedis =  getResource();
            return jedis.exists(SerializingUtil.serialize(key));
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return false;
        } finally {
            closeRedis(jedis);
        }
    }

    public static Boolean existsKey(String key) {
        Jedis jedis = null;
        Boolean result = false;
        try {
            jedis =  getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return false;
        } finally {
            closeRedis(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public static Boolean del(Object... keys) {
        Jedis jedis = null;
        try {
            jedis =  getResource();
            jedis.del(SerializingUtil.serialize(keys));
            return true;
        } catch (Exception e) {
            LOGGER.error("Cache删除失败：" + e);
            return false;
        } finally {
            closeRedis(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public static Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis =  getResource();
            jedis.del(keys);
            return  jedis.del(keys);
        } catch (Exception e) {
            LOGGER.error("Cache删除失败：" + e);
            return 0l;
        } finally {
            closeRedis(jedis);
        }
    }

    /**
     * 保存一个对象到Redis中(缓存过期时间:使用此工具类中的默认时间) . <br/>
     *
     * @param key
     *            键 . <br/>
     * @param object
     *            缓存对象 . <br/>
     * @return true or false . <br/>
     * @throws Exception
     */
    public static Boolean save(Object key, Object object) {
        return save(key, object, DEFAULT_CACHE_SECONDS);
    }

    /**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key
     *            键 . <br/>
     * @param object
     *            缓存对象 . <br/>
     * @param seconds
     *            过期时间（单位为秒）.<br/>
     * @return true or false .
     */
    public static Boolean save(Object key, Object object, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(SerializingUtil.serialize(key), SerializingUtil.serialize(object));
            jedis.expire(SerializingUtil.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Cache保存失败：" + e);
            return false;
        } finally {
            closeRedis(jedis);
        }
    }

    /**
     * 删除Redis中的所有key
     *
     * @throws Exception
     */
    public static void flushAll() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.flushAll();
        } catch (Exception e) {
            LOGGER.error("Cache清空失败：" + e);
        } finally {
            closeRedis(jedis);
        }
    }

    /**
     * 获取list
     * @param <T>
     * @param key
     * @return list
     */
    public static <T> Map<String,T> getMap(String key) throws Exception{
        if(getResource() == null || !getResource().exists(key.getBytes())){
            return null;
        }
        byte[] in = getResource().get(key.getBytes());
        return (Map<String, T>) SerializingUtil.deserialize(in);
    }


    /**
     * 设置 map
     * @param <T>
     * @param key
     */
    public static <T> void setMap(String key ,Map<String,T> map){
        try {
            getResource().set(key.getBytes(),SerializingUtil.serialize(map));
        } catch (Exception e) {
            LOGGER.warn("Set key error : "+e);
        }
    }

	public static Long dbSize() {
		 Jedis jedis = null;
		 Long size=0l;
        try {
            jedis = pool.getResource();
            size= jedis.dbSize();
        } catch (Exception e) {
            LOGGER.error("查询库异常：" + e);
        } finally {
            closeRedis(jedis);
        }
		 return size;
	}

	public static Set<String> keys(String pattern) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			Set<String> allKey = jedis.keys("*" + pattern + "*");
			return allKey;
		} catch (Exception e) {
			LOGGER.error("Cache获取失败：" + e);
			return new HashSet<String>();
		} finally {
			closeRedis(jedis);
		}
	}

	public static void flushDB() {
		 Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.flushDB();
        } catch (Exception e) {
            LOGGER.error("Cache清空失败：" + e);
        } finally {
            closeRedis(jedis);
        }
	}

	public static boolean setex(byte[] key,byte[]value ,int expire) {
		Jedis redis = getResource();
    	try {
    		 redis.setex(key, expire, value);
    		 return true;
		} catch (Exception e) {
            LOGGER.error("保存redis：" + e);
            return false;
        }finally {
			closeRedis(redis);
		}
	}

	public static boolean setByte(byte[] key, byte[] value) {
		Jedis redis = getResource();
    	try {
    		 redis.set(key, value);
    		 return true;
		} catch (Exception e) {
            LOGGER.error("保存redis：" + e);
            return false;
        }finally {
			closeRedis(redis);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}


	
}
