package com.dsj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdaogang on 2016/10/28.
 */
public enum OutputEnum {


    /** 输出正常 **/
    NORMAL("正常", "1"),
    /** 输出EXCEL **/
    EXCEL("EXCEL", "2"),
    /** 输出CSV **/
    CSV("CSV","3");


    /** 描述 */
    private String desc;
    /** 枚举值 */
    private String value;

    private OutputEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

//    public void setValue(String value) {
//        this.value = value;
//    }

    public String getDesc() {
        return desc;
    }

//    public void setDesc(String desc) {
//        this.desc = desc;
//    }

    public static OutputEnum getEnum(String value){
        OutputEnum resultEnum = null;
        OutputEnum[] enumAry = OutputEnum.values();
        for(int i = 0;i<enumAry.length;i++){
            if(enumAry[i].getValue().equals(value)){
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        OutputEnum[] ary = OutputEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(ary[num].getValue()));
            map.put("value", ary[num].getValue());
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List toList(){
        OutputEnum[] ary = OutputEnum.values();
        List list = new ArrayList();
        for(int i=0;i<ary.length;i++){
            Map<String,String> map = new HashMap<String,String>();
            map.put("value",ary[i].getValue());
            map.put("desc", ary[i].getDesc());
            list.add(map);
        }
        return list;
    }


}
