package com.dsj.modules.system.enums;

/**
 * 用户类型
 * 
 * @author wangjl
 * @since 2017-06-15
 * @version V1.0 
 */
public enum UserStatus {
	
    WEIXIN(1, "weixin"), //微信
    QQ(2, "qq"), //QQ
    WEIBO(3, "weibo"); //微博
    
    private int value;

    private String desc;

    private UserStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static String parse(int value) {
        for (UserStatus os : UserStatus.values()) {
            if (value == os.getValue()) {
                return os.getDesc();
            }
        }
        return null;
    }
    
    public static int getValue(String desc) {
        for (UserStatus os : UserStatus.values()) {
            if (desc.equals(os.getDesc())) {
                return os.getValue();
            }
        }
        return 0;
    }
}
