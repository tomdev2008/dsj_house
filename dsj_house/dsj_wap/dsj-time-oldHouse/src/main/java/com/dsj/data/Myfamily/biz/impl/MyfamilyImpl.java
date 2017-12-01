package com.dsj.data.Myfamily.biz.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.data.Myfamily.biz.MyfamilyBiz;
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
public class MyfamilyImpl implements MyfamilyBiz{
	private final static Logger LOGGER = LoggerFactory.getLogger(MyfamilyImpl.class);
	    @Autowired
		private ZydcOldhouseFlagService ZydcOldhouseFlagService;
	    @Autowired
	    private HouseMasterCrawlerService houseMasterCrawlerService;
	    
	    @Autowired
	    MasterCrawlerTaskService masterCrawlerTaskService;
	  
	    public void jiexiLoupan(int i){
			if(i<332){
		 		String pageUrl = "http://bj.5i5j.com/exchange/n" + i;
				try {
					Document doc = Jsoup.connect(pageUrl).get();
					List<Element> element = doc.select("div.list-body > ul > li");
					System.out.println(element.size());
					if(element.isEmpty()){
						System.out.println("没有可抓取的数据");
					}else{
						try {
							int x=0;
							for (Element ele : element) {
								x++;
								System.out.println(x);
								String href=ele.getElementsByTag("a").attr("href");
								String loupanUrl="http://bj.5i5j.com"+href;
								System.out.println(loupanUrl);
								List<ZydcOldhouseFlagPo> count = ZydcOldhouseFlagService.getCount(loupanUrl);
								if(count.size()==0){
									//楼盘tab
									System.out.println("准备解析楼盘:" + loupanUrl);
									HouseMasterCrawlerPo tabModal = jieXiTab(loupanUrl,i);
									tabModal.setCompanyType(CompanyTypeEnum.WAWJ.getValue());
									//添加楼盘地址
									ZydcOldhouseFlagPo model = new ZydcOldhouseFlagPo();
									model.setUrl(loupanUrl);
									model.setPagenum(i);
									model.setPageflag(x);
									model.setPageurl(pageUrl);
									//图片实体类
									List<OldHousePictureCrawlerPo> oldHousePicture=tabModal.getOldHousePicture();
									//小区实体类 
									HouseAlikeCommunityPo  houseAlikeCommunity=tabModal.getHouseAlikeCommunity();
									//经纪人实体类
									HouseAgentCrawlerPo agentCrawler=tabModal.getAgentCrawler();
									//小区图片实体类
									List<HousePictureCrawlerPo> pictureCrawler=tabModal.getPictureCrawler();
									if(houseAlikeCommunity.getTradingAreaName()==null){
										
									}else{
										if(oldHousePicture==null || houseAlikeCommunity==null || agentCrawler==null || pictureCrawler==null){
											
										}else{
											try {
												houseMasterCrawlerService.saveHouseMasterCrawler(tabModal, houseAlikeCommunity, pictureCrawler, oldHousePicture, agentCrawler, CompanyTypeEnum.getEnum(CompanyTypeEnum.WAWJ.getValue()));
												ZydcOldhouseFlagService.insertZydc(model);
											} catch (Exception e) {
												ZydcOldhouseFlagService.insertZydc(model);
											}
											
										}
									}
								}else{
									System.out.println("该楼盘已经解析过----"+loupanUrl);
								}
								
								if(x==element.size()){
										i++;
										jiexiLoupan(i);
								}
							}
							
						} catch (Exception e1) {
							System.err.println("该页错误");
							jiexiLoupan(i+1);
						}
					}
				} catch (Exception e) {
					LOGGER.info("解析错误", e.getMessage(), e);
					jiexiLoupan(i+1);
				}
			}else{
				System.err.println("解析结束");
			}
			
			
		}
		
	    private static HouseMasterCrawlerPo jieXiTab(String loupanUrl, Integer i) throws IOException, SAXException {
	    	String loupanUrls=loupanUrl;
	    	Integer is=i;
	    	HouseMasterCrawlerPo familyDetailModel =new HouseMasterCrawlerPo();
	    	try {
	    		    Document doc = Jsoup.connect(loupanUrl).get();
					Elements element=doc.getElementsByClass("house-main");
					if(element!=null){
						Elements pictureLists=element.get(0).getElementsByClass("lb-small-pic mask");
						String title=element.get(0).getElementsByClass("house-tit").get(0).text();
						System.out.println(title+"----标题");
						familyDetailModel.setTitle(title);
						String querys=element.get(0).getElementsByClass("house-code").get(0).getElementsByTag("p").text();
						String[] query=null;
						if(StringUtils.isNotEmpty(querys)){
							query=querys.split("：");
							String[] houseID=null;
							houseID=query[1].split(" ");
							//房源Id
							familyDetailModel.setOriginHouseId(query[2]);
							System.out.println(query[2]+"----房源Id");
						}
						HouseAgentCrawlerPo agent= new HouseAgentCrawlerPo();
						//经纪人姓名
						String agentsName=element.get(0).getElementsByClass("house-broker-info").get(0).getElementsByTag("dd").get(0).getElementsByTag("p").get(0).text();
						agent.setAgentName(agentsName);
						System.out.println(agentsName+"----经纪人姓名");
						//经纪人电话
						String agentTellphone=element.get(0).getElementsByClass("house-broker-info").get(0).getElementsByTag("dd").get(0).getElementsByTag("p").get(1).text();
						agent.setAgentPhone(agentTellphone);
						System.out.println(agentTellphone+"----经纪人电话");
						agent.setOriginHouseId(query[2]);
						String agentImg=element.get(0).getElementsByClass("house-broker-info").get(0).getElementsByTag("dd").get(0).getElementsByTag("img").attr("src");
						agent.setOriginAgentPicUrl(agentImg);
						System.out.println(agentImg+"----经纪人头像");
						agent.setCompanyType(CompanyTypeEnum.WAWJ.getValue());
						agent.setDeleteFlag(2);
						agent.setCreateTime(new Date());
						familyDetailModel.setAgentCrawler(agent);
						List<OldHousePictureCrawlerPo> pictureListPo=new ArrayList<>();
						if(pictureLists.size()>0){
							Elements  pictureList=pictureLists.get(0).getElementsByTag("img");
							if(pictureList!=null &&pictureList.size()>0){
								OldHousePictureCrawlerPo familyPicture=null;
			    				for (Element element2 : pictureList) {
			    				   familyPicture=new OldHousePictureCrawlerPo();
			    					String img=element2.getElementsByTag("img").attr("src");
			    					System.out.println(img+"----img");
			    					familyPicture.setOriginPictureUrl(img);
			    					familyPicture.setOriginObjId(query[2]);
			    					familyPicture.setCompayType(CompanyTypeEnum.WAWJ.getValue());
			    					familyPicture.setDeleteFlag(2);
			    					familyPicture.setCreateTime(new Date());
			    					familyPicture.setPictureType(1);
			    					familyPicture.setIsDownload(2);
			    					familyPicture.setIsUpload(2);
			    					pictureListPo.add(familyPicture);
			    				}
			    				familyDetailModel.setOldHousePicture(pictureListPo);
			                }
						}
						String price=element.get(0).getElementsByClass("house-info").get(0).getElementsByTag("li").get(0).getElementsByTag("span").html();
						if(StringUtils.isNotEmpty(price)){
							//售价
							familyDetailModel.setPrice(Double.parseDouble(price));
							System.out.println(Double.parseDouble(price)+"----售价");
						}
						String huxing=element.get(0).getElementsByClass("house-info-2").get(0).getElementsByTag("li").get(0).text();
						if(StringUtils.isNotEmpty(huxing)){
							String layoutName=huxing.split("相似户型")[0].replace("户型：", "");
							System.out.println(layoutName+"----户型");
							//户型
							String[] room=layoutName.split("室");
							familyDetailModel.setRoom(Integer.parseInt(room[0]));
							System.out.println(room[0]+"----室");
							String[] hall=room[1].split("厅");
							familyDetailModel.setHall(Integer.parseInt(hall[0]));
							System.out.println(hall[0]+"----厅");
							String[] toilet=hall[1].split("卫");
							familyDetailModel.setToilet(Integer.parseInt(toilet[0]));
							System.out.println(toilet[0]+"----卫");
						}
						String unin=element.get(0).getElementsByClass("house-info-2").get(0).getElementsByTag("li").get(1).text();
						if(StringUtils.isNotEmpty(unin)){
							String[] uninPrice=unin.split("：");
							System.out.println(uninPrice[1].substring(0,uninPrice[1].length()-1)+"----单价");
							//单价
							familyDetailModel.setUnitPrice(Double.parseDouble(uninPrice[1].substring(0,uninPrice[1].length()-1)));
						}
						String building=element.get(0).getElementsByClass("house-info-2").get(0).getElementsByTag("li").get(2).text();
						if(StringUtils.isNotEmpty(building)){
							String[] buildingArea=building.split("：");
							System.out.println(buildingArea[1]+"----建筑面积");
							//建筑面积
							familyDetailModel.setBuildArea(Double.parseDouble(buildingArea[1].substring(0, buildingArea[1].length()-2)));
						}
						String orientation=element.get(0).getElementsByClass("house-info-2").get(0).getElementsByTag("li").get(4).text();
						if(StringUtils.isNotEmpty(orientation)){
							String[] orientations=orientation.split("：");
							System.out.println(orientations[1]+"----朝向名称");
							familyDetailModel.setOrientationsName(orientations[1]);
						}
						String floor=element.get(0).getElementsByClass("house-info-2").get(0).getElementsByTag("li").get(5).text();
						if(StringUtils.isNotEmpty(floor)){
							String[] floors=floor.split("：");
							System.out.println(floors[1]+"----floors[1]");
							String[] floorType=floors[1].split("/");
							if(floorType[0].equals("上部")){
								familyDetailModel.setFloorType(FloorTypeEnum.HEIGHT.getValue());
								System.out.println(FloorTypeEnum.HEIGHT.getValue()+"----上部");
							}else if(floorType[0].equals("中部")){
								familyDetailModel.setFloorType(FloorTypeEnum.MIDDEL.getValue());
								System.out.println(FloorTypeEnum.MIDDEL.getValue()+"----中部");
							}else{
								familyDetailModel.setFloorType(FloorTypeEnum.BOTTOM.getValue());
								System.out.println(FloorTypeEnum.BOTTOM.getValue()+"----下部");
							}
							familyDetailModel.setFloorTypeName(floorType[0]);
							familyDetailModel.setFloorNum(Integer.parseInt(floorType[1].substring(0,floorType[1].length()-1)));
						}
						
							 Elements element1=doc.getElementsByClass("xq-intro-info");
							 String href=element1.get(0).getElementsByTag("a").get(0).attr("href");
								String[] name=href.split("/community/");
								System.out.println(name[1]+"-----小区Id");
								familyDetailModel.setOriginDicId(name[1]);
					        	try {
									if(StringUtils.isNotEmpty(href)){
										String familyDetailModelge="http://bj.5i5j.com"+href;
										Document docs = Jsoup.connect(familyDetailModelge).get();
										//小区图片
										Elements prcList=doc.getElementsByClass("lb-small-pic mask");
										List<HousePictureCrawlerPo> pictureCrawlerList=new ArrayList<>();
										if(prcList != null && prcList.size()>0){
											HousePictureCrawlerPo pictureCrawler=null;
											Elements  pictureList=prcList.get(0).getElementsByTag("img");
											for (Element element2 : pictureList) {
												pictureCrawler=new HousePictureCrawlerPo();
												String pic=element2.getElementsByTag("img").get(0).attr("src");
												System.out.println(pic+"----小区图片");
												pictureCrawler.setOriginPictureUrl(pic);
												pictureCrawler.setOriginObjId(name[1]);
												pictureCrawler.setCompayType(CompanyTypeEnum.WAWJ.getValue());
												pictureCrawler.setDeleteFlag(2);
												pictureCrawler.setCreateTime(new Date());
												pictureCrawler.setPictureType(4);
												pictureCrawler.setIsDownload(2);
												pictureCrawler.setIsUpload(2);
												pictureCrawlerList.add(pictureCrawler);
											}
											familyDetailModel.setPictureCrawler(pictureCrawlerList);
											
										}
									    Elements element11=docs.select("div[class=comm-baseInfo-l] >ul");
									    if(element11.size()>0&&element11!=null){
									    	HouseAlikeCommunityPo community=new HouseAlikeCommunityPo();
									    	//区域，商圈
											Elements ele=doc.getElementsByClass("w-full path");
											Elements dd=ele.get(0).getElementsByTag("a");
											String areaName1=dd.get(2).text();
											areaName1=areaName1.substring(0, areaName1.length()-3);
											if(areaName1.equals("密云") || areaName1.equals("延庆")){
												areaName1 +="县";
											}else{
												areaName1 +="区";
											}
											System.out.println(areaName1+"----区域");
											community.setAreaName3(areaName1);
											community.setAreaCode1("110000");
											community.setAreaCode2("110100");
											community.setAreaName1("北京市");
											community.setAreaName2("北京市");
											if(dd.size()>3){
												String areaName2=dd.get(3).text();
												areaName2=areaName2.substring(0, areaName2.length()-3);
												System.out.println(areaName2+"----商圈");
												community.setTradingAreaName(areaName2);
											}
											community.setOriginCommunityId(name[1]);
									    	   String allage=element11.get(0).getElementsByTag("li").get(0).text();
										    	String[] allageName=allage.split("：");
										    	  System.out.println("准备解析小区:" + familyDetailModelge);
										    	  community.setName(allageName[1]);
										    	  System.out.println(allageName[1]+"-----小区名称");
										    	  String property=element11.get(0).getElementsByTag("li").get(1).text();
												  if(property.length()>4){
													  String[] property1=property.split("：");
													  community.setPropertyFee(property1[1]);
													  System.out.println(property1[1]+"-----物业费");
												  }
												  String buildArea=element11.get(0).getElementsByTag("li").get(2).text();
												  if(buildArea.length()>5){
													  String[] build_area=buildArea.split("：");
													  community.setBuiltUp(build_area[1]);
													  System.out.println(build_area[1]+"-----建筑面积");
												  }
												 //占地面积
												  String coversArea=element11.get(0).getElementsByTag("li").get(3).text();
												  if(coversArea.length()>5){
													  String[] coversArea1=coversArea.split("：");
													  community.setOccupyArea(coversArea1[1]);
													  System.out.println(coversArea1[1]+"-----占地面积");
												  }
												  //容积率
												  String volume=element11.get(0).getElementsByTag("li").get(4).text();
												  if(volume.length()>7){
													  String[] volume1=volume.split("：");
													  community.setPlotRatio(volume1[1]);
													  System.out.println(volume1[1]+"---容积率");
												  }
												  //小区地址
												  String dresss=element11.get(0).getElementsByTag("li").get(5).text();
												  if(dresss.length()>5){
													  String[] dress1=dresss.split("：");
													  community.setAddress(dress1[1]);	
													  System.out.println(dress1[1]+"---小区地址");
												  }
												  //开发商
												  String developer=element11.get(1).getElementsByTag("li").get(0).text();
												  if(developer.length()>6){
													  String[] developers=developer.split("：");
													  if(developers[1].equals("无开发商")){
													  }else{
														  community.setDeveloper(developers[1]);
														  System.out.println(developers[1]+"---开发商");
													  }
													 
												  }
												  //总户数
												  String houseCount=element11.get(1).getElementsByTag("li").get(1).text();
										    	   if(houseCount.length()>6){
										    		   String[] houseCounts=houseCount.split("：");
										    		   if(houseCounts[1].length()>1){
										    			   community.setTotalHouse(houseCounts[1]);
											    		   System.out.println(houseCounts[1]+"---总户数");
										    		   }
										    	   }
										    	   //绿化
										    	   String green=element11.get(1).getElementsByTag("li").get(2).text();
										    	   if(green.length()>6){
										    		   String[] greens=green.split("：");
										    			   community.setGreenRate(greens[1].substring(0, greens[1].length()-1)); 
										    			   System.out.println(greens[1].substring(0, greens[1].length()-1)+"---绿化");
										    	   }
										    	   //供暖方式
										    	   String heating=element11.get(1).getElementsByTag("li").get(3).text();
										    	   if(heating.length()>5){
										    		   String[] heatingType=heating.split("：");
										    		   community.setHeatingModeName(heatingType[1]);
										    		   System.out.println(heatingType[1]+"---供暖方式");
										    	   }
										    	   //停车位
										    	   String car_position_count=element11.get(1).getElementsByTag("li").get(4).text();
										    	   if(car_position_count.length()>7){
										    		   String[] car_position_counts=car_position_count.split("：");
										    		   community.setParkingSpace(car_position_counts[1]);
										    		   System.out.println(car_position_counts[1]+"---停车位");
										    	   }
										    	   //物业公司
										    	   String wy_company=element11.get(1).getElementsByTag("li").get(5).text();
										    	   if(wy_company.length()>5){
										    		   String[] wy_companys=wy_company.split("：");
										    		   community.setPropertyCompany(wy_companys[1]);
										    		   System.out.println(wy_companys[1]+"---物业公司");
										    	   }
										    	   String e = doc.getElementsByTag("script").get(30).html();
													if(StringUtils.isNotEmpty(e)){
														String[] map=e.split(";");
														String map1=map[3].split("=")[1];
														String map2=map[4].split("=")[1];
														System.out.println(map1);
														System.out.println(map2);
														if(map1.equals("0")){
															community.setAccuracy(map1);
														}if(map2.equals("0")){
															community.setDimension(map2);
														}else{
															String mapx=map[3].split("=")[1].substring(1, map[3].split("=")[1].length()-1);
															String mapy=map[4].split("=")[1].substring(1, map[4].split("=")[1].length()-1);
															System.out.println(mapx+"----mapx");
															System.out.println(mapy+"----mapy");
															community.setAccuracy(mapx);
															community.setDimension(mapy);
														}
									    }
										familyDetailModel.setHouseAlikeCommunity(community);
									}
									}
					        	} catch (Exception e2) {
									LOGGER.info("解析错误",e2.getMessage(),e2);
									System.out.println("重新解析小区");
								}
				}
					return familyDetailModel;
			} catch (Exception e2) {
				LOGGER.info("解析错误",e2.getMessage(),e2);
				System.out.println("解析错误，开始重新解析");
				jieXiTab(loupanUrls,is);
			}
			return familyDetailModel;
		}

		@Override
		public void jiexiLoupanJop() {
			ZydcOldhouseFlagService.deleteWawjAll();
			jiexiLoupan(1);
			masterCrawlerTaskService.updateDynamicTaskByIndex(1);
		}


}
