package com.dsj.common.utils.excel;

import java.io.FileInputStream;  
import java.util.ArrayList;  
  
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;  
  
/** 
 * Excel 读取（97-2003格式） 

 * @date    2012-4-27 下午03:39:01 
 * @note    PoiExcel2k3Helper 
 */  
public class PoiExcel2k3Helper extends PoiExcelHelper {  
	private static Logger log = Logger.getLogger(PoiExcel2k3Helper.class);
    /** 获取sheet列表 */  
    public ArrayList<String> getSheetList(Workbook workbook ) {  
        ArrayList<String> sheetList = new ArrayList<String>(0);  
        try {  
            //Workbook workbook  = new HSSFWorkbook(new FileInputStream(filePath));  
        	HSSFWorkbook wb = (HSSFWorkbook)workbook;
            int i = 0;  
            while (true) {  
                try {  
                    String name = wb.getSheetName(i);  
                    sheetList.add(name);  
                    i++;  
                } catch (Exception e) {  
                    break;  
                }  
            }  
        } catch (Exception e) {  
        	log.error("Exception", e);
        }  
        return sheetList;  
    }  
  
    /** 读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(Workbook workbook , int sheetIndex, String rows, String columns) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
           // Workbook workbook  = new HSSFWorkbook(new FileInputStream(filePath));  
        	HSSFWorkbook wb = (HSSFWorkbook)workbook;
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);  
              
            dataList = readExcel(sheet, rows, getColumnNumber(sheet, columns));  
        } catch (Exception e) {  
           	log.error("Exception", e);
        }  
        return dataList;  
    }  
      
    /** 读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(Workbook workbook , int sheetIndex, String rows, int[] cols) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
            //Workbook workbook  = new HSSFWorkbook(new FileInputStream(filePath));  
        	HSSFWorkbook wb = (HSSFWorkbook)workbook;
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);  
              
            dataList = readExcel(sheet, rows, cols);  
        } catch (Exception e) {  
           	log.error("Exception", e);
        }  
        return dataList;  
    }  
}  