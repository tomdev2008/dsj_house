package com.dsj.common.utils.spring;

import org.springframework.context.ApplicationContext;

public final class SpringBeans
{
	private static SpringBeans instance = new SpringBeans();
	private ApplicationContext springAppContext;

	private SpringBeans()
	{
	}

	public static void initialize(ApplicationContext context)
	{
		instance.springAppContext = context;
	}

	private static void assertSpringContext()
	{
		if (instance.springAppContext == null)
			throw new RuntimeException("未初始化spring application context");
	}

	/**
	 * 通过bean名称获取bean实例
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object get(String beanName)
	{
		assertSpringContext();
		return instance.springAppContext.getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E get(Class<E> beanInterfaceClass,String beanName)
	{
		return (E) get(beanName);
	}

	/**
	 * 同过接口Class获取bean实例
	 * 约束：bean在spring context中的注册名必须为接口类名（首字母小写）
	 * 
	 * @param <E>
	 * @param beanInterfaceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E get(Class<E> beanInterfaceClass)
	{
		String infClassName = beanInterfaceClass.getSimpleName();
		String beanName = infClassName.substring(0, 1).toLowerCase() + infClassName.substring(1);

		return (E) get(beanName);
	}

	/**
	 * 通过bean的ID(name)获取对应的实现Class
	 * 
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getBeanType(String beanName)
	{
		assertSpringContext();

		Class beanImplClass = null;
		try
		{
			beanImplClass = instance.springAppContext.getType(beanName);
		}
		catch (Exception e)
		{
			return null;
		}
		return beanImplClass;
	}

	/**
	 * 判断当前Spring上下文中是否存在指定name（id）的bean
	 * 
	 * @param beanName
	 * @return
	 */
	public static boolean containsBean(String beanName)
	{
		assertSpringContext();

		return instance.springAppContext.containsBean(beanName);
	}
}
