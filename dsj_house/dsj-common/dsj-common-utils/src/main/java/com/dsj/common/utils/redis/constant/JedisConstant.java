package com.dsj.common.utils.redis.constant;

/**
 * 
 * @作用：Redis常量 @功能：
 * @作者: zgz
 * @日期：2016-8-5 上午9:47:01 @版本：V1.0
 */
public class JedisConstant {
	/**
	 * 默认过期时间(秒)
	 */
	public static Integer EXPIRE = 3600;
	/**
	 * 1个月
	 */
	public static Integer APPTOKEN_EXPIRE = 2678400;
	/**
	 * 本项目通用key前缀名
	 */
	public final static String DSJ = "dsj.";

	public final static String DIC_TYPE = DSJ + "dic_type";

	public final static String LASTID = DSJ + "lastid";
	
	//二手房字典
	public final static String HOUSE_DIC_TYPE = DSJ + "house_dic_type";

}
