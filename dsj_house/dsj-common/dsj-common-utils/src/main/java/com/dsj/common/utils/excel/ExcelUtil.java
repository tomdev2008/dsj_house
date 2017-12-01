package com.dsj.common.utils.excel;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings({"rawtypes","unchecked"}) 
public class ExcelUtil {
	/************************************XSSF*********************************************/
    
    /**
     * 取得指定单元格行和列
     * @param keyMap 所有单元格行、列集合
     * @param key 单元格标识
     * @return 0：列 1：行（列表型数据不记行，即1无值）
     */
    public static int[] getPos(HashMap keyMap, String key){
        int[] ret = new int[0];
         
        String val = (String)keyMap.get(key);
         
        if(val == null || val.length() == 0)
            return ret;
         
        String pos[] = val.split(",");
         
        if(pos.length == 1 || pos.length == 2){
            ret = new int[pos.length];
            for(int i0 = 0; i0 < pos.length; i0++){
                if(pos[i0] != null && pos[i0].trim().length() > 0){
                    ret[i0] = Integer.parseInt(pos[i0].trim());
                } else {
                    ret[i0] = 0;
                }
            }
        }
        return ret;
    }
     
    /**
     * 取对应格子的值
     * @param sheet
     * @param rowNo 行
     * @param cellNo 列
     * @return
     * @throws IOException
     */
    public static String getCellValue(Sheet sheet,int rowNo,int cellNo) {
        String cellValue = null;
        Row row = sheet.getRow(rowNo);
        Cell cell = row.getCell(cellNo);
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            	DecimalFormat df = new DecimalFormat("0");
                cellValue = getCutDotStr(df.format(cell.getNumericCellValue()));
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cellValue = cell.getStringCellValue();
            }
            if (cellValue != null) {
                cellValue = cellValue.trim();
            }           
        } else {
            cellValue = null;
        }
        return cellValue;
    }
      
    /**
     * 取整数
     * @param srcString
     * @return
     */
    private static String getCutDotStr(String srcString) {
        String newString = "";
        if (srcString != null && srcString.endsWith(".0")) {
            newString = srcString.substring(0,srcString.length()-2);
        } else {
            newString = srcString;
        }
        return newString;
    }   
     
    /**
     * 读数据模板
     * @param 模板地址
     * @throws IOException
     */
    public static HashMap[] getTemplateFile(String templateFileName) throws IOException {    
        FileInputStream fis = new FileInputStream(templateFileName);
        
        Workbook wbPartModule = null;
        if(templateFileName.endsWith(".xlsx")){
        	wbPartModule = new XSSFWorkbook(fis);
        }else if(templateFileName.endsWith(".xls")){
        	wbPartModule = new HSSFWorkbook(fis);
        }
        HashMap[] templateMap =null;
        if(wbPartModule!=null){
	        int numOfSheet = wbPartModule.getNumberOfSheets();
	        templateMap=new HashMap[numOfSheet];
	        for(int i = 0; i < numOfSheet; i++){
	            Sheet sheet = wbPartModule.getSheetAt(i);
	            templateMap[i] = new HashMap();
	            readSheet(templateMap[i], sheet);
	        }
        }
        fis.close();
        return templateMap;
    }
     
    /**
     * 读模板数据的样式值置等信息
     * @param keyMap
     * @param sheet
     */
    private static void readSheet(HashMap keyMap, Sheet sheet){
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
         
        for (int j = firstRowNum; j <= lastRowNum; j++) {
            Row rowIn = sheet.getRow(j);
            if(rowIn == null) {
                continue;
            }
            int firstCellNum = rowIn.getFirstCellNum();
            int lastCellNum = rowIn.getLastCellNum();
            for (int k = firstCellNum; k <= lastCellNum; k++) {
//              Cell cellIn = rowIn.getCell((short) k);
                Cell cellIn = rowIn.getCell(k);
                if(cellIn == null) {
                    continue;
                }
                 
                int cellType = cellIn.getCellType();
                if(Cell.CELL_TYPE_STRING != cellType) {
                    continue;
                }
                String cellValue = cellIn.getStringCellValue();
                if(cellValue == null) {
                    continue;
                }
                cellValue = cellValue.trim();
                if(cellValue.length() > 2 && cellValue.substring(0,2).equals("<%")) {
                    String key = cellValue.substring(2, cellValue.length());
                    String keyPos = Integer.toString(k)+","+Integer.toString(j);
                    keyMap.put(key, keyPos);
                    keyMap.put(key+"CellStyle", cellIn.getCellStyle());
                } else if(cellValue.length() > 3 && cellValue.substring(0,3).equals("<!%")) {
                    String key = cellValue.substring(3, cellValue.length());
                    keyMap.put("STARTCELL", Integer.toString(j));
                    keyMap.put(key, Integer.toString(k));
                    keyMap.put(key+"CellStyle", cellIn.getCellStyle());
                }
            }
        }
    }
     
    /**
     * 获取格式，不适于循环方法中使用，wb.createCellStyle()次数超过4000将抛异常
     * @param keyMap
     * @param key
     * @return
     */
    public static CellStyle getStyle(HashMap keyMap, String key,Workbook wb) {
        CellStyle cellStyle = null;      
         
        cellStyle = (CellStyle) keyMap.get(key+"CellStyle");
        //当字符超出时换行
        cellStyle.setWrapText(true);
        CellStyle newStyle = wb.createCellStyle();
        newStyle.cloneStyleFrom(cellStyle);
        return newStyle;
    }
    /**
     * Excel单元格输出
     * @param sheet
     * @param row 行
     * @param cell 列
     * @param value 值
     * @param cellStyle 样式
     */
    public static void setValue(Sheet sheet, int row, int cell, Object value, CellStyle cellStyle){
        Row rowIn = sheet.getRow(row);
        if(rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        Cell cellIn = rowIn.getCell(cell);
        if(cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if(cellStyle != null) {  
            //修复产生多超过4000 cellStyle 异常
            //CellStyle newStyle = wb.createCellStyle();
            //newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
        //对时间格式进行单独处理
     
        if(value==null){
            cellIn.setCellValue("");
        }else {
        	
        	if(value instanceof java.lang.Integer){
        		cellIn.setCellValue(Double.parseDouble(value.toString()));
        	}else if (value instanceof java.util.Date) {
        		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        		   cellIn.setCellValue(sdf.format((Date)value));
            } else if(value instanceof java.lang.Double){
            	cellIn.setCellValue((Double)value);
            }else if(value instanceof java.lang.Float){
            	cellIn.setCellValue(Double.parseDouble(value.toString()));
            }else{
                cellIn.setCellValue(value.toString());
            }
        }
    }
    
    public static void setValue(Sheet sheet, int row, int cell, Object value, CellStyle cellStyle,String cellStr){
        
       	Row rowIn = sheet.getRow(row);
        if(rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        Cell cellIn = rowIn.getCell(cell);
        if(cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if(cellStyle != null) {  
            //修复产生多超过4000 cellStyle 异常
            //CellStyle newStyle = wb.createCellStyle();
            //newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
        //对时间格式进行单独处理
     
        if(value==null){
        	if(cellStr!=null&&cellStr.equals("rowSum")){
        		int index =row+1;
        		cellIn.setCellType(HSSFCell.CELL_TYPE_FORMULA);//设置为公式  
        		  String formula = "SUM(F"+index+",-H"+index+")";//计算一列总数  
        		  cellIn.setCellFormula(formula);  
        	}else if(cellStr!=null&&cellStr.equals("sumIndex")){
        		cellIn.setCellValue((double)row-1);
        	}else{
        		cellIn.setCellValue("");
        	}
        }else {
        	
        	if(value instanceof  java.lang.Integer){

        		cellIn.setCellValue(Double.parseDouble(value.toString()));
        		
        	}else if (value instanceof java.util.Date) {
        		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        		   cellIn.setCellValue(sdf.format((Date)value));
            } else if(value instanceof java.lang.Double){
            	cellIn.setCellValue((Double)value);
            }else if(value instanceof java.lang.Float){
            	cellIn.setCellValue(Double.parseDouble(value.toString()));
            }else{
                cellIn.setCellValue(value.toString());
            }
        }
    }
    
    public static void setValue(Sheet sheet, int row, int cell, CellStyle cellStyle,String cellStr){
        
    	
    	Row rowIn = sheet.getRow(row);
        if(rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        Cell cellIn = rowIn.getCell(cell);
        if(cellIn == null) {
        	String formula="";
            cellIn = rowIn.createCell(cell);
          
            if(cell>=4){
            cellIn.setCellType(HSSFCell.CELL_TYPE_FORMULA);//设置为公式 
            }
          if(cell==4){
        	  formula = "SUM(E4:E"+row+")";//计算一列总数 
        	  cellIn.setCellFormula(formula);  
          }else if(cell==5){
        	  formula = "SUM(F4:F"+row+")";//计算一列总数 
        	  cellIn.setCellFormula(formula);  
          }else if(cell==6){
        	  formula = "SUM(G4:G"+row+")";//计算一列总数 
        	  cellIn.setCellFormula(formula);  
          }else if(cell==7){
        	  formula = "SUM(H4:H"+row+")";//计算一列总数 
        	  cellIn.setCellFormula(formula);  
          }else if(cell==8){
        	  formula = "SUM(I4:I"+row+")";//计算一列总数 
        	  cellIn.setCellFormula(formula);  
          }else if(cell==9){
        	  formula = "SUM(J4:J"+row+")";//计算一列总数 
        	  cellIn.setCellFormula(formula);  
          }else if(cell==0){
        	  cellIn.setCellValue("合计");
          }else{
        	  cellIn.setCellValue("");
          }
  		 
        }
        if(cellStyle != null) {  
            //修复产生多超过4000 cellStyle 异常
            //CellStyle newStyle = wb.createCellStyle();
            //newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
       
     
       
    }
     
    /**
     * 根据表格样式判断是否为日期格式
     * @param cellStyle
     * @return
     */
    public static boolean isCellDateFormatted(CellStyle cellStyle){
        if(cellStyle==null){
            return false;
        }
        int i = cellStyle.getDataFormat();
        String f = cellStyle.getDataFormatString();
        
        return org.apache.poi.ss.usermodel.DateUtil.isADateFormat(i, f);
    }
    /**
     * 适用于导出的数据Excel格式样式重复性较少
     * 不适用于循环方法中使用
     * @param wbModule
     * @param sheet
     * @param pos 模板文件信息
     * @param startCell 开始的行
     * @param value 要填充的数据
     * @param cellStyle 表格样式
     */
    public static void createCell(Workbook wbModule, Sheet sheet,HashMap pos, int startCell,Object value,String cellStyle){
        int[] excelPos = getPos(pos, cellStyle);
        setValue(sheet, startCell, excelPos[0], value, getStyle(pos, cellStyle,wbModule));
    }
    /************************************XSSF*******************************************/
}
