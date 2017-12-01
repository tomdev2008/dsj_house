package com.dsj.common.constants;

public interface BusinessConst {
	/**房屋类型*/
	static String HOUSE_TYPE="383";
	/**装修情况*/
	static String RENVATION="81";
	/**朝向*/
	static String ORIENTATIONS="80";
	/**物业类型*/
	static String WY_TYPE="72";
	/**楼盘特点*/
	static String DICTRAIT="73";
	/**建筑类型*/
	static String ACHTYPE="74";
	/**建筑类型-楼盘字典*/
	static String ACH_H_TYPE="425";
	/**图片细类*/
	static String PICTURE_STATUS="76";
	/**产权年限*/
	static String CERTIFICATE="71";
	/**房屋特色*/
	static String FEATURES="390";
	/**供暖方式*/
	static String HEATING_MODE="392";
	/**房屋配置 */
	static String HOUSE_DEPLOY = "397";
	/**付款方式 */
	static String PAY_TYPE = "407";
	/**性别限制 */
	static String SEX_TYPE = "416";
	/**产权性质 */
	static String PROPERTY_RIGHT = "459";
	
	/**担保交易的时间  */
	static String WARRANT_DAY = "457";
	
	/**二手房标识 */
	static String SOLR_ERSHOUFANG = "ershoufang_";
	
	/**北京地区编码 */
	static String BEIJING_AREA_CODE = "110100";
	
	static String PAGE_NUM="pg";
	//分页正则
	static String PAGE_NUM_P="pg([0-9]+)";
	
	//价格
	static String PRICE="pr";
	static String PRICE_P="pr([0-9]+)";
	
	static String PRICE_MAX="pmx";
	static String PRICE_MAX_P="pmx([0-9]+)";
	
	static String PRICE_MIN="pmn";
	static String PRICE_MIN_P="pmn([0-9]+)";
	
	//地区
	static String BUILD_AREA="ar";
    static String BUILD_AREA_P="ar([0-9]+)";
    static String BUILD_AREA_MAX="amx";
	static String BUILD_AREA_MAX_P="amx([0-9]+)";
	
	static String BUILD_AREA_MIN="amn";
	static String BUILD_AREA_MIN_P="amn([0-9]+)";
	
	//商圈
	static String BUILD_TRADE="ta";
    static String BUILD_TRADE_P="ta([0-9]+)";
    
    //户型
    static String ROOM="rm";
    static String ROOM_P="rm([0-9]+)";
    
    //特色
    static String TS="ts";
    static String TS_P="ts([0-9]+)";
    
	//付款方式
    static String PAYTYPE="pt";
    static String PAYTYPE_P="pt([0-9]+)";
    
	//租房类型
    static String RENTTYPE="rtp";
    static String RENTTYPE_P="rtp([0-9]+)";
    
    //装修情况
    static String RENOVATION_RT="rt";
    static String RENOVATION_P="rt([0-9]+)";
    
    
    //来源
    static String COMPANY="ct";
    static String COMPANY_P="ct([0-9]+)";
    
    //建筑类型
    static String BUILD_TYPE="bt";
    static String BUILD_TYPE_P="bt([0-9]+)";
    
    //年代
    static String BUILD_YEAR="by";
    static String BUILD_YEAR_P="by([0-9]+)";
    
    //朝向orientations
    static String ORIENTATIONS_O="ori";
    static String ORIENTATIONS_P="ori([0-9]+)";
    
    //朝向物业类型
    static String WY_TYPE_SEO="wy";
    static String WY_TYPE_SEO_P="wy([0-9]+)";
    
    //楼层
    static String FLOOR_TYPE="ft";
    static String FLOOR_TYPE_P="ft([0-9]+)";
    
    //服务
    static String SERVICE_TYPE="fw";
    static String SERVICE_TYPE_P="fw([0-9]+)";
    
    //查询条件
    static String SERVICE_KEY="kw";
    static String SERVICE_KEY_P="kw([\\s\\S]*)";
    
    static String STR_TO_STR="([0-9]+)-([0-9]+)";
    
    static String NUMBER_P="([0-9]+)";
    
    //排序
    
    static String ORDER_OD_P="od([0-9]+)";//排序
    
    static String ORDER_OD="od";//排序

    static String SOLR_DIC="dic";

    static String SOLR_AREA="area";
    
    static String SOLR_TRADE="trade";
    
    static String AREA_UNIT="m²";
    
    static String PC_USER_SIESSION="pc_user_session";
    
    static String WAP_USER_SIESSION="wap_user_session";
}
