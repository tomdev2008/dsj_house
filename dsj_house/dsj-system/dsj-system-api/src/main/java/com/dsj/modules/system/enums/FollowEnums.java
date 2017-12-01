package com.dsj.modules.system.enums;

public enum FollowEnums {
	
	AGENT("经纪人",4),
	RENTHOUSE("租房",3),
	OLDHOUSE("二手房",2),
	NEWHOUSE("新房",1);
	
	private String info;
	private int value;
	
	private FollowEnums(String info,int value) {
        this.value = value;
        this.info = info;
    }

    public int getValue() {
        return this.value;
    }

    public String getInfo() {
        return this.info;
    }
}
