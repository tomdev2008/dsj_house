package com.dsj.modules.system.enums;

/**
 * 用户类型
 * 
 * @author wangjl
 * @since 2017-06-15
 * @version V1.0 
 */
public enum UserType {
	
    EMPLOYEE(1, "employee"), //员工
    AGENT(2, "agent"), //经纪人
    DEVELOPER(3, "developer"), //开发商
    FLAT(4, "flat"), //品牌公寓
    WARRANT(6, "warrant"), //服务
	MEMBER(5,"member");//普通会员
    
    private int value;

    private String desc;

    private UserType(int value, String desc) {
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
        for (UserType os : UserType.values()) {
            if (value == os.getValue()) {
                return os.getDesc();
            }
        }
        return null;
    }
    
    public static int getValue(String desc) {
        for (UserType os : UserType.values()) {
            if (desc.equals(os.getDesc())) {
                return os.getValue();
            }
        }
        return 0;
    }
}
