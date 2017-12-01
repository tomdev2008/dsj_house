package com.dsj.modules.system.enums;

public enum MemberEnum {
		WAP("WAP",2),
		PC("PC",1),
		
		BlACK_NUM_INIT("初始拉黑次数",0),
		IS_BLACK("是",1),
		NOT_BALCK("否",0);
		
		
		private String msg;
		private Integer code;
		
		private MemberEnum(String msg,Integer code) {
			this.msg = msg;
	        this.code = code;        
	    }

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}
}
