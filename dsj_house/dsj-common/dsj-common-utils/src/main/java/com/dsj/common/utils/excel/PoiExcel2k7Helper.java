package com.dsj.common.utils.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
  
/** 
 * Excel 读取（2007+新格式） 
 * @date    2012-4-27 下午03:39:01 
 * @note    PoiExcel2k7Helper 
 */  
public class PoiExcel2k7Helper extends PoiExcelHelper {  
	
	private static Logger log = Logger.getLogger(PoiExcel2k3Helper.class);
    /** 获取sheet列表 */  
    public ArrayList<String> getSheetList(Workbook workbook) {  
        ArrayList<String> sheetList = new ArrayList<String>(0);  
        try {  
            //XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));  
            XSSFWorkbook wb = (XSSFWorkbook)(workbook);  
            Iterator<XSSFSheet> iterator = wb.iterator();  
            while (iterator.hasNext()) {  
                sheetList.add(iterator.next().getSheetName());  
            }  
        } catch (Exception e) {  
        	log.error("Exception", e);
        }  
        return sheetList;  
    }  
  
    /** 读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(Workbook workbook, int sheetIndex, String rows, String columns) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
        	XSSFWorkbook wb = (XSSFWorkbook)(workbook);  
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);  
              
            dataList = readExcel(sheet, rows, getColumnNumber(sheet, columns));  
        } catch (Exception e) {  
        	log.error("Exception", e);
        }  
        return dataList;  
    }  
      
    /** 读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(Workbook workbook, int sheetIndex, String rows, int[] cols) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
        	XSSFWorkbook wb = (XSSFWorkbook)(workbook);  
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);  
              
            dataList = readExcel(sheet, rows, cols);  
        } catch (Exception e) {  
        	log.error("Exception", e);
        }  
        return dataList;  
    }  
}  

