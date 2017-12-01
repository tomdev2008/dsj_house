package com.dsj.data.maitian.biz.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.data.maitian.biz.MaitianBiz;
import com.dsj.data.service.UploadService;
import com.dsj.modules.oldHouseParser.po.DicDealLogsCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAgentCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.MtfcOldhouseFlagPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;
import com.dsj.modules.oldHouseParser.service.HousePictureCrawlerService;
import com.dsj.modules.oldHouseParser.service.MasterCrawlerTaskService;
import com.dsj.modules.oldHouseParser.service.MtfcOldhouseFlagService;
import com.dsj.modules.oldHouseParser.service.OldHousePictureCrawlerService;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;

@Service
public class MaitianBizImpl implements MaitianBiz{
	private final static Logger LOGGER = LoggerFactory.getLogger(MaitianBizImpl.class);
	
	@Autowired
	MasterCrawlerTaskService masterCrawlerTaskService;
	
	public void runPage(Integer s,Integer e){
		start(s,e);
	}
	
	public void runEsfId(String esfId){
		try {
			List<Object> resList = dealEsf(esfId);
			saveDate(resList);
			saveDown( 0, 0 , esfId , "Y");
			LOGGER.info("suceessOne:esfId="+esfId);
		} catch (Exception e) {
			LOGGER.error("errorOne:esfId="+esfId,e);
		}
	}
	

	//下载二手房图片，上传，并删除
	public void downUpDelEsfImg(){
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isDownload", YesNoEnum.NO.getValue());//没下载
			PageBean pageTmp = oldHousePictureCrawlerService.listPage(new PageParam( 1, pagesize), paramMap);
			Integer pageCount = pageTmp.getPageCount();
			for (int i = 1; i <= pageCount; i++) {
				PageParam pageParam = new PageParam( i, pagesize);
				PageBean page = oldHousePictureCrawlerService.listPage(pageParam, paramMap);
				@SuppressWarnings("unchecked")
				List<OldHousePictureCrawlerPo> list = (List<OldHousePictureCrawlerPo>) page.getRecordList();
				for (OldHousePictureCrawlerPo po : list) {
					try {
						String path = downloadImg(esfImgPath , po.getOriginPictureUrl());
						if (path != null) {
							po.setPath(path);
							po.setIsDownload(YesNoEnum.YES.getValue());//设置已下载
							
							String picUrl = uploadImg(esfImgPath+path);
							if (StringUtils.isNotEmpty(picUrl)) {
								po.setPictureUrl(picUrl);
								po.setIsUpload(YesNoEnum.YES.getValue());//设置已上传
								po.setUpdateTime(new Date());
								boolean b = deleteImg(esfImgPath+path);
								if (b) {
									oldHousePictureCrawlerService.updateDynamic(po);
									LOGGER.info("二手房图片操作完成：OldHousePictureCrawlerPo："+po.toString() );
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("二手房图片操作出错:"+po.toString(), e);
					}
					
				}
			}
		} catch (Exception e) {
			LOGGER.error("二手房图片操作出错", e);
		}
	}
	
	//下载小区图片，上传，并删除
	public void downUpDelXqImg(){ 
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isDownload", YesNoEnum.NO.getValue());//没下载
			paramMap.put("originObjId", "1111027378045");//测试
			PageBean pageTmp = housePictureCrawlerService.listPage(new PageParam( 1, pagesize), paramMap);
			Integer pageCount = pageTmp.getPageCount();
			for (int i = 1; i <= pageCount; i++) {
				PageParam pageParam = new PageParam( i, pagesize);
				PageBean page = housePictureCrawlerService.listPage(pageParam, paramMap);
				@SuppressWarnings("unchecked")
				List<HousePictureCrawlerPo> list = (List<HousePictureCrawlerPo>) page.getRecordList();
				for (HousePictureCrawlerPo po : list) {
					try {
						String path = downloadImg(xqImgPath , po.getOriginPictureUrl());
						if (path != null) {
							po.setPath(path);
							po.setIsDownload(YesNoEnum.YES.getValue());//设置已下载
							
							String picUrl = uploadImg(xqImgPath+path);
							if (StringUtils.isNotEmpty(picUrl)) {
								po.setPictureUrl(picUrl);
								po.setIsUpload(YesNoEnum.YES.getValue());//设置已上传
								po.setUpdateTime(new Date());
								boolean b = deleteImg(xqImgPath+path);
								if (b) {
									housePictureCrawlerService.updateDynamic(po);
									LOGGER.info("小区图片操作完成：OldHousePictureCrawlerPo："+po.toString() );
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("小区图片操作出错:"+po.toString(), e);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("小区图片操作出错", e);
		}
	}

	public void downEsfImg(){
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isDownload", YesNoEnum.NO.getValue());//没下载
			PageBean pageTmp = oldHousePictureCrawlerService.listPage(new PageParam( 1, pagesize), paramMap);
			Integer pageCount = pageTmp.getPageCount();
			for (int i = 1; i <= pageCount; i++) {
				PageParam pageParam = new PageParam( i, pagesize);
				PageBean page = oldHousePictureCrawlerService.listPage(pageParam, paramMap);
				@SuppressWarnings("unchecked")
				List<OldHousePictureCrawlerPo> list = (List<OldHousePictureCrawlerPo>) page.getRecordList();
				for (OldHousePictureCrawlerPo po : list) {
					try {
						String path = downloadImg(esfImgPath , po.getOriginPictureUrl());
						if (path != null) {
							System.out.println("源地址："+po.getOriginPictureUrl());
							po.setPath(path);
							po.setIsDownload(YesNoEnum.YES.getValue());//设置已下载
							po.setUpdateTime(new Date());
							oldHousePictureCrawlerService.updateDynamic(po);
							System.out.println("本地地址："+esfImgPath+path);
						}
					} catch (Exception e) {
						LOGGER.error("二手房图片下载出错:"+po.toString(), e);
					}
					
				}
			}
		} catch (Exception e) {
			LOGGER.error("二手房图片下载出错", e);
		}
	}
	
	public void upEsfImg(){
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isDownload", YesNoEnum.YES.getValue());//已下载
			paramMap.put("isUpload", YesNoEnum.NO.getValue());//没上传
			PageBean pageTmp = oldHousePictureCrawlerService.listPage(new PageParam( 1, pagesize), paramMap);
			Integer pageCount = pageTmp.getPageCount();
			for (int i = 1; i <= pageCount; i++) {
				PageParam pageParam = new PageParam( i, pagesize);
				PageBean page = oldHousePictureCrawlerService.listPage(pageParam, paramMap);
				@SuppressWarnings("unchecked")
				List<OldHousePictureCrawlerPo> list = (List<OldHousePictureCrawlerPo>) page.getRecordList();
				for (OldHousePictureCrawlerPo po : list) {
					try {
						System.out.println("本地地址："+esfImgPath+po.getPath());
						String picUrl = uploadImg(esfImgPath+po.getPath());
						if (StringUtils.isNotEmpty(picUrl)) {
							po.setPictureUrl(picUrl);
							po.setIsUpload(YesNoEnum.YES.getValue());//设置已上传
							po.setUpdateTime(new Date());
							System.out.println("上传地址："+picUrl);
							oldHousePictureCrawlerService.updateDynamic(po);
						}
					} catch (Exception e) {
						LOGGER.error("二手房图片上传出错:"+po.toString(), e);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("二手房图片上传出错", e);
		}
	}
	
	public void downXqImg(){
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isDownload", YesNoEnum.NO.getValue());//没下载
			//paramMap.put("originObjId", "1828");//测试
			PageBean pageTmp = housePictureCrawlerService.listPage(new PageParam( 1, pagesize), paramMap);
			Integer pageCount = pageTmp.getPageCount();
			for (int i = 1; i <= pageCount; i++) {
				PageParam pageParam = new PageParam( i, pagesize);
				PageBean page = housePictureCrawlerService.listPage(pageParam, paramMap);
				@SuppressWarnings("unchecked")
				List<HousePictureCrawlerPo> list = (List<HousePictureCrawlerPo>) page.getRecordList();
				for (HousePictureCrawlerPo po : list) {
					try {
						String path = downloadImg(xqImgPath , po.getOriginPictureUrl());
						if (path != null) {
							System.out.println("源地址："+po.getOriginPictureUrl());
							po.setPath(path);
							po.setIsDownload(YesNoEnum.YES.getValue());//设置已下载
							po.setUpdateTime(new Date());
							housePictureCrawlerService.updateDynamic(po);
							System.out.println("本地地址："+xqImgPath+path);
						}
					} catch (Exception e) {
						LOGGER.error("小区图片下载出错:"+po.toString(), e);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("小区图片下载出错", e);
		}
	}
	
	public void upXqImg(){
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isDownload", YesNoEnum.YES.getValue());//已下载
			paramMap.put("isUpload", YesNoEnum.NO.getValue());//没上传
			//paramMap.put("originObjId", "1828");//测试
			PageBean pageTmp = housePictureCrawlerService.listPage(new PageParam( 1, pagesize), paramMap);
			Integer pageCount = pageTmp.getPageCount();
			for (int i = 1; i <= pageCount; i++) {
				PageParam pageParam = new PageParam( i, pagesize);
				PageBean page = housePictureCrawlerService.listPage(pageParam, paramMap);
				@SuppressWarnings("unchecked")
				List<HousePictureCrawlerPo> list = (List<HousePictureCrawlerPo>) page.getRecordList();
				for (HousePictureCrawlerPo po : list) {
					try {
						System.out.println("本地地址："+xqImgPath+po.getPath());
						String picUrl = uploadImg(xqImgPath+po.getPath());
						if (StringUtils.isNotEmpty(picUrl)) {
							po.setPictureUrl(picUrl);
							po.setIsUpload(YesNoEnum.YES.getValue());//设置已上传
							po.setUpdateTime(new Date());
							System.out.println("上传地址："+picUrl);
							housePictureCrawlerService.updateDynamic(po);
						}
					} catch (Exception e) {
						LOGGER.error("小区图片上传出错:"+po.toString(), e);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("小区图片上传出错", e);
		}
	}
	
	@Autowired
	private HouseMasterCrawlerService houseMasterCrawlerService;
	
	@Autowired
	private MtfcOldhouseFlagService mtfcOldhouseFlagService;
	
	@Autowired
	private OldHousePictureCrawlerService oldHousePictureCrawlerService;
	
	@Autowired
	private HousePictureCrawlerService housePictureCrawlerService;
	
	@Autowired
	private UploadService uploadService;
	
	private List<String> listEsf = new ArrayList<String>();
	//整页数url前缀
	private String pgurl = "http://bj.maitian.cn/esfall/PG";
	//二手房url前缀
	private String esfUrl = "http://bj.maitian.cn/esfxq/";//IFY00585137
	//小区url前缀
	private String xqUrl = "http://bj.maitian.cn/xqxqgk/";//I1AE7343323A29630E053660310AC41DE
	
	//上传下载磁盘路径前缀 -- 二手房
	@Value("${oldHouseImgDUL}")
	private String esfImgPath;;
	
	//上传下载磁盘路径前缀 -- 小区
	@Value("${houseDirectoryImgDUL}")
	private String xqImgPath;
	//二手房
	HouseMasterCrawlerPo ershoufPo;

	//二手房图片
	List<OldHousePictureCrawlerPo> ershoufPicList ;
	//小区
	HouseAlikeCommunityPo xiaoquPo;
	//小区图片
	List<HousePictureCrawlerPo> xiaoquPicList ;
	//经纪人
	HouseAgentCrawlerPo agent;
	
	private Integer pagesize = 1000;
	/**
	 * 爬取多页数据
	 * @param startPg 开始页数
	 * @param endPg 结束页数（传null则自动获取最大）
	 */
	public void start(Integer startPg,Integer endPg){
		if (startPg == null) {
			startPg = 1;
		}
		if (endPg == null || startPg > endPg ) {
			endPg = getTotalPage(pgurl+startPg);
		}
		for (int pg = startPg; pg <= endPg; pg++) {
			dealPage(pg);
		}
	}
	
	/**
	 * 爬取单页数据
	 * @param pg 页号
	 */
	public void dealPage(Integer pg){
		Document doc = null;
		try {
			doc = Jsoup.connect(pgurl + pg).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Element list_wrap = doc.select(".list_wrap").first();
		Element ul = list_wrap.getElementsByTag("ul").first();
		Elements liArr = ul.children();
		for (int i = 0; i < liArr.size() ; i ++) {
			String ersId = null;
			try {
				ersId = liArr.get(i).child(0).attr("href").replace("/esfxq/", "").trim();
				if (!checkSaved(ersId)) {
					List<Object> resList = dealEsf(ersId);
					saveDate(resList);
					saveDown( i, pg , ersId , "Y");
					LOGGER.info("suceess:当前页数:"+pg+",最后下载的房源id:" + listEsf.get(listEsf.size()-1));
				}
			} catch (Exception e) {
				try {
					ersId = liArr.get(i).child(0).attr("href").replace("/esfxq/", "").trim();
					if (!checkSaved(ersId)) {
						List<Object> resList = dealEsf(ersId);
						saveDate(resList);
						saveDown( i, pg , ersId , "Y");
						LOGGER.info("suceess:当前页数:"+pg+",最后下载的房源id:" + listEsf.get(listEsf.size()-1));
					}
				} catch (Exception e2) {
					try {
						ersId = liArr.get(i).child(0).attr("href").replace("/esfxq/", "").trim();
						if (!checkSaved(ersId)) {
							List<Object> resList = dealEsf(ersId);
							saveDate(resList);
							saveDown( i, pg , ersId , "Y");
							LOGGER.info("suceess:当前页数:"+pg+",最后下载的房源id:" + listEsf.get(listEsf.size()-1));
						}
					} catch (Exception e3) {
						e.printStackTrace();
						LOGGER.error("esf_add_fail: page:"+pg+",esfId:"+ersId,e.getMessage());
						saveDown( i, pg , ersId , "N");
					}
				}
			}
		}
	}
	
	/**
	 * 保存数据
	 * @param resList
	 * @throws Exception 
	 */
	public void saveDate(List<Object> resList) throws Exception{
		//小区
		HouseAlikeCommunityPo xiaoquRes = (HouseAlikeCommunityPo) resList.get(0);
		//小区图片
		@SuppressWarnings("unchecked")
		List<HousePictureCrawlerPo> xiaoquPicListRes = (List<HousePictureCrawlerPo>) resList.get(1);
		//二手房
		HouseMasterCrawlerPo esfRes = (HouseMasterCrawlerPo) resList.get(2);
		esfRes.setBuildYear(xiaoquRes.getBuiltYear());//建筑年代
		//二手房图片
		@SuppressWarnings("unchecked")
		List<OldHousePictureCrawlerPo> esfPicListRes = (List<OldHousePictureCrawlerPo>) resList.get(3);
		//经纪人
		HouseAgentCrawlerPo agentRes = (HouseAgentCrawlerPo) resList.get(4);
		
		//交易记录
		esfRes.setDealList((List<DicDealLogsCrawlerPo>) resList.get(5));
		esfRes.setCompanyType(CompanyTypeEnum.MAITIAN.getValue());
		agentRes.setCompanyType(CompanyTypeEnum.MAITIAN.getValue());
		houseMasterCrawlerService.saveHouseMasterCrawler
			(esfRes, xiaoquRes, xiaoquPicListRes, esfPicListRes, agentRes, CompanyTypeEnum.MAITIAN);
	}
	
	/**
	 * 数据记录
	 * @param item
	 * @param pg
	 * @param ersId
	 */
	public void saveDown(Integer item , Integer pg , String ersId ,String suceessflag){
		MtfcOldhouseFlagPo po = new MtfcOldhouseFlagPo();
		po.setCreateTime(new Date());
		po.setPagenum(item +1);
		po.setPageflag(pg);
		po.setUrl(esfUrl + ersId);
		po.setPageurl(pgurl + pg);
		po.setSuccessflag(suceessflag);
		mtfcOldhouseFlagService.saveDynamic(po);
	}
	
	public boolean checkSaved(String ersId){
		Map<String, Object> map = new HashMap<>();
		map.put("url", ersId);
		List<MtfcOldhouseFlagPo> listBy = mtfcOldhouseFlagService.listBy(map);
		if (listBy!=null && listBy.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 爬取二手房房源数据单条
	 * @param mainId 原房源id
	 * @return
	 * @throws Exception
	 */
	public List<Object> dealEsf(String mainId) throws Exception  {
		List<Object> resList = null;
		ershoufPo = new HouseMasterCrawlerPo();
		agent = new  HouseAgentCrawlerPo();
		listEsf.add(mainId);
		ershoufPo.setOriginHouseId(mainId);
		ershoufPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		agent.setOriginHouseId(mainId);
		Document document = Jsoup.connect(esfUrl+mainId).get();
		
		Element hc_left = document.select(".home_content .hc_left").first();
		//title
		String title = hc_left.select("h1 samp").text();
		ershoufPo.setTitle(title);
		//楼盘特色名称
		Elements marks = hc_left.select("h1 dl dd mark");
		String features = "";
		for (int i =0; i<marks.size(); i++) {
			if (marks.size() == i+1) {
				features += marks.get(i).text().trim();
			}else {
				features += marks.get(i).text().trim()+",";
			}
		}
		ershoufPo.setFeaturesName(features);
		//tr的集合
		Elements trArr = hc_left.getElementsByTag("table").get(0).getElementsByTag("tbody").first().children();
		
		//售价
		String price = trArr.get(0).select("td strong span").text();
		ershoufPo.setPrice(new Double(price));
		//单价
		String _unit_price = trArr.get(1).select("td").get(0).text();
		String unit_price = _unit_price.substring(3, _unit_price.indexOf(" 元/"));
		ershoufPo.setUnitPrice(new Double(unit_price));
		//首付
		String _payments = trArr.get(2).select("td").get(0).text();
		String payments = _payments.substring(3, _payments.indexOf("万"));
		ershoufPo.setPayments(new Double(payments));
		//月供
		String _monthpay = trArr.get(2).select("td").get(1).text();
		String monthpay = _monthpay.substring(3, _monthpay.indexOf("元"));
		ershoufPo.setMonthpay(new Double(monthpay));
		//建筑面积
		String _build_area = trArr.get(4).select("td").get(0).text();
		String build_area = _build_area.substring(5, _build_area.indexOf("㎡"));
		ershoufPo.setBuildArea(new Double(build_area));
		String huxing = trArr.get(4).select("td").get(1).text().substring(3);
		//室
		String room = huxing.substring(0, huxing.indexOf("室")).trim();
		
		ershoufPo.setRoom((int)Math.ceil(Float.parseFloat(room)));
		//厅
		String hall = huxing.substring(huxing.indexOf("室")+1, huxing.indexOf("厅")).trim();
		ershoufPo.setHall(Integer.parseInt(hall));
		
		//厨
		String kitchen = huxing.substring(huxing.indexOf("厅")+1, huxing.indexOf("厨")).trim();
		ershoufPo.setKitchen(Integer.parseInt(kitchen));
		
		//卫
		String toilet = huxing.substring(huxing.indexOf("厨")+1, huxing.indexOf("卫")).trim();
		ershoufPo.setToilet(Integer.parseInt(toilet));
		
		//朝向名称
		String _orientations_name = trArr.get(5).select("td").get(0).text();
		String orientations_name = _orientations_name.substring(3).trim();
		ershoufPo.setOrientationsName(orientations_name);
		
		//楼层
		String _floor_num = trArr.get(5).select("td").get(1).text();
		String floor_num = _floor_num.substring(3);
		if (floor_num.length()>0) {
			String floor_type = floor_num.trim().substring(0, 1);
			if ("低".equals(floor_type)) ershoufPo.setFloorType(1);
			if ("中".equals(floor_type)) ershoufPo.setFloorType(2);
			if ("高".equals(floor_type)) ershoufPo.setFloorType(3);
			String totalFloor = floor_num.substring(floor_num.indexOf("/")+1, floor_num.lastIndexOf("层"));
			String totalFloorName = floor_num.substring(0,floor_num.indexOf("/"));
			ershoufPo.setFloorNum(Integer.parseInt(totalFloor));
			ershoufPo.setFloorTypeName(totalFloorName);
		}
		
		/*//房评
		if (trArr.get(9).select("td").size()>0) {
			String house_review = trArr.get(9).select("td").get(0).text();
		}*/
		
		//经纪人
		Element hc_right = document.select(".home_content .hc_right").first();
		//经纪人头像
		String agent_img = hc_right.select("dl dt a img").attr("src");
		//经纪人姓名
		String agent_name = hc_right.select("dl dd span a").text();
		//经纪人电话
		String phoneStr = hc_right.select("#clickMobileTJ").attr("onclick");
		phoneStr = checkCellphone(phoneStr);
		agent.setAgentName(agent_name);
		agent.setAgentPhone(phoneStr);
		agent.setOriginAgentPicUrl(agent_img);
		
		//房源图片
		OldHousePictureCrawlerPo po;
		ershoufPicList = new ArrayList<OldHousePictureCrawlerPo>();
		Elements house_photos = document.getElementById("house_photos").select(".house_photos_cont li");
		for (Element element : house_photos) {
			po = new OldHousePictureCrawlerPo();
			po.setOriginObjId(mainId);//源房源id
			po.setOriginPictureUrl(element.select("img").attr("data-original"));
			po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			po.setCreateTime(new Date());
			if ("户型".equals(element.text())) {
				po.setPictureType(PictureTypeEnum.HOUSETYPE.getValue());
			}else {
				po.setPictureType(PictureTypeEnum.INSIDE.getValue());
			}
			po.setCompayType(CompanyTypeEnum.MAITIAN.getValue());
			po.setIsUpload(YesNoEnum.NO.getValue());
			po.setIsDownload(YesNoEnum.NO.getValue());
			ershoufPicList.add(po);
		}
		
		//小区
		String xiaoquId = document.select(".environment table tbody tr td a").first().attr("href").trim().replace("/xqxqgk/", "");
		ershoufPo.setOriginDicId(xiaoquId);
		
		
		resList = xiaoquPage(xiaoquId);
		
		List<DicDealLogsCrawlerPo> dealList=(List<DicDealLogsCrawlerPo>) resList.get(2);
		resList.add(2, ershoufPo);
		resList.add(3, ershoufPicList);
		resList.add(4, agent);
		resList.add(5,dealList);
		return resList;
	}
	
	private List<DicDealLogsCrawlerPo> dealChengjiaoLogs(Document document,String mainId) {
		//tr集合
		Elements  trArr=document.getElementsByClass("trad_table fl").get(0).getElementsByTag("tbody").first().children();
		List<DicDealLogsCrawlerPo> dealList=new ArrayList<DicDealLogsCrawlerPo>();
		for(Element e:trArr){
			Elements tde=e.getElementsByTag("td");
			
			DicDealLogsCrawlerPo po=new DicDealLogsCrawlerPo();
			po.setCompayType(CompanyTypeEnum.MAITIAN.getValue());
			po.setOriginDicId(mainId);
			
			String title=tde.get(0).getElementsByClass("fl trad_info").get(0).getElementsByTag("a").get(0).ownText();
			po.setTitle(title);
			
			if(title.split(" ").length>0){
				po.setRoom(Integer.parseInt(com.dsj.common.utils.StringUtils.getNumberByText(title.split(" ")[0])));
			}
			
			if(title.split(" ").length>=2){
				po.setHall(Integer.parseInt(com.dsj.common.utils.StringUtils.getNumberByText(title.split(" ")[1])));
			}
			
			String title2=tde.get(0).getElementsByClass("fl trad_info").get(0).getElementsByTag("span").get(0).ownText();
			
			if(title2.split("/").length>0){
				FloorTypeEnum[] ary = FloorTypeEnum.values();
				for (int i=0;i<ary.length;i++) {
					if(ary[i].getDesc().equals(title2.split("/")[0].split("楼层")[0])){
						po.setFloorType(ary[i].getValue());
					}
				}
			}
			
			if(title2.split("/").length>=2){
				po.setFloorNum(Integer.parseInt(com.dsj.common.utils.StringUtils.getNumberByText(title2.split("/")[1])));
			}
			
			if(title2.split(" ").length>0){
				po.setOrientationsName(title2.split(" ")[1]);
			}
			
			//第二tr
			String area=tde.get(1).ownText();
			
			po.setBuildArea(Double.parseDouble(area.replace("㎡", "")));
			
			//第三个
			String unitPrice=tde.get(2).ownText();
			po.setUnitPrice(new java.math.BigDecimal(unitPrice.replace("元/㎡", "")));
			
			String price=tde.get(3).getElementsByTag("span").get(0).ownText();
			po.setPrice(new java.math.BigDecimal(price.replace("万", "")));
			
			
			String contractDate=tde.get(5).ownText();
			po.setContractDate(DateUtils.string2Date(contractDate));
			
			String source=tde.get(6).ownText();
			po.setSource(source);
			dealList.add(po);
		}
		return dealList;
	}

	//小区信息
	public List<Object>  xiaoquPage(String mainId) throws Exception{
		List<Object> list = new ArrayList<>() ;
		xiaoquPo = new HouseAlikeCommunityPo();
		xiaoquPo.setOriginCommunityId(mainId);

		Document document = Jsoup.connect(xqUrl+mainId).get();
		
		Element status = document.getElementById("status");
		
		//小区名称
		String title = status.select(".xq_title cite span").first().text();
		xiaoquPo.setName(title);
		
		//小区别名
		String _nickname = status.select(".xq_title cite").first().text();
		if (_nickname.contains("[别名:")) {
			String nickname = _nickname.substring(_nickname.indexOf("[")+4, _nickname.indexOf("]"));
			xiaoquPo.setNickName(nickname);
		}
		
		Elements home_main = status.select(".home_content .home_content_left .home_main").get(0).children();
		
		//小区均价
		String junjia = home_main.get(0).select("b").first().text().replace("元", "").trim();
		xiaoquPo.setAveragePrice(junjia);
		
		//待售房源
		String daishou = home_main.get(1).select("a").first().text().trim();
		xiaoquPo.setSaleNumber(daishou);
		
		/*//已成交房源
		String yichengjiao = home_main.get(1).select("a").get(1).text().trim();
		*/
		
		//商圈
		String area = home_main.get(2).select("a").get(0).text().trim();
		String areaName3 = area.substring(area.indexOf("[")+1, area.indexOf("]"));
		String tradingAreaName = area.substring(area.indexOf("]")+1);
		xiaoquPo.setAreaCode1("110000");
		xiaoquPo.setAreaCode2("110100");
		xiaoquPo.setAreaName1("北京市");
		xiaoquPo.setAreaName2("北京市");
		if ("延庆".equals(areaName3) || "密云".equals(areaName3)) {
			areaName3 += "县";
		}else {
			areaName3 += "区";
		}
		xiaoquPo.setAreaName3(areaName3);
		xiaoquPo.setTradingAreaName(tradingAreaName);
		
		//小区地址
		String address = home_main.get(3).select("em").get(0).text().trim();
		xiaoquPo.setAddress(address);
		
		//开 发 商
		String develops = home_main.get(4).select("em").get(0).text().trim();
		xiaoquPo.setDeveloper(develops);
		
		Elements home_details = status.select(".home_content .home_content_left .home_details").get(0).children();
		
		//建筑面积
		String built_up = home_details.get(0).select("em").get(0).text().trim().replace("万平方米", "");
		xiaoquPo.setBuiltUp(built_up);

		//所在环线
//		String online = home_details.get(1).select("em").get(0).text().trim();
		
		//物 业 费
		String property_fee = home_details.get(2).select("em").get(0).text().trim();
		xiaoquPo.setPropertyFee(property_fee);
		
		//建成年代
		String ach_year = home_details.get(3).select("em").get(0).text().trim().replace("年", "");
		xiaoquPo.setBuiltYear(ach_year);
		
		//总户数
		String house_num = home_details.get(4).select("em").get(0).text().trim().replace("户", "");
		xiaoquPo.setTotalHouse(house_num);
		
		//绿化率 
		String green_rate = home_details.get(5).select("em").get(0).text().trim().replace("%", "");
		xiaoquPo.setGreenRate(green_rate);
		
		//占地面积
		String occupy_area = home_details.get(6).select("em").get(0).text().trim().replace("万平方米", "");
		xiaoquPo.setOccupyArea(occupy_area);
		
		//楼栋总数
		String floor_num = home_details.get(7).select("em").get(0).text().trim().replace("栋", "");
		xiaoquPo.setBuildingNum(floor_num);
		
		//容 积 率
		String plot_ratio = home_details.get(8).select("em").get(0).text().trim();
		xiaoquPo.setPlotRatio(plot_ratio);
		
		//经纬度
		Elements elements = document.getElementsByTag("script");
		for (Element element : elements) {
			if (element.data().toString().contains("CommunityX")) {
				/*取得JS变量数组*/  
	            String[] data = element.data().toString().split("var");  
	            /*取得单个JS变量*/  
	            for(String variable : data){  
	                /*过滤variable为空的数据*/  
	                if(variable.contains("=")){  
	                    /*取到满足条件的JS变量*/  
	                    if(variable.contains("CommunityX") || variable.contains("CommunityY")){  
	                        String[]  kvp = variable.split("=");
	                        if (kvp[0].contains("CommunityX")) {
	                        	String xx = kvp[1].trim().replace("'", "");
	                        	xiaoquPo.setAccuracy(xx);
							}
	                        if (kvp[0].contains("CommunityY")) {
	                        	String yy = kvp[1].trim().replace("'", "");
	                        	xiaoquPo.setDimension(yy);
							}
	                    }  
	                }  
	            }
	            break;
			}
		}
		
		//小区相册
		Element pics_p = document.select("#trad_list").get(0).nextElementSibling();
		Elements pics = pics_p.select(".tabBar .conWrap .con dl dd a img");
		
		HousePictureCrawlerPo picPo;
		xiaoquPicList = new ArrayList<HousePictureCrawlerPo>();
		for (Element element : pics) {
			picPo = new HousePictureCrawlerPo();
			picPo.setOriginObjId(mainId);
			picPo.setOriginPictureUrl(element.attr("src").replace("364X272", "800X600"));
			picPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			picPo.setCreateTime(new Date());
			picPo.setObjType(PictureObjectEnum.DIC.getValue());
			picPo.setPictureType(PictureTypeEnum.BUILDING.getValue());
			picPo.setCompayType(CompanyTypeEnum.MAITIAN.getValue());
			picPo.setIsUpload(YesNoEnum.NO.getValue());
			picPo.setIsDownload(YesNoEnum.NO.getValue());
			xiaoquPicList.add(picPo);
		}
		list.add(0,xiaoquPo);
		list.add(1,xiaoquPicList);
		
		//解析成交记录
		List<DicDealLogsCrawlerPo> dealList=dealChengjiaoLogs( document, mainId);
		list.add(2,dealList);
		return list;
	}
	
	//总页数 
	public Integer getTotalPage(String url){
		Integer res = null;
		Integer pageNum = 10;
		try {
			Document document = Jsoup.connect(url).get();
			Element search_result = document.select(".search_result").first();
			String reString = search_result.getElementsByTag("span").first().text();
			Integer totalNum = Integer.parseInt(reString);
			totalNum = 8049;
			if (totalNum%pageNum>0) {
				res = totalNum/pageNum + 1;
			}else {
				res = totalNum/pageNum;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//获取经纪人手机号
	public String checkCellphone(String str) {
		// 将给定的正则表达式编译到模式中
		Pattern pattern = Pattern.compile("1\\d{10}");
		// 创建匹配给定输入与此模式的匹配器。
		Matcher matcher = pattern.matcher(str);
		// 查找字符串中是否有符合的子字符串
		while (matcher.find()) {
			// 查找到符合的即输出
			return matcher.group();
		}
		return null;
	}
	
	/**
	 * 上传图片
	 * @param path 绝对路径
	 * @return 返回图片url
	 */
	public String uploadImg(String path) {
		File fs = new File(path);
		String extName = path.substring(path.lastIndexOf("."));
		String key = null;
		try {
			// 阿里云
			key = uploadService.uploadImg(fs, extName);
			if (StringUtils.isNotBlank(key)) {
				key = ConfigUtils.instance.getOssAccessUrl() + key;
			}
			LOGGER.info("上传图片完成");
		} catch (Exception e) {
			LOGGER.error("上传出错：", e.getMessage(), e);
		}
		return key;
	}
	
	/**
	 * 下载图片
	 * @param path	保存路径
	 * @param destUrl 目标url
	 */
	public String downloadImg(String path ,String destUrl) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		String fileName = destUrl.substring(destUrl.lastIndexOf("/")+1);
		if (!new File(path).exists()) {
			new File(path).mkdirs();
		}
		byte[] buf = new byte[1024];
		int size = 0;
		try {
			URL url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(path+File.separator+fileName);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassCastException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException e) {
				e.printStackTrace();
				return null;
			}
		}
		return fileName;
	}
	
	public static boolean deleteImg(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                LOGGER.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                LOGGER.error("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            LOGGER.error("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
	
	public String getEsfImgPath() {
		return esfImgPath;
	}

	public void setEsfImgPath(String esfImgPath) {
		this.esfImgPath = esfImgPath;
	}

	public String getXqImgPath() {
		return xqImgPath;
	}

	public void setXqImgPath(String xqImgPath) {
		this.xqImgPath = xqImgPath;
	}
	
	public static void main(String[] args) throws Exception {
		//跑页
		//new MtfcParser().start(1,null);
		//跑单条
		new MaitianBizImpl().dealEsf("IFY00600615");
	}

	@Override
	public void runPageJob() {
		mtfcOldhouseFlagService.deleteAll();
		runPage(1, null);
		masterCrawlerTaskService.updateDynamicTaskByIndex(2);
	}
	
}
