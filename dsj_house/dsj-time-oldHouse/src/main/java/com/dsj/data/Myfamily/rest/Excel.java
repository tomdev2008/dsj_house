package com.dsj.data.Myfamily.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityTempPo;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityTempService;

@Controller
@RequestMapping("excel")
public class Excel {
	@Autowired
	private HouseAlikeCommunityTempService houseAlikeCommunityTempService;
    @Value("${wiwjPoi}")
	private String wiwjPoi;
	@RequestMapping("excelPoi") 
	public void excelPoi(HttpServletResponse response) throws IOException{
		loadScoreInfo(wiwjPoi,response);
     }
    
    /* 导出*/
    public void loadScoreInfo(String xlsPath,HttpServletResponse response) throws IOException{
    	List<HouseAlikeCommunityTempPo> housePoList=houseAlikeCommunityTempService.selectList();
		    	//创建HSSFWorkbook对象(excel的文档对象)
		        HSSFWorkbook wb = new HSSFWorkbook();
		  //建立新的sheet对象（excel的表单）
		  HSSFSheet sheet=wb.createSheet("楼盘表");
		  //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		  HSSFRow row1=sheet.createRow(0);
		  //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		  HSSFCell cell=row1.createCell(0);
		        //设置单元格内容
		  cell.setCellValue("二手房楼盘表");
		  //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		  sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
		  //在sheet里创建第二行
		  HSSFRow row2=sheet.createRow(1);    
		        //创建单元格并设置单元格内容
		        row2.createCell(0).setCellValue("姓名");
		        row2.createCell(1).setCellValue("小区名称");    
		        row2.createCell(2).setCellValue("公司");
		        row2.createCell(3).setCellValue("id"); 
		        row2.createCell(4).setCellValue("临时表的id");
		    for(int i=0;i<housePoList.size();i++){
		    	 //在sheet里创建第三行
		        HSSFRow row3=sheet.createRow(i+2);
				        row3.createCell(1).setCellValue(housePoList.get(i).getName());
				        row3.createCell(2).setCellValue(housePoList.get(i).getCompany());    
				        row3.createCell(3).setCellValue(housePoList.get(i).getOriginCommunityId()); 
				        row3.createCell(4).setCellValue(housePoList.get(i).getId()); 
		    }    
         
		  //输出Excel文件
		      OutputStream output=response.getOutputStream();
		      response.reset();
		      response.setHeader("Content-disposition", "attachment; filename=LoupanDetails.xls");
		      response.setContentType("application/msexcel");        
		      wb.write(output);
		      output.close();
    }

	public String getWiwjPoi() {
		return wiwjPoi;
	}

	public void setWiwjPoi(String wiwjPoi) {
		this.wiwjPoi = wiwjPoi;
	}
    
}
