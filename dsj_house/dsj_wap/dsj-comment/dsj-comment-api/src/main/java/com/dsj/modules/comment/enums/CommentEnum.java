package com.dsj.modules.comment.enums;

public enum CommentEnum {
	/**"评论初始值",0 */
	COMMENT_INTI_NUM("评论初始值",0),
	/**"初始点赞",0 */
	LIKE_INIT_NUM("初始点赞",0),
	/**"初始差评",0 */
	NAGETIVA_INIT_NUM("初始差评",0),
	/**"经纪人动态评论",1 */
	COMMENT_AGENT("经纪人动态评论",1),
	/**"楼盘动态评论",2 */
	COMMENT_HOUSE("楼盘动态评论",2),
	/**"经纪人点评楼盘",3 */
	COMMENT__HOUSE_REMARK("经纪人点评楼盘",3),
	/**"普通用户点评楼盘",4 */
	GENERAL_HOUSE_REMARK("普通用户点评楼盘",4),
	/**"经纪人动态点赞",1 */
	AGENTNEWS_LIKE("经纪人动态点赞",1),
	/**"评论或者点评点赞",2 */
	COMMENT_LIKE("评论或者点评点赞",2),
	/**"顶",1 */
	LIKE("顶",1),
	/**"踩",2 */
	UN_LIKE("踩",2);
	
	private String msg;
	private Integer code;
	
	private CommentEnum(String msg,Integer code) {
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
