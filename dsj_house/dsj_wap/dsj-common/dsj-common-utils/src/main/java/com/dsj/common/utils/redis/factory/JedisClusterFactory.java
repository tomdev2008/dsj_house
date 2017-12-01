package com.dsj.common.utils.redis.factory;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.dsj.common.utils.redis.JedisProxy;
import com.dsj.common.utils.redis.constant.JedisConstant;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * redis
 * @author wyt
 *
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {
	
	private Resource addressConfig;
	
	private String addressKeyPrefix;
	
	private JedisCluster jedisCluster;
	
	private Integer timeout;
	
	private Integer maxRedirections;
	
	private Integer expireTime;
	
	private GenericObjectPoolConfig genericObjectPoolConfig;
	
	private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

	@Override
	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	@Override
	public Class<? extends JedisCluster> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass()
				: JedisCluster.class);
	}

	public boolean isSingleton() {
		return true;
	}

	private Set<HostAndPort> parseHostAndPort() throws Exception {
		try {
			Properties prop = new Properties();
			prop.load(this.addressConfig.getInputStream());

			Set<HostAndPort> haps = new HashSet<HostAndPort>();
			for (Object key : prop.keySet()) {

				if (!((String) key).startsWith(addressKeyPrefix)) {
					continue;
				}

				String val = (String) prop.get(key);

				boolean isIpPort = p.matcher(val).matches();

				if (!isIpPort) {
					throw new IllegalArgumentException("ip or port illegality");
				}
				String[] ipAndPort = val.split(":");

				HostAndPort hap = new HostAndPort(ipAndPort[0],
						Integer.parseInt(ipAndPort[1]));
				haps.add(hap);
			}
			JedisConstant.EXPIRE = expireTime;
			return haps;
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new Exception("analysis jedis file error", ex);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		Set<HostAndPort> haps = this.parseHostAndPort();
		/*JedisCluster jc = new JedisCluster(jedisClusterNodes,1000,5,poolConfig);
	      */
		jedisCluster = new JedisCluster(haps, timeout, maxRedirections,
				genericObjectPoolConfig);
		
		//jedisCluster.select(14);
		//jedisCluster.auth("");

	}

	public void setAddressConfig(Resource addressConfig) {
		this.addressConfig = addressConfig;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public void setAddressKeyPrefix(String addressKeyPrefix) {
		this.addressKeyPrefix = addressKeyPrefix;
	}

	public void setGenericObjectPoolConfig(
			GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}

}