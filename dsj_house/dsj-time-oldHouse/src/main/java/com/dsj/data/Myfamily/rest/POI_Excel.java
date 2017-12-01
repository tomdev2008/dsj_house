package com.dsj.data.Myfamily.rest;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.common.utils.MyBeanUtils;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityTempPo;
import com.dsj.modules.oldHouseParser.po.HouseLianjiaCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityService;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityTempService;
import com.dsj.modules.oldHouseParser.service.HouseLianjiaCommunityService;
import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;
import com.dsj.modules.oldHouseParser.service.HousePictureCrawlerService;
import com.dsj.modules.oldHouseParser.service.OldHousePictureCrawlerService;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.HousePictureService;
@Controller
@RequestMapping("excelPoi")
public class POI_Excel {
	@Autowired
	private HouseAlikeCommunityTempService houseAlikeCommunityTempService;
	@Autowired
	private HouseLianjiaCommunityService houseLianjiaCommunityService;
	@Autowired
	private HouseAlikeCommunityService houseAlikeCommunityService;
	@Autowired
	private HouseMasterCrawlerService HouseMasterCrawlerService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	@Autowired
	private HousePictureCrawlerService housePictureCrawlerService;
	@Autowired
	private HousePictureService housePictureService;
	//导出路径
	@Value("${wiwjExcel}")
	private String wiwjExcel;
	@RequestMapping("excelCome") 
	public void excelPoi(HttpServletResponse response) throws Exception{
    	loadScoreInfo(wiwjExcel);
     }
	
	public void loadScoreInfo(String xlsPath) throws Exception{
	    List<HouseAlikeCommunityTempPo> housePoList=houseAlikeCommunityTempService.selectList();
	FileInputStream fileIn = new FileInputStream(xlsPath);
	//根据指定的文件输入流导入Excel从而产生Workbook对象
	Workbook wb0 = new HSSFWorkbook(fileIn);
	//获取Excel文档中的第一个表单
	Sheet sht0 = wb0.getSheetAt(0);
	     for(int i=0;i<housePoList.size();i++){
	    	 Row dd=sht0.getRow(i+2);
	 		 Cell name=dd.getCell(0);
	 		 Cell id=dd.getCell(4);
	 		double ss=id.getNumericCellValue();
			DecimalFormat fmat=new DecimalFormat("#######");
			String ids=fmat.format(ss);
			if(name != null){
	 			//查询临时表
	 			HouseAlikeCommunityTempPo houseCommunity=houseAlikeCommunityTempService.selectIdCommunit(Long.parseLong(ids));
				HouseLianjiaCommunityPo houseLianjiaCommunity=new HouseLianjiaCommunityPo();
				MyBeanUtils.copyBean2Bean(houseLianjiaCommunity, houseCommunity);
				//保存到主表中
				houseLianjiaCommunity.setId(null);
				Long lianjiaId=houseLianjiaCommunityService.saveInsertLianjiaHouse(houseLianjiaCommunity);
				//插入到主表的数据
				HouseLianjiaCommunityPo houseLianjia=houseLianjiaCommunityService.selectLianjia(lianjiaId);
			    //根据小区名查出合并的数据
			    List<HouseAlikeCommunityTempPo> communityList=houseAlikeCommunityTempService.selectListCommunity(name.toString());
			    for(int j=0;j<communityList.size();j++){
			    	HouseAlikeCommunityTempPo housepo=communityList.get(j);
			    	housepo.setLianjiaId(lianjiaId);
			    	HouseAlikeCommunityPo houseAlikeCommunity=new HouseAlikeCommunityPo();
					MyBeanUtils.copyBean2Bean(houseAlikeCommunity, housepo);
					//保存到子表中
					houseAlikeCommunity.setId(null);
					houseAlikeCommunityService.insertHouse(houseAlikeCommunity);
			    }
			  //查询房源表
				List<HouseMasterCrawlerPo> houseMasterCrawlerPo = HouseMasterCrawlerService.selectHouseMasterCrawler(Long.parseLong(ids));
				for(int j=0;j<houseMasterCrawlerPo.size();j++){
					HouseMasterCrawlerPo houseL=houseMasterCrawlerPo.get(j);
					houseL.setDicId(lianjiaId);
					//修改合并后id
					HouseMasterCrawlerService.updateDynamic(houseL);
				}
				HouseDirectoryPo houseDirectory=new HouseDirectoryPo();
				MyBeanUtils.copyBean2Bean(houseDirectory, houseLianjia);
				//保存小区业务表
				Long directoryId=houseDirectoryService.saveByHouseId(houseDirectory);
				houseLianjia.setDicId(directoryId);
				houseLianjiaCommunityService.updateDynamic(houseLianjia);
				//保存主表图片到dsj_house_picture
				String originCommunityId=houseLianjia.getOriginCommunityId();
				List<HousePictureCrawlerPo> oldHousePicture=housePictureCrawlerService.selectPictureId(originCommunityId);
				for(int h=0;h<oldHousePicture.size();h++){
					HousePicturePo housePicture=new HousePicturePo();
					MyBeanUtils.copyBean2Bean(housePicture, oldHousePicture.get(h));
					housePicture.setId(null);
					housePictureService.save(housePicture);
				}
			}
	     }
	     
	     for(int i=0;i<housePoList.size();i++){
	 		Row dd=sht0.getRow(i+2);
	 		Cell id=dd.getCell(4);
	 		Cell name=dd.getCell(1);
	 		double ss=id.getNumericCellValue();
	 		DecimalFormat fmat=new DecimalFormat("#######");
	 		String ids=fmat.format(ss);
	 		List<HouseAlikeCommunityTempPo> communityList=houseAlikeCommunityTempService.selectListCommunity(name.toString());
	 		if(communityList.size()==1){
	 			//查询临时表
		 		HouseAlikeCommunityTempPo houseCommunity=houseAlikeCommunityTempService.selectIdCommunit(Long.parseLong(ids));
		 		HouseAlikeCommunityPo houseAlikeCommunity=new HouseAlikeCommunityPo();
		 			MyBeanUtils.copyBean2Bean(houseAlikeCommunity, houseCommunity);
		 			//保存到子表中
		 			houseAlikeCommunity.setId(null);
		 			houseAlikeCommunityService.insertHouse(houseAlikeCommunity);
		 			HouseLianjiaCommunityPo houseLianjiaCommunity=new HouseLianjiaCommunityPo();
					MyBeanUtils.copyBean2Bean(houseLianjiaCommunity, houseCommunity);
					//保存到主表中
					houseLianjiaCommunity.setId(null);
					Long lianjiaId=houseLianjiaCommunityService.saveInsertLianjiaHouse(houseLianjiaCommunity);
	 		}
	 	}
	        fileIn.close();    
	    }

	public String getWiwjExcel() {
		return wiwjExcel;
	}

	public void setWiwjExcel(String wiwjExcel) {
		this.wiwjExcel = wiwjExcel;
	}

	
	
}
