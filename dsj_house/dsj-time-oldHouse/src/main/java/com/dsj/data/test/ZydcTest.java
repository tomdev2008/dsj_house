package com.dsj.data.test;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.common.utils.crawler.DOMParserConfig;


public class ZydcTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(ZydcTest.class);
	
	public static void main(String[] args) {
		       
		int i =1; 
		String pageUrl = "http://bj.centanet.com/ershoufang/g" + i;
		try {
			Document doc = DOMParserConfig.getDocByUrl(pageUrl);
			Element element = (Element) doc.selectSingleNode("//DIV[@class='section-wrap section-houselists']");
			List elements4 = element.elements();
			Element sectionElement = (Element) elements4.get(0);
			List elements = sectionElement.elements();
			
			if (elements.isEmpty()) {
				System.out.println("无数据可抓");
			} else {
				Element e;
				try {
					int x=0;
					/*while (true){*/
						
						e = (Element) elements.get(x);
						
						List elements2 = e.elements();
						Element Ele = (Element)elements2.get(1);
						List elements3 = Ele.elements();
						Element h4 = (Element)elements3.get(0);
						
						//详情连接
						Element element2 = h4.element("A");
						String deltialurl = "http://bj.centanet.com"+element2.attributeValue("href");
						System.out.println("详情连接:"+deltialurl);
						
						//title
						System.out.println("标题:"+element2.attributeValue("title"));
						
						Element houseMsg = (Element)elements3.get(1);
						List houseMsgelements = houseMsg.elements();
						//小区名称
						Element xqName = (Element)houseMsgelements.get(0);
						System.out.println("小区:"+xqName.getTextTrim());
						
						//小区连接
						String xqstr = "http://bj.centanet.com"+xqName.attributeValue("href");
						System.out.println("小区链接:"+xqstr);
						
						//户型
						Element hxName = (Element)houseMsgelements.get(2);
						System.out.println("户型:"+hxName.getTextTrim());
						
						//面积
						Element mjName = (Element)houseMsgelements.get(4);
						System.out.println("面积:"+mjName.getTextTrim());
						
						
						Element houseMsg2 = (Element)elements3.get(2);
						List houseMsg2elements = houseMsg2.elements();
						//楼层信息
						Element lcName = (Element)houseMsg2elements.get(0);
						System.out.println("楼层:"+lcName.getTextTrim());
						
						//方向
						Element fxName = (Element)houseMsg2elements.get(1);
						System.out.println("方向:"+fxName.getTextTrim());
						
						//装修程度
						Element zxName = (Element)houseMsg2elements.get(2);
						System.out.println("装修:"+zxName.getTextTrim());
						
						//年代
						Element ndName = (Element)houseMsg2elements.get(3);
						System.out.println("年代:"+ndName.getTextTrim());
						
						Element houseMsg3 = (Element)elements3.get(3);
						String address = houseMsg3.element("SPAN").getTextTrim();
						
						String[] split = address.split(" ");
						System.out.println("地区:"+split[0].split("-")[0]);
						System.out.println("商圈:"+split[0].split("-")[1]);
						//地址
						System.out.println("地址:"+split[1]);
						Element houseMsg4 = (Element)elements3.get(4);
						//标签
						List bqElements5 = houseMsg4.elements();
						String bq="";
						for (Object object : bqElements5) {
							Element bqName = (Element)object;
							bq+=","+bqName.getTextTrim();
						}
						if(StringUtils.isNotBlank(bq)){
							bq=bq.substring(1);
						}
						System.out.println("标签:"+bq);
						
						
						Element Ele2 = (Element)elements2.get(2);
						
						List ele2elements = Ele2.elements();
						//售价
						Element sjName = (Element)ele2elements.get(0);
						System.out.println("售价:"+sjName.getStringValue().trim());
						//单价
						Element djName = (Element)ele2elements.get(1);
						System.out.println("单价:"+djName.getTextTrim());
						//小区均价
						Element xqdjName = (Element)ele2elements.get(2);
						System.out.println("小区均价:"+xqdjName.getTextTrim().replace("小区均价：", ""));
						
						
						detail(deltialurl);
						xiaoqu(xqstr);
						
						//去重查询
						/* Criteria c = new Criteria("loupan_url").is(loupanUrl);
						Long count = loupanEveryUrlDao.getCount(c);*/
						if(true){
							//System.out.println("准备解析楼盘:" + loupanUrl);
							//添加楼盘地址
							
							//楼盘tab
							
						}else{
							//System.out.println("该楼盘已经解析过----"+loupanUrl);
						}
						/*x++;
						
					}*/

				} catch (Exception e1) {
					LOGGER.info("该页数组越界,添加数据到集合,即将进入下一页", e1.getMessage(), e1);
					/*i++;
					jieXiUrl(i);*/
				}

			}

		} catch (Exception e) {
			LOGGER.info("列表解析错误", e.getMessage(), e);
		}
	}

	private static void detail(String url) {
		Document doc;
		try {
			org.jsoup.nodes.Document docByUrl = DOMJsonpConfig.getDocByUrl(url);
			
			org.jsoup.nodes.Element elementById = docByUrl.getElementById("js_detailphoto");
			//org.jsoup.nodes.Element element3 = docByUrl.getElementsByClass("docByUrl").get(0);
			Elements elementsByTag2 = elementById.getElementsByTag("img");
			for (org.jsoup.nodes.Element element : elementsByTag2) {
				String src = element.attr("src");
				System.out.println(src);
			}
			//首付 月供
			Elements elementsByClass = docByUrl.getElementsByClass("supplement");
			org.jsoup.nodes.Element element2 = elementsByClass.get(0);
			Elements elementsByTag4 = element2.getElementsByTag("span");
			for (org.jsoup.nodes.Element element : elementsByTag4) {
				System.out.println(element.text());
			}
			
			//地图坐标
			Elements elementsByTag3 = docByUrl.getElementsByTag("SCRIPT");
			for (org.jsoup.nodes.Element element : elementsByTag3) {
				String str = element.toString();
				if(str.indexOf("lng")>-1){
					String[] split = str.split("lng:");
					
					String[] split4 = split[0].split("postId: '");
					String newurl =split4[1].split("'")[0];
					System.out.println("postId:"+newurl);
					postId(newurl);
					
					String[] split2 = split[1].split("lat:");
					String lng = split2[0].replaceAll("\"", "").replaceAll(" ", "").replaceAll(",", "");
					System.out.println("经度:"+lng);
					String[] split3 = split2[1].split(",");
					String lat = split3[0].replaceAll(" ", "").replaceAll("\"", "");
					System.out.println("维度:"+lat);
					break;
				}
			}
			
		} catch (Exception e) {
			LOGGER.info("详情解析错误", e.getMessage(), e);
		}
		
	}
	
	//经纪人
	private static void postId(String url) {
		try {
			String str = "http://bj.centanet.com/page/v1/ajax/moredetailcomment.aspx?index=1&posttype=S&postid="+url;
			org.jsoup.nodes.Document docByUrl = DOMJsonpConfig.getDocByUrl(str);
			Elements elementsByTag = docByUrl.getElementsByTag("dl");
			for (org.jsoup.nodes.Element element : elementsByTag) {
				Elements elementsByTag2 = element.getElementsByTag("dt");
				Elements elementsByTag3 = elementsByTag2.get(0).getElementsByTag("img");
				String attr = elementsByTag3.get(0).attr("src");
				System.out.println("头像地址:"+attr);
				Elements elementsByTag4 = elementsByTag2.get(0).getElementsByTag("A");
				String attr2 = elementsByTag4.get(0).attr("href");
				String text = elementsByTag4.get(1).text();
				System.out.println("经纪人链接:"+attr2);
				System.out.println("经纪人名称:"+text);
				Elements elementsByTagdl = element.getElementsByTag("dd");
				Elements elementsByTag5 = elementsByTagdl.get(0).getElementsByTag("SPAN");
				String text2 = elementsByTag5.get(0).text();
				System.out.println("电话:"+text2);
			}
		} catch (Exception e) {
			LOGGER.info("经纪人信息解析错误", e.getMessage(), e);
		}
		
	}
	
	private static void xiaoqu(String url) {
		try {
			org.jsoup.nodes.Document docByUrl = DOMJsonpConfig.getDocByUrl(url);
			org.jsoup.nodes.Element xqImg = docByUrl.getElementById("picBox");
			Elements elementsByClass = xqImg.getElementsByTag("A");
			for (org.jsoup.nodes.Element element : elementsByClass) {
				System.out.println(element.attr("src"));
			}
			org.jsoup.nodes.Element element = docByUrl.getElementsByClass("xiaop").get(0);
			Elements allElements = element.getElementsByClass("addr");
			//物业类型
			org.jsoup.nodes.Element bsicelement0 = (org.jsoup.nodes.Element)allElements.get(0);
			String attrname0 = bsicelement0.getElementsByTag("label").text();
			String attrval0 = bsicelement0.getElementsByTag("SPAN").text();
			System.out.println(attrname0+":"+attrval0);
			//年代
			org.jsoup.nodes.Element bsicelement1 = (org.jsoup.nodes.Element)allElements.get(1);
			String attrname1 = bsicelement1.getElementsByTag("LABEL").text();
			String attrval1 = bsicelement1.getElementsByTag("SPAN").text();
			System.out.println(attrname1+":"+attrval1);
			//物业费
			org.jsoup.nodes.Element bsicelement2 = (org.jsoup.nodes.Element)allElements.get(2);
			String attrname2 = bsicelement2.getElementsByTag("LABEL").text();
			String attrval2 = bsicelement2.getElementsByTag("SPAN").text();
			System.out.println(attrname2+":"+attrval2);
			
			//物业公司
			org.jsoup.nodes.Element bsicelement3 = (org.jsoup.nodes.Element)allElements.get(3);
			String attrname3 = bsicelement3.getElementsByTag("LABEL").text();
			String attrval3 = bsicelement3.getElementsByTag("SPAN").text();
			System.out.println(attrname3+":"+attrval3);
			
			
			org.jsoup.nodes.Element element1 = docByUrl.getElementsByClass("xiaop").get(1);
			
			Elements allElements1 = element1.getElementsByClass("addr");
			
			//开发商
			org.jsoup.nodes.Element lhelement0 = (org.jsoup.nodes.Element)allElements1.get(0);
			String lhattrname0 = lhelement0.getElementsByTag("LABEL").text();
			String lhattrval0 = lhelement0.getElementsByTag("SPAN").text();
			System.out.println(lhattrname0+":"+lhattrval0);
			//容积率
			org.jsoup.nodes.Element lhelement1 = (org.jsoup.nodes.Element)allElements1.get(1);
			String lhattrname1 = lhelement1.getElementsByTag("LABEL").text();
			String lhattrval1 = lhelement1.getElementsByTag("SPAN").text();
			System.out.println(lhattrname1+":"+lhattrval1);
			//绿化率
			org.jsoup.nodes.Element lhelement2 = (org.jsoup.nodes.Element)allElements1.get(2);
			String lhattrname2 = lhelement2.getElementsByTag("LABEL").text();
			String lhattrval2 = lhelement2.getElementsByTag("SPAN").text();
			System.out.println(lhattrname2+":"+lhattrval2);
			
			
			org.jsoup.nodes.Element element2 = docByUrl.getElementsByClass("xiaop").get(2);
			
			Elements allElements2 = element2.getElementsByClass("addr");
			int xq=0;
			//小区特色
			try {
				org.jsoup.nodes.Element xqelement0 = (org.jsoup.nodes.Element)allElements2.get(xq);
				String xqattrname0 = xqelement0.getElementsByTag("LABEL").text();
				String xqattrval0 = xqelement0.getElementsByTag("SPAN").text();
				if(xqattrname0.equals("小区特色")){
					System.out.println(xqattrname0+":"+xqattrval0);
					xq++;
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("小区特色抓取异常");
			}
			//小区描述
			try {
				org.jsoup.nodes.Element xqelement1 = (org.jsoup.nodes.Element)allElements2.get(xq);
				String xqattrname1 = xqelement1.getElementsByTag("LABEL").text();
				String xqattrval1 = xqelement1.getElementsByTag("SPAN").text();
				System.out.println(xqattrname1+":"+xqattrval1);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("小区描述抓取异常");
			}
			
			org.jsoup.nodes.Element pscele = docByUrl.getElementById("picBox");
			Elements elementsByTag = pscele.getElementsByTag("IMG");
			for (org.jsoup.nodes.Element element3 : elementsByTag) {
				System.out.println(element3.attr("data-src"));
			}
		
		} catch (Exception e) {
			LOGGER.info("小区信息解析错误", e.getMessage(), e);
		}
		
	}
	
	/*private static void yuegong(String url) {
		Document doc;
		try {
			org.jsoup.nodes.Document docByUrl = DOMJsonpConfig.getDocByUrl(url);
			org.jsoup.nodes.Element element = docByUrl.getElementsByClass("count_table f14").get(0);
			Elements elementsByTag = element.getElementsByTag("tr");
			//首付款
			org.jsoup.nodes.Element element2 = elementsByTag.get(0);
			Elements elementsByTag2 = element2.getElementsByTag("td");
			String sfname = elementsByTag2.get(0).text();
			String sfval = elementsByTag2.get(1).ownText();
			System.out.println(sfname+":"+sfval);
			
			//月供
			org.jsoup.nodes.Element element11 = elementsByTag.get(1);
			Elements elementsByTag11 = element11.getElementsByTag("td");
			String ygname = elementsByTag11.get(0).text();
			String ygval = elementsByTag11.get(1).toString();
			System.out.println(ygname+":"+ygval);
			
			//贷款金额
			org.jsoup.nodes.Element element22 = elementsByTag.get(2);
			Elements elementsByTag22 = element22.getElementsByTag("td");
			String dkname = elementsByTag22.get(0).text();
			String dkval = elementsByTag22.get(1).text();
			System.out.println(dkname+":"+dkval);
			
			//贷款月数
			org.jsoup.nodes.Element element33 = elementsByTag.get(3);
			Elements elementsByTag33 = element33.getElementsByTag("td");
			String dkysname = elementsByTag33.get(0).text();
			String dkysval = elementsByTag33.get(1).text();
			System.out.println(dkysname+":"+dkysval);
			
			//总计利息
			org.jsoup.nodes.Element element44 = elementsByTag.get(4);
			Elements elementsByTag44 = element44.getElementsByTag("td");
			String lxname = elementsByTag44.get(0).text();
			String lxval = elementsByTag44.get(1).text();
			System.out.println(lxname+":"+lxval);
			
			//本息合计
			org.jsoup.nodes.Element element55 = elementsByTag.get(5);
			Elements elementsByTag55 = element55.getElementsByTag("td");
			String bxname = elementsByTag55.get(0).text();
			String bxval = elementsByTag55.get(1).text();
			System.out.println(bxname+":"+bxval);
		} catch (Exception e) {
			LOGGER.info("月供信息解析错误", e.getMessage(), e);
		}
		
	}*/
}
