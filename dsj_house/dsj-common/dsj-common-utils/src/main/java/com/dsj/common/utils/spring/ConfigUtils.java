package com.dsj.common.utils.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wdg on 2017/1/20.
 */
@Component
public class ConfigUtils implements InitializingBean {
	@Autowired
	public static ConfigUtils instance = new ConfigUtils();

	private String systemCode;

	// 七牛配置--start
	@Value("${AK}")
	private String Ak;

	@Value("${SK}")
	private String Sk;

	@Value("${qiniuDomain}")
	private String qiniuDomain;

	@Value("${bucket}")
	private String bucket;

	@Value("${baseKey}")
	private String baseKey;
	// 骑牛配置--end

	// aliyun配置--start
	@Value("${upload.ossAccessUrl}")
	private String ossAccessUrl;

	@Value("${upload.ossEndPoint}")
	private String ossEndPoint;

	@Value("${upload.accessId}")
	private String accessId;

	@Value("${upload.accessKey}")
	private String accessKey;

	@Value("${upload.ossBucket}")
	private String ossBucket;
	// aliyun配置--end

	// 400-start
	@Value("${mobile.editurl}")
	private String mobileEditUrl;
	
	@Value("${mobile.delurl}")
	private String mobileDelUrl;
	
	@Value("${mobile.detailurl}")
	private String detailurl;
	
	@Value("${mobile.getRecorderUrl}")
	private String getRecorderUrl;

	@Value("${mobile.bigCode400}")
	private String mobileBigCode400;
	
	@Value("${mobile.LoginName}")
	private String mobileLoginName;
	
	@Value("${mobile.redispwd}")
	private String mobileRedispwd;
	
	@Value("${mobile.custid}")
	private String mobileCustid;
	
	@Value("${mobile.addurl}")
	private String mobileAddurl;
	// 400-end

	@Value("${agent.product.name}")
	private String agentProductName;

	@Value("${developer.product.name}")
	private String developerProductName;
	// 开发商添加承诺书
	@Value("${chengNuoBook}")
	private String chengNuoBook;
	
	// 订单excel路径
	@Value("${orderExcelPath}")
	private String orderExcelPath;
	//经纪人数据统计
	@Value("${agentStatisticsPath}")
	private String agentStatisticsPath;
	
	
	// 经纪人400昨天新增excel路径
	@Value("${agentYesterdayExcelPath}")
	private String agentYesterdayExcelPath;
	// 经纪人400累计统计excel路径
	@Value("${agentTotalExcelPath}")
	private String agentTotalExcelPath;
	// 按经纪人400昨天新增excel路径
	@Value("${agentAgentYesterdayExcelPath}")
	private String agentAgentYesterdayExcelPath;
	// 按经纪人400累计统计excel路径
	@Value("${agentAgentTotalExcelPath}")
	private String agentAgentTotalExcelPath;
	

	/*	solr.zk=211.159.180.106:2181
			solr.core.ershoufang=ershoufang
			solr.core.new_house=new_house
			solr.core.new_solr=new_solr
			solr.core.new_house_search_solr=new_house_search_solr*/
	// 开发商添加承诺书
	@Value("${solr.zk}")
	private static String solrZk;
/*	@Value("${solr.core.ershoufang}")
	private String solrErshoufang;
	@Value("${solr.core.new_house}")
	private String solrNewHouse;
	@Value("${new_house_search_solr}")
	private String solrNewSeachh;*/
	
	@Value("${customernumber}")
	private String customernumber;
	@Value("${key}")
	private String key;
	@Value("${callbackurl}")
	private String callbackurl;
	@Value("${pcwebcallbackurl}")
	private String pcwebcallbackurl;
	@Value("${wapwebcallbackurl}")
	private String wapwebcallbackurl;
	@Value("${period}")
	private String period;
	@Value("${PayApi}")
	private String PayApi;
	@Value("${QueryOrderApi}")
	private String QueryOrderApi;
	@Value("${RefundApi}")
	private String RefundApi;
	@Value("${QueryRefundApi}")
	private String QueryRefundApi;
	@Value("${SettleConfirmApi}")
	private String SettleConfirmApi;
	@Value("${QueryBindCardsApi}")
	private String QueryBindCardsApi;
	@Value("${UnbindCardApi}")
	private String UnbindCardApi;
	@Value("${RegisterApi}")
	private String RegisterApi;
	@Value("${uploadApi}")
	private String uploadApi;
	@Value("${ModifyRequestApi}")
	private String ModifyRequestApi;
	@Value("${QueryModifyRequestApi}")
	private String QueryModifyRequestApi;
	@Value("${TransferApi}")
	private String TransferApi;
	@Value("${TransferQueryApi}")
	private String TransferQueryApi;
	@Value("${DivideApi}")
	private String DivideApi;
	@Value("${QueryDivideApi}")
	private String QueryDivideApi;
	@Value("${QueryBalanceApi}")
	private String QueryBalanceApi;
	@Value("${QuerySettlementApi}")
	private String QuerySettlementApi;
	@Value("${DownloadOrderDocumentApi}")
	private String DownloadOrderDocumentApi;
	@Value("${QueryCheckRecordApi}")
	private String QueryCheckRecordApi;
	@Value("${IDCardAuthApi}")
	private String IDCardAuthApi;
	
   @Value("${redis_one.address}")
    private  String redisIp;
    @Value("${redis_one.port}")
    private  Integer port;
    
    @Value("${redis_one.password}")
    private  String password;
    
    @Value("${byNewHouseExcelPath}")
    private  String byNewHouseExcelPath;
    
    @Value("${byNewHouseTotalExcelPath}")
    private  String byNewHouseTotalExcelPath; 
    
    @Value("${newHouseExcelPath}")
    private  String newHouseExcelPath;
    
    @Value("${newHouseTotalExcelPath}")
    private  String newHouseTotalExcelPath;
    
    @Value("${newHouseMobileDetailExcel}")
    private  String newHouseMobileDetailExcel;
    
    
    @Value("${agentMobileDetailExcel}")
    private  String agentMobileDetailExcel;
    
    
    
    public String getNewHouseMobileDetailExcel() {
		return newHouseMobileDetailExcel;
	}

	public void setNewHouseMobileDetailExcel(String newHouseMobileDetailExcel) {
		this.newHouseMobileDetailExcel = newHouseMobileDetailExcel;
	}

	public String getAgentMobileDetailExcel() {
		return agentMobileDetailExcel;
	}

	public void setAgentMobileDetailExcel(String agentMobileDetailExcel) {
		this.agentMobileDetailExcel = agentMobileDetailExcel;
	}

	public String getByNewHouseTotalExcelPath() {
		return byNewHouseTotalExcelPath;
	}

	public void setByNewHouseTotalExcelPath(String byNewHouseTotalExcelPath) {
		this.byNewHouseTotalExcelPath = byNewHouseTotalExcelPath;
	}

	public String getNewHouseTotalExcelPath() {
		return newHouseTotalExcelPath;
	}

	public void setNewHouseTotalExcelPath(String newHouseTotalExcelPath) {
		this.newHouseTotalExcelPath = newHouseTotalExcelPath;
	}

	public String getByNewHouseExcelPath() {
		return byNewHouseExcelPath;
	}

	public void setByNewHouseExcelPath(String byNewHouseExcelPath) {
		this.byNewHouseExcelPath = byNewHouseExcelPath;
	}

	public String getNewHouseExcelPath() {
		return newHouseExcelPath;
	}

	public void setNewHouseExcelPath(String newHouseExcelPath) {
		this.newHouseExcelPath = newHouseExcelPath;
	}

	@Value("${basePath}")
    private  String basePath;
    
    @Value("${oldHouseImgDUL}")
    private String oldHouseImgDUL;
    @Value("${houseDirectoryImgDUL}")
    private String houseDirectoryImgDUL;
    
	public String getOldHouseImgDUL() {
		return oldHouseImgDUL;
	}

	public void setOldHouseImgDUL(String oldHouseImgDUL) {
		this.oldHouseImgDUL = oldHouseImgDUL;
	}

	public String getHouseDirectoryImgDUL() {
		return houseDirectoryImgDUL;
	}

	public void setHouseDirectoryImgDUL(String houseDirectoryImgDUL) {
		this.houseDirectoryImgDUL = houseDirectoryImgDUL;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getOrderExcelPath() {
		return orderExcelPath;
	}

	public void setOrderExcelPath(String orderExcelPath) {
		this.orderExcelPath = orderExcelPath;
	}
	
	public String getAgentYesterdayExcelPath() {
		return agentYesterdayExcelPath;
	}

	public void setAgentYesterdayExcelPath(String agentYesterdayExcelPath) {
		this.agentYesterdayExcelPath = agentYesterdayExcelPath;
	}

	public String getAgentTotalExcelPath() {
		return agentTotalExcelPath;
	}

	public void setAgentTotalExcelPath(String agentTotalExcelPath) {
		this.agentTotalExcelPath = agentTotalExcelPath;
	}

	public String getAgentAgentYesterdayExcelPath() {
		return agentAgentYesterdayExcelPath;
	}

	public void setAgentAgentYesterdayExcelPath(String agentAgentYesterdayExcelPath) {
		this.agentAgentYesterdayExcelPath = agentAgentYesterdayExcelPath;
	}

	public String getAgentAgentTotalExcelPath() {
		return agentAgentTotalExcelPath;
	}

	public void setAgentAgentTotalExcelPath(String agentAgentTotalExcelPath) {
		this.agentAgentTotalExcelPath = agentAgentTotalExcelPath;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public String getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(String customernumber) {
		this.customernumber = customernumber;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPayApi() {
		return PayApi;
	}

	public void setPayApi(String payApi) {
		PayApi = payApi;
	}

	public String getQueryOrderApi() {
		return QueryOrderApi;
	}

	public void setQueryOrderApi(String queryOrderApi) {
		QueryOrderApi = queryOrderApi;
	}

	public String getRefundApi() {
		return RefundApi;
	}

	public void setRefundApi(String refundApi) {
		RefundApi = refundApi;
	}

	public String getQueryRefundApi() {
		return QueryRefundApi;
	}

	public void setQueryRefundApi(String queryRefundApi) {
		QueryRefundApi = queryRefundApi;
	}

	public String getSettleConfirmApi() {
		return SettleConfirmApi;
	}

	public void setSettleConfirmApi(String settleConfirmApi) {
		SettleConfirmApi = settleConfirmApi;
	}

	public String getQueryBindCardsApi() {
		return QueryBindCardsApi;
	}

	public void setQueryBindCardsApi(String queryBindCardsApi) {
		QueryBindCardsApi = queryBindCardsApi;
	}

	public String getUnbindCardApi() {
		return UnbindCardApi;
	}

	public void setUnbindCardApi(String unbindCardApi) {
		UnbindCardApi = unbindCardApi;
	}

	public String getRegisterApi() {
		return RegisterApi;
	}

	public void setRegisterApi(String registerApi) {
		RegisterApi = registerApi;
	}

	public String getUploadApi() {
		return uploadApi;
	}

	public void setUploadApi(String uploadApi) {
		this.uploadApi = uploadApi;
	}

	public String getModifyRequestApi() {
		return ModifyRequestApi;
	}

	public void setModifyRequestApi(String modifyRequestApi) {
		ModifyRequestApi = modifyRequestApi;
	}

	public String getQueryModifyRequestApi() {
		return QueryModifyRequestApi;
	}

	public void setQueryModifyRequestApi(String queryModifyRequestApi) {
		QueryModifyRequestApi = queryModifyRequestApi;
	}

	public String getTransferApi() {
		return TransferApi;
	}

	public void setTransferApi(String transferApi) {
		TransferApi = transferApi;
	}

	public String getTransferQueryApi() {
		return TransferQueryApi;
	}

	public void setTransferQueryApi(String transferQueryApi) {
		TransferQueryApi = transferQueryApi;
	}

	public String getDivideApi() {
		return DivideApi;
	}

	public void setDivideApi(String divideApi) {
		DivideApi = divideApi;
	}

	public String getQueryDivideApi() {
		return QueryDivideApi;
	}

	public void setQueryDivideApi(String queryDivideApi) {
		QueryDivideApi = queryDivideApi;
	}

	public String getQueryBalanceApi() {
		return QueryBalanceApi;
	}

	public void setQueryBalanceApi(String queryBalanceApi) {
		QueryBalanceApi = queryBalanceApi;
	}

	public String getQuerySettlementApi() {
		return QuerySettlementApi;
	}

	public void setQuerySettlementApi(String querySettlementApi) {
		QuerySettlementApi = querySettlementApi;
	}

	public String getDownloadOrderDocumentApi() {
		return DownloadOrderDocumentApi;
	}

	public void setDownloadOrderDocumentApi(String downloadOrderDocumentApi) {
		DownloadOrderDocumentApi = downloadOrderDocumentApi;
	}

	public String getQueryCheckRecordApi() {
		return QueryCheckRecordApi;
	}

	public void setQueryCheckRecordApi(String queryCheckRecordApi) {
		QueryCheckRecordApi = queryCheckRecordApi;
	}

	public String getIDCardAuthApi() {
		return IDCardAuthApi;
	}

	public void setIDCardAuthApi(String iDCardAuthApi) {
		IDCardAuthApi = iDCardAuthApi;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}

	public String getMobileAddurl() {
		return mobileAddurl;
	}

	public void setMobileAddurl(String mobileAddurl) {
		this.mobileAddurl = mobileAddurl;
	}

	public String getMobileCustid() {
		return mobileCustid;
	}

	public void setMobileCustid(String mobileCustid) {
		this.mobileCustid = mobileCustid;
	}

	public String getMobileEditUrl() {
		return mobileEditUrl;
	}

	public void setMobileEditUrl(String mobileEditUrl) {
		this.mobileEditUrl = mobileEditUrl;
	}

	public String getMobileDelUrl() {
		return mobileDelUrl;
	}

	public void setMobileDelUrl(String mobileDelUrl) {
		this.mobileDelUrl = mobileDelUrl;
	}

	public String getMobileBigCode400() {
		return mobileBigCode400;
	}

	public void setMobileBigCode400(String mobileBigCode400) {
		this.mobileBigCode400 = mobileBigCode400;
	}

	public String getMobileLoginName() {
		return mobileLoginName;
	}

	public void setMobileLoginName(String mobileLoginName) {
		this.mobileLoginName = mobileLoginName;
	}

	public String getMobileRedispwd() {
		return mobileRedispwd;
	}

	public void setMobileRedispwd(String mobileRedispwd) {
		this.mobileRedispwd = mobileRedispwd;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getAk() {
		return Ak;
	}

	public void setAk(String ak) {
		Ak = ak;
	}

	public String getSk() {
		return Sk;
	}

	public void setSk(String sk) {
		Sk = sk;
	}

	public String getQiniuDomain() {
		return qiniuDomain;
	}

	public void setQiniuDomain(String qiniuDomain) {
		this.qiniuDomain = qiniuDomain;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getBaseKey() {
		return baseKey;
	}

	public void setBaseKey(String baseKey) {
		this.baseKey = baseKey;
	}

	public String getChengNuoBook() {
		return chengNuoBook;
	}

	public void setChengNuoBook(String chengNuoBook) {
		this.chengNuoBook = chengNuoBook;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ConfigUtils.instance = this;
	}

	public String getOssAccessUrl() {
		return ossAccessUrl;
	}

	public void setOssAccessUrl(String ossAccessUrl) {
		this.ossAccessUrl = ossAccessUrl;
	}

	public String getOssEndPoint() {
		return ossEndPoint;
	}

	public void setOssEndPoint(String ossEndPoint) {
		this.ossEndPoint = ossEndPoint;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getOssBucket() {
		return ossBucket;
	}

	public void setOssBucket(String ossBucket) {
		this.ossBucket = ossBucket;
	}

	public String getAgentProductName() {
		return agentProductName;
	}

	public void setAgentProductName(String agentProductName) {
		this.agentProductName = agentProductName;
	}

	public String getDeveloperProductName() {
		return developerProductName;
	}

	public void setDeveloperProductName(String developerProductName) {
		this.developerProductName = developerProductName;
	}

	public String getGetRecorderUrl() {
		return getRecorderUrl;
	}

	public void setGetRecorderUrl(String getRecorderUrl) {
		this.getRecorderUrl = getRecorderUrl;
	}

	public String getSolrZk() {
		return solrZk;
	}

	public void setSolrZk(String solrZk) {
		this.solrZk = solrZk;
	}
	public String getPcwebcallbackurl() {
		return pcwebcallbackurl;
	}

	public void setPcwebcallbackurl(String pcwebcallbackurl) {
		this.pcwebcallbackurl = pcwebcallbackurl;
	}

	public String getWapwebcallbackurl() {
		return wapwebcallbackurl;
	}

	public void setWapwebcallbackurl(String wapwebcallbackurl) {
		this.wapwebcallbackurl = wapwebcallbackurl;
	}

	public  String getRedisIp() {
		return redisIp;
	}

	public  void setRedisIp(String redisIp) {
		this.redisIp = redisIp;
	}

	public  Integer getPort() {
		return port;
	}

	public  void setPort(Integer port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAgentStatisticsPath() {
		return agentStatisticsPath;
	}

	public void setAgentStatisticsPath(String agentStatisticsPath) {
		this.agentStatisticsPath = agentStatisticsPath;
	}
	
}
