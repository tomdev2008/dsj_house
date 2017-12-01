package com.dsj.data.lianjia.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.common.utils.crawler.DOMParserConfig;
import com.dsj.data.lianjia.biz.ZydcBiz;
import com.dsj.modules.oldHouseParser.po.HouseAgentCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.ZydcOldhouseFlagPo;
import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;
import com.dsj.modules.oldHouseParser.service.MasterCrawlerTaskService;
import com.dsj.modules.oldHouseParser.service.ZydcOldhouseFlagService;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;

@Service
public class ZydcBizImpl implements ZydcBiz{
	private static final Logger LOGGER = LoggerFactory.getLogger(ZydcBizImpl.class);
	
	
	@Autowired
	private HouseMasterCrawlerService HouseMasterCrawlerService;

	@Autowired
	private ZydcOldhouseFlagService zydcOldhouseFlagService;
	
	@Autowired
	private MasterCrawlerTaskService masterCrawlerTaskService;

	public void list() {
		ZydcOldhouseFlagPo po = zydcOldhouseFlagService.getLastPo();
		if (null != po) {
			jieXiUrl(po.getPageflag(), po.getPagenum());
		} else {
			jieXiUrl(1, 0);
		}

	}

	public void jieXiUrl(int i, int x) {

		while (true) {
			if(x<25){
				try {
					boolean result = startJx(i,x);
					if(!result){//如果没有数据
						break;
					}else{
						x++;
					}
				} catch (Exception e1) {
					x++;
					LOGGER.info("此条数据有问题", e1.getMessage(), e1);
				}
			}else{
				i++;
				jieXiUrl(i,0);
				break;
			}
			
		}
		LOGGER.info("-------------处理完毕!------------");
	}

	public boolean startJx(int i, int x) throws Exception {

		// 解析开始
		String pageUrl = "http://bj.centanet.com/ershoufang/g" + i;
		Document doc = DOMParserConfig.getDocByUrl(pageUrl);
		Element element = (Element) doc.selectSingleNode("//DIV[@class='section-wrap section-houselists']");
		List elements4 = element.elements();
		Element sectionElement = (Element) elements4.get(0);
		List elements = sectionElement.elements();
		if (elements.isEmpty()) {
			LOGGER.info("无数据可抓");
			return false;
		}else{
			Element e = (Element) elements.get(x);
			List elements2 = e.elements();
			Element Ele = (Element) elements2.get(1);
			List elements3 = Ele.elements();
			Element h4 = (Element) elements3.get(0);

			// 详情连接
			Element element2;
			String deltialurl = "";
			String oldId = "";
			element2 = h4.element("A");
			deltialurl = "http://bj.centanet.com" + element2.attributeValue("href");
			oldId = deltialurl.substring(34, deltialurl.length());
			oldId = oldId.substring(0, oldId.length() - 5);
			System.out.println("详情连接:" + deltialurl);
			System.out.println("原始id:" + oldId);

			// title ok
			String title = "";
			title = element2.attributeValue("title");
			System.out.println("标题:" + title);

			Element houseMsg = (Element) elements3.get(1);
			List houseMsgelements = houseMsg.elements();
			// 小区名称 ok
			Element xqName = null;
			String xqText = "";
			xqName = (Element) houseMsgelements.get(0);
			xqText = xqName.getTextTrim();
			System.out.println("小区:" + xqText);

			// 小区连接
			String xqstr = null;
			String xiquStr = xqName.attributeValue("href");
			
			//小区编码
			xqstr = xiquStr.substring(8,xiquStr.length()-1);
			System.out.println("小区编码:" + xqstr);
			
			xiquStr = "http://bj.centanet.com" + xiquStr;
			System.out.println("小区链接:" + xqstr);
			
			
			// 室 ok 厅 ok
			String room = null;
			String hall = null;
			Element hxName = (Element) houseMsgelements.get(2);
			String hxtext = hxName.getTextTrim();
			room = null;
			hall = null;
			if (StringUtils.isNotBlank(hxtext)) {
				room = hxtext.split("室")[0];
				hall = hxtext.split("室")[1];
				if (StringUtils.isNotBlank(hall)) {
					hall = hall.split("厅")[0];
				}
			}
			System.out.println("室:" + room);
			System.out.println("厅:" + hall);

			// 面积 ok
			String mjText = null;
			Element mjName = (Element) houseMsgelements.get(4);
			mjText = mjName.getTextTrim().replace("平", "");
			System.out.println("面积:" + mjText);

			Element houseMsg2 = (Element) elements3.get(2);
			List houseMsg2elements = houseMsg2.elements();
			// 楼层类型 楼层总数 ok
			Integer floorType = null;
			Integer floorNum = null;
			Element lcName = (Element) houseMsg2elements.get(0);
			String textTrim = lcName.getTextTrim();
			floorType = null;
			floorNum = null;
			if (StringUtils.isNotBlank(textTrim)) {
				if (textTrim.indexOf("高") > -1) {
					floorType = 3;
					String totalCeng = textTrim.split("/")[1].split("层")[0];
					if (StringUtils.isNotBlank(totalCeng)) {
						floorNum = Integer.parseInt(totalCeng);
					}
				} else if (textTrim.indexOf("中") > -1) {
					floorType = 2;
					String totalCeng = textTrim.split("/")[1].split("层")[0];
					if (StringUtils.isNotBlank(totalCeng)) {
						floorNum = Integer.parseInt(totalCeng);
					}
				} else if (textTrim.indexOf("低") > -1) {
					floorType = 1;
					String totalCeng = textTrim.split("/")[1].split("层")[0];
					if (StringUtils.isNotBlank(totalCeng)) {
						floorNum = Integer.parseInt(totalCeng);
					}
				}
			}
			System.out.println("楼层类型:" + floorType);
			System.out.println("楼层总数:" + floorNum);
			// 方向 ok
			String fxText = null;
			Element fxName = (Element) houseMsg2elements.get(1);
			fxText = fxName.getTextTrim();
			System.out.println("方向:" + fxText);

			// 装修程度 ok
			String zxText = null;
			Element zxName = (Element) houseMsg2elements.get(2);
			zxText = zxName.getTextTrim();
			System.out.println("装修:" + zxText);

			// 年代 ok
			String ndText = null;
			Element ndName = (Element) houseMsg2elements.get(3);
			ndText = ndName.getTextTrim();
			System.out.println("年代:" + ndText);

			Element houseMsg3 = (Element) elements3.get(3);
			String address = houseMsg3.element("SPAN").getTextTrim();
			// 地址 地区 商圈 ok
			String dq = null;
			String sq = null;
			String bq = null;
			String[] split = address.split(" ");
			String address1 = split[1];
			dq = split[0].split("-")[0];
			sq = split[0].split("-")[1];
			System.out.println("地区:" + dq);
			System.out.println("商圈:" + sq);
			System.out.println("地址:" + address1);
			Element houseMsg4 = (Element) elements3.get(4);
			// 标签 ok
			List bqElements5 = houseMsg4.elements();
			bq = "";
			for (Object object : bqElements5) {
				Element bqName = (Element) object;
				bq += "," + bqName.getTextTrim();
			}
			if (StringUtils.isNotBlank(bq)) {
				bq = bq.substring(1);
			}
			System.out.println("标签:" + bq);

			Element Ele2 = (Element) elements2.get(2);

			List ele2elements = Ele2.elements();
			// 售价 ok
			String price = null;
			Element sjName = (Element) ele2elements.get(0);
			price = sjName.getStringValue().trim().replace("万", "");
			System.out.println("售价:" + price);

			// 单价 ok
			String unitPrice = null;
			Element djName = (Element) ele2elements.get(1);
			unitPrice = djName.getTextTrim().replace("元/平", "");
			System.out.println("单价:" + unitPrice);

			// 小区均价 ok
			String averagePrice = null;
			Element xqdjName = (Element) ele2elements.get(2);
			averagePrice = xqdjName.getTextTrim().replace("小区均价：", "").replace("元/平", "");
			System.out.println("小区均价:" + averagePrice);

			// 开始解析详情
			HashMap<String, Object> detail = detail(deltialurl, oldId);
			// 二手房图片列表
			List<OldHousePictureCrawlerPo> housePicList = (List<OldHousePictureCrawlerPo>) detail.get("housePicList");
			

			// 首付 ok
			Double payments = null;
			try {
				if (null != detail.get("sf")) {
					payments = Double.parseDouble(detail.get("sf").toString());
				}
			} catch (Exception e1) {
				LOGGER.error("首付 解析错误:{}", e1);
			}
			// 月供 ok
			Double monthpay = null;
			try {
				monthpay = null;
				if (null != detail.get("yg")) {
					monthpay = Double.parseDouble(detail.get("yg").toString());
				}
				;
			} catch (Exception e1) {
				LOGGER.error("月供转换错误:{}", e1);
			}
			HouseAgentCrawlerPo agent = null;
			// 经纪人 ok
			if (null != detail.get("agent")) {
				agent = (HouseAgentCrawlerPo) detail.get("agent");
			}

			// 经度ok
			String lng = "";
			if (null != detail.get("lng")) {
				lng = detail.get("lng").toString();
			}
			;
			// 维度ok
			String lat = "";
			if (null != detail.get("lat")) {
				lat = detail.get("lat").toString();
			}
			;
			// 开始解析小区
			HashMap<String, Object> xiaoquHashMap = xiaoqu(xiquStr, oldId);
			
			// 小区图片列表
			List<HousePictureCrawlerPo> dicPicList = (List<HousePictureCrawlerPo>) xiaoquHashMap.get("dicPicList");
			
			// 物业类型 ok
			String houseType = "";
			if (null != xiaoquHashMap.get("houseType")) {
				houseType = xiaoquHashMap.get("houseType").toString();
			}
			// 物业费 ok
			String propertyFee = "";
			if (null != xiaoquHashMap.get("propertyFee")) {
				if(!"暂无".equals(xiaoquHashMap.get("propertyFee").toString())){
					propertyFee = xiaoquHashMap.get("propertyFee").toString();
				}
			}
			// 物业公司 ok
			String propertyCompany = "";
			if (null != xiaoquHashMap.get("propertyCompany")) {
				if(!"暂无".equals(xiaoquHashMap.get("propertyCompany").toString())){
					propertyCompany = xiaoquHashMap.get("propertyCompany").toString();
				}
			}
			// 开发商 ok
			String developer = "";
			if (null != xiaoquHashMap.get("developer")) {
				if(!"暂无".equals(xiaoquHashMap.get("developer").toString())){
					developer = xiaoquHashMap.get("developer").toString();
				}
			}
			// 容积率 ok
			String plotRatio = "";
			if (null != xiaoquHashMap.get("plotRatio")) {
				if(!"暂无".equals(xiaoquHashMap.get("plotRatio").toString())){
					plotRatio = xiaoquHashMap.get("plotRatio").toString();
				}
			}
			// 绿化率 ok
			String greenRate = "";
			if (null != xiaoquHashMap.get("greenRate")) {
				if(!"暂无".equals(xiaoquHashMap.get("greenRate").toString())){
					greenRate = xiaoquHashMap.get("greenRate").toString().replace("%", "");
				}
			}

			// ---------------------------------------po赋值-----------------------------------------

			// 断点表实体
			ZydcOldhouseFlagPo zydcOldhouseFlagPo = new ZydcOldhouseFlagPo();
			zydcOldhouseFlagPo.setPageflag(i);
			zydcOldhouseFlagPo.setPageurl(pageUrl);
			zydcOldhouseFlagPo.setPagenum(x);
			zydcOldhouseFlagPo.setUrl(deltialurl);

			// 小区实体
			HouseAlikeCommunityPo houseAlikeCommunityPo = new HouseAlikeCommunityPo();
			//小区编码
			houseAlikeCommunityPo.setOriginCommunityId(xqstr);
			// 商圈
			houseAlikeCommunityPo.setTradingAreaName(sq);
			
			// 三级地区
			if(dq.equals("延庆")||dq.equals("密云")){
				dq += "县";
			}else{
				dq += "区";
			}
			houseAlikeCommunityPo.setAreaName3(dq);
			//一级地区
			houseAlikeCommunityPo.setAreaName1("北京市");
			//二级地区 
			houseAlikeCommunityPo.setAreaName2("北京市");
			//一级地区编码
			houseAlikeCommunityPo.setAreaCode1("110000");
			//二级地区编码
			houseAlikeCommunityPo.setAreaCode2("110100");
			
			// 小区名称
			houseAlikeCommunityPo.setName(xqText);
			// 小区均价
			houseAlikeCommunityPo.setAveragePrice(averagePrice);
			// 小区经度
			houseAlikeCommunityPo.setAccuracy(lng);
			// 小区维度
			houseAlikeCommunityPo.setDimension(lat);
			// 物业费
			houseAlikeCommunityPo.setPropertyFee(propertyFee);
			// 物业公司
			houseAlikeCommunityPo.setPropertyCompany(propertyCompany);
			// 开发商
			houseAlikeCommunityPo.setDeveloper(developer);
			// 容积率
			houseAlikeCommunityPo.setPlotRatio(plotRatio);
			// 绿化率
			houseAlikeCommunityPo.setGreenRate(greenRate);

			// 二手房实体
			HouseMasterCrawlerPo houseMasterCrawlerPo = new HouseMasterCrawlerPo();
			//小区编码
			houseMasterCrawlerPo.setOriginDicId(xqstr);
			//公司类型
			houseMasterCrawlerPo.setCompanyType(CompanyTypeEnum.ZHONGYUAN.getValue());
			// 原始房源id
			houseMasterCrawlerPo.setOriginHouseId(oldId);
			// 标题
			houseMasterCrawlerPo.setTitle(title);
			// 室
			if (StringUtils.isNotBlank(room)) {
				houseMasterCrawlerPo.setRoom(Integer.parseInt(room));
			}
			// 厅
			if (StringUtils.isNotBlank(hall)) {
				houseMasterCrawlerPo.setHall(Integer.parseInt(hall));
			}
			// 装修
			houseMasterCrawlerPo.setRenovationName(zxText);
			// 建筑面积
			try {
				if (StringUtils.isNotBlank(mjText)) {
					houseMasterCrawlerPo.setBuildArea(Double.parseDouble(mjText.replace("平", "")));
				}
			} catch (Exception e1) {
				LOGGER.error("建筑面积赋值错误:{}", e1);
			}
			// 建筑年代
			if (StringUtils.isNotBlank(ndText)) {
				houseMasterCrawlerPo.setBuildYear(ndText);
				houseAlikeCommunityPo.setBuiltYear(ndText);
			}
			// 特色
			if (StringUtils.isNotBlank(bq)) {
				houseMasterCrawlerPo.setFeaturesName(bq);
			}
			// 朝向
			if (StringUtils.isNotBlank(fxText)) {
				houseMasterCrawlerPo.setOrientationsName(fxText);
			}
			// 楼层
			houseMasterCrawlerPo.setFloorType(floorType);
			// 楼层数
			houseMasterCrawlerPo.setFloorNum(floorNum);
			// 单价
			try {
				if (StringUtils.isNotBlank(unitPrice)) {
					houseMasterCrawlerPo.setUnitPrice(Double.parseDouble(unitPrice));
				}
			} catch (Exception e2) {
				LOGGER.error("单价赋值错误:{}", e2);
			}
			// 售价
			try {
				if (StringUtils.isNotBlank(price)) {
					houseMasterCrawlerPo.setPrice(Double.parseDouble(price));
				}
			} catch (Exception e1) {
				LOGGER.error("售价赋值错误:{}", e1);
			}
			// 首付
			houseMasterCrawlerPo.setPayments(payments);
			// 月供
			houseMasterCrawlerPo.setMonthpay(monthpay);
			// 房屋类型
			houseMasterCrawlerPo.setHouseTypeName(houseType);

		   HouseMasterCrawlerService.saveHouseMasterCrawler(houseMasterCrawlerPo,
			 houseAlikeCommunityPo, dicPicList, housePicList,
			 agent, CompanyTypeEnum.ZHONGYUAN);
			// 插入断点
			zydcOldhouseFlagService.saveDynamic(zydcOldhouseFlagPo);
			return true;
		}
		
	}

	public HashMap<String, Object> detail(String url, String oldId) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<OldHousePictureCrawlerPo> list = new ArrayList<OldHousePictureCrawlerPo>();
		Document doc;
		org.jsoup.nodes.Document docByUrl = DOMJsonpConfig.getDocByUrl(url);

		org.jsoup.nodes.Element elementById = docByUrl.getElementById("js_detailphoto");
		Elements elementsByTag2 = elementById.getElementsByTag("img");

		// 房子图片
		for (org.jsoup.nodes.Element element : elementsByTag2) {
			String src = element.attr("data-src");
			System.out.println("房屋图:" + src);
			OldHousePictureCrawlerPo oldHousePictureCrawlerPo = new OldHousePictureCrawlerPo();
			oldHousePictureCrawlerPo.setCreateTime(new Date());
			oldHousePictureCrawlerPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			oldHousePictureCrawlerPo.setOriginObjId(oldId);
			oldHousePictureCrawlerPo.setOriginPictureUrl(src);
			oldHousePictureCrawlerPo.setCompayType(CompanyTypeEnum.ZHONGYUAN.getValue());
			oldHousePictureCrawlerPo.setIsDownload(2);
			oldHousePictureCrawlerPo.setIsUpload(2);
			oldHousePictureCrawlerPo.setPictureType(1);// 默认室内图
			list.add(oldHousePictureCrawlerPo);
		}
		hashMap.put("housePicList", list);

		// 首付 月供 ok
		Elements elementsByClass = docByUrl.getElementsByClass("supplement");
		org.jsoup.nodes.Element element2 = elementsByClass.get(0);
		Elements elementsByTag4 = element2.getElementsByTag("span");
		for (org.jsoup.nodes.Element element : elementsByTag4) {
			if (element.text().indexOf("首付") > -1) {
				hashMap.put("sf", element.text().replace("首付", "").replace("万", ""));
			}
			if (element.text().indexOf("月供") > -1) {
				hashMap.put("yg", element.text().replace("月供", "").replace("元", ""));
			}
		}

		// 地图坐标
		Elements elementsByTag3 = docByUrl.getElementsByTag("SCRIPT");
		for (org.jsoup.nodes.Element element : elementsByTag3) {
			String str = element.toString();
			if (str.indexOf("lng") > -1) {
				String[] split = str.split("lng:");

				String[] split4 = split[0].split("postId: '");
				String newurl = split4[1].split("'")[0];
				System.out.println("postId:" + newurl);
				// 经纪人
				HouseAgentCrawlerPo agent = postId(newurl, oldId);
				hashMap.put("agent", agent);

				String[] split2 = split[1].split("lat:");
				String lng = split2[0].replaceAll("\"", "").replaceAll(" ", "").replaceAll(",", "");
				System.out.println("经度:" + lng);
				hashMap.put("lng", lng);

				String[] split3 = split2[1].split(",");
				String lat = split3[0].replaceAll(" ", "").replaceAll("\"", "");
				System.out.println("维度:" + lat);
				hashMap.put("lat", lat);
				break;
			}
		}

		return hashMap;

	}

	// 经纪人
	private HouseAgentCrawlerPo postId(String url, String oldId) throws Exception {
		// 获取第一个经纪人
		HouseAgentCrawlerPo houseAgentCrawlerPo = new HouseAgentCrawlerPo();

		String str = "http://bj.centanet.com/page/v1/ajax/moredetailcomment.aspx?index=1&posttype=S&postid=" + url;
		org.jsoup.nodes.Document docByUrl = DOMJsonpConfig.getDocByUrl(str);
		Elements elementsByTag = docByUrl.getElementsByTag("dl");

		for (org.jsoup.nodes.Element element : elementsByTag) {
			Elements elementsByTag2 = element.getElementsByTag("dt");
			Elements elementsByTag3 = elementsByTag2.get(0).getElementsByTag("img");
			String attr = elementsByTag3.get(0).attr("src");
			System.out.println("头像地址:" + attr);
			Elements elementsByTag4 = elementsByTag2.get(0).getElementsByTag("A");
			String attr2 = elementsByTag4.get(0).attr("href");
			String text = elementsByTag4.get(1).text();
			System.out.println("经纪人链接:" + attr2);
			System.out.println("经纪人名称:" + text);
			Elements elementsByTagdl = element.getElementsByTag("dd");
			Elements elementsByTag5 = elementsByTagdl.get(0).getElementsByTag("SPAN");
			String text2 = elementsByTag5.get(0).text();
			System.out.println("电话:" + text2);

			houseAgentCrawlerPo.setOriginHouseId(oldId);
			houseAgentCrawlerPo.setAgentName(text);
			houseAgentCrawlerPo.setAgentPhone(text2);
			houseAgentCrawlerPo.setCompanyType(4);
			houseAgentCrawlerPo.setOriginAgentPicUrl(attr);
			houseAgentCrawlerPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			houseAgentCrawlerPo.setCreateTime(new Date());
			break;
		}
		return houseAgentCrawlerPo;
	}

	public HashMap<String, Object> xiaoqu(String url, String oldId) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<HousePictureCrawlerPo> dicPicList = new ArrayList<HousePictureCrawlerPo>();
		org.jsoup.nodes.Document docByUrl = DOMJsonpConfig.getDocByUrl(url);
		org.jsoup.nodes.Element element = docByUrl.getElementsByClass("xiaop").get(0);
		Elements allElements = element.getElementsByClass("addr");
		// 物业类型 ok
		org.jsoup.nodes.Element bsicelement0 = (org.jsoup.nodes.Element) allElements.get(0);
		String attrname0 = bsicelement0.getElementsByTag("label").text();
		String attrval0 = bsicelement0.getElementsByTag("SPAN").text();
		System.out.println(attrname0 + ":" + attrval0);
		hashMap.put("houseType", attrval0);
		// 年代 ok
		org.jsoup.nodes.Element bsicelement1 = (org.jsoup.nodes.Element) allElements.get(1);
		String attrname1 = bsicelement1.getElementsByTag("LABEL").text();
		String attrval1 = bsicelement1.getElementsByTag("SPAN").text();
		System.out.println(attrname1 + ":" + attrval1);
		// 物业费 ok
		org.jsoup.nodes.Element bsicelement2 = (org.jsoup.nodes.Element) allElements.get(2);
		String attrname2 = bsicelement2.getElementsByTag("LABEL").text();
		String attrval2 = bsicelement2.getElementsByTag("SPAN").text();
		System.out.println(attrname2 + ":" + attrval2);
		hashMap.put("propertyFee", attrval2);
		// 物业公司 ok
		org.jsoup.nodes.Element bsicelement3 = (org.jsoup.nodes.Element) allElements.get(3);
		String attrname3 = bsicelement3.getElementsByTag("LABEL").text();
		String attrval3 = bsicelement3.getElementsByTag("SPAN").text();
		System.out.println(attrname3 + ":" + attrval3);
		hashMap.put("propertyCompany", attrval3);

		org.jsoup.nodes.Element element1 = docByUrl.getElementsByClass("xiaop").get(1);

		Elements allElements1 = element1.getElementsByClass("addr");

		// 开发商 ok
		org.jsoup.nodes.Element lhelement0 = (org.jsoup.nodes.Element) allElements1.get(0);
		String lhattrname0 = lhelement0.getElementsByTag("LABEL").text();
		String lhattrval0 = lhelement0.getElementsByTag("SPAN").text();
		System.out.println(lhattrname0 + ":" + lhattrval0);
		hashMap.put("developer", lhattrval0);
		// 容积率 ok
		org.jsoup.nodes.Element lhelement1 = (org.jsoup.nodes.Element) allElements1.get(1);
		String lhattrname1 = lhelement1.getElementsByTag("LABEL").text();
		String lhattrval1 = lhelement1.getElementsByTag("SPAN").text();
		System.out.println(lhattrname1 + ":" + lhattrval1);
		hashMap.put("plotRatio", lhattrval1);
		// 绿化率 ok
		org.jsoup.nodes.Element lhelement2 = (org.jsoup.nodes.Element) allElements1.get(2);
		String lhattrname2 = lhelement2.getElementsByTag("LABEL").text();
		String lhattrval2 = lhelement2.getElementsByTag("SPAN").text();
		System.out.println(lhattrname2 + ":" + lhattrval2);
		hashMap.put("greenRate", lhattrval2);

		/*
		 * org.jsoup.nodes.Element element2 =
		 * docByUrl.getElementsByClass("xiaop").get(2);
		 * 
		 * Elements allElements2 = element2.getElementsByClass("addr"); int
		 * xq=0; //小区特色 不用 try { org.jsoup.nodes.Element xqelement0 =
		 * (org.jsoup.nodes.Element)allElements2.get(xq); String xqattrname0 =
		 * xqelement0.getElementsByTag("LABEL").text(); String xqattrval0 =
		 * xqelement0.getElementsByTag("SPAN").text();
		 * if(xqattrname0.equals("小区特色")){
		 * System.out.println(xqattrname0+":"+xqattrval0); xq++; }
		 * 
		 * } catch (Exception e1) { e1.printStackTrace();
		 * System.out.println("小区特色抓取异常"); }
		 */
		// 小区描述 暂时不要
		/*
		 * try { org.jsoup.nodes.Element xqelement1 =
		 * (org.jsoup.nodes.Element)allElements2.get(xq); String xqattrname1 =
		 * xqelement1.getElementsByTag("LABEL").text(); String xqattrval1 =
		 * xqelement1.getElementsByTag("SPAN").text();
		 * System.out.println(xqattrname1+":"+xqattrval1); } catch (Exception e)
		 * { e.printStackTrace(); System.out.println("小区描述抓取异常"); }
		 */

		// 小区图片
		org.jsoup.nodes.Element pscele = docByUrl.getElementById("picBox");
		Elements elementsByTag = pscele.getElementsByTag("IMG");
		for (org.jsoup.nodes.Element element3 : elementsByTag) {
			String src = element3.attr("data-src");
			HousePictureCrawlerPo housePictureCrawlerPo = new HousePictureCrawlerPo();
			housePictureCrawlerPo.setCreateTime(new Date());
			housePictureCrawlerPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			housePictureCrawlerPo.setOriginObjId(oldId);
			housePictureCrawlerPo.setCompayType(CompanyTypeEnum.ZHONGYUAN.getValue());
			housePictureCrawlerPo.setOriginPictureUrl(src);
			housePictureCrawlerPo.setPictureType(4);// 默认楼盘图
			housePictureCrawlerPo.setIsDownload(2);
			housePictureCrawlerPo.setIsUpload(2);
			dicPicList.add(housePictureCrawlerPo);
			System.out.println("小区图:" + src);
		}
		hashMap.put("dicPicList", dicPicList);
		return hashMap;
	}

	@Override
	public void listJob() {
		zydcOldhouseFlagService.deleteAll();
		list();
		masterCrawlerTaskService.updateDynamicTaskByIndex(3);
	}
}
