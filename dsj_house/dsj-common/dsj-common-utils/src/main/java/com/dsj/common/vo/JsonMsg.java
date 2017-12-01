package com.dsj.common.vo;

import java.io.Serializable;

/**
 * Created by liu on 2016/11/2.
 */
public class JsonMsg implements Serializable{
    private String code; // 0 失败 1 成功
    private String msg;

    public JsonMsg(){}
    public JsonMsg(String code){
        this.code=code;
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
