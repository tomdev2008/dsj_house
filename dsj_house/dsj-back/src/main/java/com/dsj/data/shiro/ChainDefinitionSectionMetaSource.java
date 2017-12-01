package com.dsj.data.shiro;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsj.modules.system.po.FunctionPo;
import com.dsj.modules.system.service.FunctionService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{  
		  
	    private String filterChainDefinitions;  
	    
	    @Autowired
	    FunctionService functionService;
	    /** 
	     * 默认premission字符串 
	     */  
	    public static final String PREMISSION_STRING="perms[\"{0}\"]";  
	    public Section getObject() throws BeansException {  
	  
	        //获取所有Resource  
	        //List<Resource> list = resourceDao.getAll();  
	  
	        Ini ini = new Ini();  
	        //加载默认的url  
	        ini.load(filterChainDefinitions);  
	        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
	        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,  
	        //里面的键就是链接URL,值就是存在什么条件才能访问该链接  
	        Map<String,Object> paramMap=new HashMap<String,Object>();
	        List<FunctionPo> funcs=functionService.listBy(paramMap);
	        for(FunctionPo po:funcs){
	        	if(StringUtils.isNotBlank(po.getPattern())&&StringUtils.isNotBlank(po.getUri())){
	              section.put(po.getUri(),  MessageFormat.format(PREMISSION_STRING,po.getPattern()));  
	        	}
	        }
	        return section;  
	    }  
	    /** 
	     * 通过filterChainDefinitions对默认的url过滤定义 
	     *  
	     * @param filterChainDefinitions 默认的url过滤定义 
	     */  
	    public void setFilterChainDefinitions(String filterChainDefinitions) {  
	        this.filterChainDefinitions = filterChainDefinitions;  
	    }  
	  
	    public Class<?> getObjectType() {  
	        return this.getClass();  
	    }  
	  
	    public boolean isSingleton() {  
	        return false;  
	    }  
}
