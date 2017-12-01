package com.dsj.common.command;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.collections.map.HashedMap;

import com.dsj.common.enums.OutputEnum;
import com.dsj.common.page.PageParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wdaogang on 2016/10/28.
         */
public class BaseCommand extends PageParam {


    private OutputEnum output=OutputEnum.NORMAL;

    public BaseCommand(){
        setNumPerPage(10);
        setPageNum(1);
    }



    public void setOutput(OutputEnum output) {
        this.output = output;
    }

    public OutputEnum getOutput() {
        return output;
    }

    public Map<String, Object> asMap(){
        Map<String,Object> ret=new HashMap();
        ret.putAll(new BeanMap(this));
        ret.remove("class");
        return ret;
    }

    public PageParam asPageParam() {
        return new PageParam(getPageNum(),getNumPerPage());
    }
}
