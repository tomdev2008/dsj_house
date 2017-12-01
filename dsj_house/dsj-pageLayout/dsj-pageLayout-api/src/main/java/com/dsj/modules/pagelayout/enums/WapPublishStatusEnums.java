package com.dsj.modules.pagelayout.enums;

public enum WapPublishStatusEnums {
	PUBLISH("发布", 2),
	NOTPUBLISH("未发布", 1),
	
	
	BIGPICTURE("大图",1),
	MANYPIC("多图",2),
	SMALLPICTURE("小图",3),
	TEXT("文字链",4);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private int value;

	private WapPublishStatusEnums(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}

	public int getValue() {
		return value;
	}



	public String getDesc() {
		return desc;
	}


}
