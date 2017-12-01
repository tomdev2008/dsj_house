package com.dsj.common.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 对excel进行操作工具类

 *
 **/
@SuppressWarnings("rawtypes")
public class ExcelHandle {

	private Map<String,HashMap[]> tempFileMap  = new HashMap<String,HashMap[]>();
	private Map<String,Map<String,Cell>> cellMap = new HashMap<String,Map<String,Cell>>();
	private Map<String,FileInputStream> tempStream = new HashMap<String, FileInputStream>();
	private Map<String,Workbook> tempWorkbook = new HashMap<String, Workbook>();
	private Map<String,Workbook> dataWorkbook = new HashMap<String, Workbook>();
	
	/**
	 * 单无格类
	 * @author xiliang.xiao
	 *
	 */
	class Cell{
		private int column;//列
		private int line;//行
		private CellStyle cellStyle;

		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
		public int getLine() {
			return line;
		}
		public void setLine(int line) {
			this.line = line;
		}
		public CellStyle getCellStyle() {
			return cellStyle;
		}
		public void setCellStyle(CellStyle cellStyle) {
			this.cellStyle = cellStyle;
		}
	}
	
	/**
	 * 向Excel中输入相同title的多条数据
	 * @param tempFilePath excel模板文件路径
	 * @param cellList 需要填充的数据（模板<!%后的字符串）
	 * @param dataList 填充的数据
	 * @param sheet 填充的excel sheet,从0开始
	 * @throws IOException 
	 */
	public void writeListData(String tempFilePath,List<String> cellList,List<?> dataList,int sheet,Boolean totalFalg) throws IOException{
		//获取模板填充格式位置等数据
		HashMap temp = getTemp(tempFilePath,sheet);
		//按模板为写入板
		Workbook temWorkbook = getTempWorkbook(tempFilePath);
		//获取数据填充开始行
		int startCell = Integer.parseInt((String)temp.get("STARTCELL"));
		//数据填充的sheet
		Sheet wsheet = temWorkbook.getSheetAt(sheet);
		//移除模板开始行数据即<!%
		wsheet.removeRow(wsheet.getRow(startCell));
		
		wsheet.setForceFormulaRecalculation(true); 
		if(dataList!=null&&dataList.size()>0){
			for(Object obj:dataList){
				Map map=null;
				if(obj instanceof Map){
					map=(Map)obj;
				}else{
					Map<String,Object> ret=new HashMap();
					ret.putAll(new BeanMap(obj));
					ret.remove("class");
					map=ret;
				}
				for(String cell:cellList){
					//获取对应单元格数据
					
					Cell c = getCell(cell,temp,temWorkbook,tempFilePath,sheet);
					//写入数据
					ExcelUtil.setValue(wsheet, startCell, c.getColumn(), map.get(cell), c.getCellStyle(),cell);
				}
				startCell++;
			  
			}
			if(sheet==0&&totalFalg){
				for(String cell:cellList){
				//获取对应单元格数据
				
				Cell c = getCell(cell,temp,temWorkbook,tempFilePath,0);
				//写入数据
				ExcelUtil.setValue(wsheet, startCell, c.getColumn(), c.getCellStyle(),cell);
				}	
			}
		}
	}

	/**
	 * 按模板向Excel中相应地方填充数据
	 * @param tempFilePath excel模板文件路径
	 * @param cellList 需要填充的数据（模板<%后的字符串）
	 * @param dataMap 填充的数据
	 * @param sheet 填充的excel sheet,从0开始
	 * @throws IOException 
	 */
	public void writeData(String tempFilePath,List<String> cellList,Map<String,Object> dataMap,int sheet) throws IOException{
		//获取模板填充格式位置等数据
		HashMap tem = getTemp(tempFilePath,sheet);
		//按模板为写入板
		Workbook wbModule = getTempWorkbook(tempFilePath);
		//数据填充的sheet
		Sheet wsheet = wbModule.getSheetAt(sheet);
		if(dataMap!=null&&dataMap.size()>0){
			for(String cell:cellList){
				//获取对应单元格数据
				Cell c = getCell(cell,tem,wbModule,tempFilePath,sheet);
				ExcelUtil.setValue(wsheet, c.getLine(), c.getColumn(), dataMap.get(cell), c.getCellStyle());
			}
		}
	}
	/**
	 * 按模板向Excel中相应地方填充数据
	 * @param tempFilePath excel模板文件路径
	 * @param cellList 需要填充的数据（模板<%后的字符串）
	 * @param dataMap 填充的数据
	 * @param sheet 填充的excel sheet,从0开始
	 * @throws IOException 
	 */
	public void writeData(String tempFilePath,List<String> cellList,Map<String,Object> dataMap,int sheet,int row,String sheetName) throws IOException{
		//获取模板填充格式位置等数据
		HashMap tem = getTemp(tempFilePath,sheet);
		//按模板为写入板
		Workbook wbModule = getTempWorkbook(tempFilePath);
		//数据填充的sheet
		Sheet wsheet = wbModule.getSheetAt(sheet);
		if(sheetName!=null){
			wbModule.setSheetName(sheet,sheetName);
		}
		if(dataMap!=null&&dataMap.size()>0){
			for(String cell:cellList){
				//获取对应单元格数据
				Cell c = getCell(cell,tem,wbModule,tempFilePath,sheet);
				if(cell.equals("title")||cell.equals("titleDetail")){
					ExcelUtil.setValue(wsheet,c.getLine(), c.getColumn(), dataMap.get(cell), c.getCellStyle());
				}else if(cell.equals("date")||cell.equals("dateDetail")){
					ExcelUtil.setValue(wsheet, 1, c.getColumn(), dataMap.get(cell), c.getCellStyle());
				}else{
					ExcelUtil.setValue(wsheet,c.getLine(), c.getColumn(), dataMap.get(cell), c.getCellStyle());

				}
				
			}
		}
	}
	
	/**
	 * Excel文件读值
	 * @param tempFilePath
	 * @param cell
	 * @param sheet
	 * @return
	 * @throws IOException 
	 */
	public Object getValue(String tempFilePath,String cell,int sheet,File excelFile) throws IOException{
		//获取模板填充格式位置等数据
		HashMap tem = getTemp(tempFilePath,sheet);
		//模板工作区
		Workbook temWorkbook = getTempWorkbook(tempFilePath);
		//数据工作区
		Workbook dataWorkbook = getDataWorkbook(tempFilePath, excelFile);
		//获取对应单元格数据
		Cell c = getCell(cell,tem,temWorkbook,tempFilePath,sheet);
		//数据sheet
		Sheet dataSheet = dataWorkbook.getSheetAt(sheet);
		return ExcelUtil.getCellValue(dataSheet, c.getLine(), c.getColumn());
	}
	
	/**
	 * 读值列表值
	 * @param tempFilePath
	 * @param cell
	 * @param sheet
	 * @return
	 * @throws IOException 
	 */
	public List<Map<String,Object>> getListValue(String tempFilePath,List<String> cellList,int sheet,File excelFile) throws IOException{
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		//获取模板填充格式位置等数据
		HashMap tem = getTemp(tempFilePath,sheet);
		//获取数据填充开始行
		int startCell = Integer.parseInt((String)tem.get("STARTCELL"));
		//将Excel文件转换为工作区间
		Workbook dataWorkbook = getDataWorkbook(tempFilePath,excelFile) ;
		//数据sheet
		Sheet dataSheet = dataWorkbook.getSheetAt(sheet);
		//文件最后一行
		int lastLine = dataSheet.getLastRowNum();
		
		for(int i=startCell;i<=lastLine;i++){
			dataList.add(getListLineValue(i, tempFilePath, cellList, sheet, excelFile));
		}
		return dataList;
	}
	
	/**
	 * 读值一行列表值
	 * @param tempFilePath
	 * @param cell
	 * @param sheet
	 * @return
	 * @throws IOException 
	 */
	public Map<String,Object> getListLineValue(int line,String tempFilePath,List<String> cellList,int sheet,File excelFile) throws IOException{
		Map<String,Object> lineMap = new HashMap<String, Object>();
		//获取模板填充格式位置等数据
		HashMap tem = getTemp(tempFilePath,sheet);
		//按模板为写入板
		Workbook temWorkbook = getTempWorkbook(tempFilePath);
		//将Excel文件转换为工作区间
		Workbook dataWorkbook = getDataWorkbook(tempFilePath,excelFile) ;
		//数据sheet
		Sheet dataSheet = dataWorkbook.getSheetAt(sheet);
		for(String cell:cellList){
			//获取对应单元格数据
			Cell c = getCell(cell,tem,temWorkbook,tempFilePath,sheet);
			lineMap.put(cell, ExcelUtil.getCellValue(dataSheet, line, c.getColumn()));
		}
		return lineMap;
	}
	
	

	/**
	 * 获得模板输入流
	 * @param tempFilePath 
	 * @return
	 * @throws FileNotFoundException 
	 */
	private FileInputStream getFileInputStream(String tempFilePath) throws FileNotFoundException {
		if(!tempStream.containsKey(tempFilePath)){
			tempStream.put(tempFilePath, new FileInputStream(tempFilePath));
		}
		
		return tempStream.get(tempFilePath);
	}

	/**
	 * 获得输入工作区
	 * @param tempFilePath
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private Workbook getTempWorkbook(String tempFilePath) throws FileNotFoundException, IOException {
		if(!tempWorkbook.containsKey(tempFilePath)){
			if(tempFilePath.endsWith(".xlsx")){
				tempWorkbook.put(tempFilePath, new XSSFWorkbook(getFileInputStream(tempFilePath)));
			}else if(tempFilePath.endsWith(".xls")){
				tempWorkbook.put(tempFilePath, new HSSFWorkbook(getFileInputStream(tempFilePath)));
			}
		}
		return tempWorkbook.get(tempFilePath);
	}
	
	/**
	 * 获取对应单元格样式等数据数据
	 * @param cell
	 * @param tem
	 * @param wbModule 
	 * @param tempFilePath
	 * @return
	 */
	private Cell getCell(String cell, HashMap tem, Workbook wbModule, String tempFilePath,int sheet) {
		
	
		if(!cellMap.get(tempFilePath+"_"+ sheet).containsKey(cell)){
			Cell c = new Cell();
			/*System.out.println("cell:"+cell);
			System.out.println("tem:"+tem);*/
			int[] pos = ExcelUtil.getPos(tem, cell);
			if(pos.length>1){
				c.setLine(pos[1]);
			}
		
			c.setColumn(pos[0]);
			c.setCellStyle((ExcelUtil.getStyle(tem, cell, wbModule)));
			cellMap.get(tempFilePath+"_"+ sheet).put(cell, c);
		}
		return cellMap.get(tempFilePath+"_"+ sheet).get(cell);
	}

	/**
	 * 获取模板数据
	 * @param tempFilePath 模板文件路径
	 * @param sheet 
	 * @return
	 * @throws IOException
	 */
	private HashMap getTemp(String tempFilePath, int sheet) throws IOException {
		if(!tempFileMap.containsKey(tempFilePath+"_"+ sheet)){
			tempFileMap.put(tempFilePath+"_"+ sheet, ExcelUtil.getTemplateFile(tempFilePath));
			cellMap.put(tempFilePath+"_"+ sheet, new HashMap<String,Cell>());
		}
		return tempFileMap.get(tempFilePath+"_"+ sheet)[sheet];
	}
	
	/**
	 * 资源关闭
	 * @param tempFilePath 模板文件路径
	 * @param os 输出流
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void writeAndClose(String tempFilePath,OutputStream os) throws FileNotFoundException, IOException{
		if(getTempWorkbook(tempFilePath)!=null){
			getTempWorkbook(tempFilePath).write(os);
			tempWorkbook.remove(tempFilePath);
		}
		if(getFileInputStream(tempFilePath)!=null){
			getFileInputStream(tempFilePath).close();
			tempStream.remove(tempFilePath);
		}
	}
	
	/**
	 * 获得读取数据工作间
	 * @param tempFilePath
	 * @param excelFile
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private Workbook getDataWorkbook(String tempFilePath, File excelFile) throws FileNotFoundException, IOException {
		if(!dataWorkbook.containsKey(tempFilePath)){
			if(tempFilePath.endsWith(".xlsx")){
				dataWorkbook.put(tempFilePath, new XSSFWorkbook(new FileInputStream(excelFile)));
			}else if(tempFilePath.endsWith(".xls")){
				dataWorkbook.put(tempFilePath, new HSSFWorkbook(new FileInputStream(excelFile)));
			}
		}
		return dataWorkbook.get(tempFilePath);
	}
	
	/**
	 * 读取数据后关闭
	 * @param tempFilePath
	 */
	public void readClose(String tempFilePath){
		dataWorkbook.remove(tempFilePath);
	}
	
	public static void main(String args[]) throws IOException{
		sum();
	}
	
	public static void sum() throws IOException{
		String tempFilePath = ExcelHandle.class.getResource("").getPath();
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		/*List<String> dataListCell = new ArrayList<String>();
		//dataListCell.add("names");
		dataListCell.add("productNames");
		dataListCell.add("productTypes");
		dataListCell.add("orderNums");
		dataListCell.add("statementUnitPrices");
		dataListCell.add("statementNums");
		dataListCell.add("statementTotalPrices");
		dataListCell.add("refundNums");
		dataListCell.add("refundNumsTotalPrices");
		dataListCell.add("refundFee");
		dataListCell.add("remarks");
		dataListCell.add("indexs");
		List<Map<String,Object>> dataList = new  ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("indexs",1);
		map.put("productNames","111");
		map.put("productTypes", "产品");
		map.put("statementUnitPrices",1);
		map.put("orderNums",1);
		
		map.put("statementNums",1.1);
		map.put("statementTotalPrices",1222);
		map.put("refundNums",1);
		
		map.put("refundNumsTotalPrices",123);
		map.put("refundFee",144);
		map.put("remarks","描述");
		dataList.add(map);
		
		
		ExcelHandle handle = new  ExcelHandle();
		//handle.writeListData(tempFilePath, dataListCell, dataList, 0);
		
		List<String> dataCell = new ArrayList<String>();
		dataCell.add("title");
		dataCell.add("date");
		Map<String,Object> dataMap = new  HashMap<String, Object>();
		dataMap.put("title", "三亚畅海游海洋运动俱乐部有限公司 - 应收账款报表");
	
		dataMap.put("date", "结算方式：消费结算           所属日期：2016-04-11 至 2016-05-11");
		
		
		handle.writeData(tempFilePath, dataCell, dataMap, 0,0,"三亚畅海游海洋运动俱乐部有限公司 - 应收账款报表");
		File file = new File("d:/data.xls");
		OutputStream os = new FileOutputStream(file);
		//写到输出流并关闭资源
		handle.writeAndClose(tempFilePath, os);
		
		os.flush();
		os.close();
		
		System.out.println("读取写入的数据----------------------------------%%%");
		System.out.println("name:"+handle.getValue(tempFilePath, "name", 0, file));
//		System.out.println("age:"+handle.getValue(tempFilePath, "age", 0, file));
//		System.out.println("sex:"+handle.getValue(tempFilePath, "sex", 0, file));
//		System.out.println("des:"+handle.getValue(tempFilePath, "des", 0, file));
//		System.out.println("读取写入的列表数据----------------------------------%%%");
//		List<Map<String,Object>> list = handle.getListValue(tempFilePath, dataListCell, 0, file);
//		for(Map<String,Object> data:list){
//			for(String key:data.keySet()){
//				System.out.print(key+":"+data.get(key)+"--");
//			}
//			System.out.println("");
//		}
		
		handle.readClose(tempFilePath);*/
	}
	public  List<String> getBeanProsList(Class<?> cls ){
		List<String> proList=new ArrayList<String>();
    	
    	Field[] fields = cls.getDeclaredFields();  
    	for (Field field : fields) { 

    		if(!"serialVersionUID".equals(field.getName())){
    			proList.add(field.getName());
    		}
    	 }
    	return proList;
	}
	
	public static void consume() throws IOException{
		System.out.println(ExcelHandle.class.getResource("").getPath());
		String tempFilePath = ExcelHandle.class.getResource("aa.xls").getPath();
		List<String> dataListCell = new ArrayList<String>();
		//dataListCell.add("names");
		dataListCell.add("orderno");
		dataListCell.add("thirdno");
		dataListCell.add("payid");
		dataListCell.add("cardPwd");//验证码号
		dataListCell.add("productName");
		dataListCell.add("productType");
		dataListCell.add("customerName");
		dataListCell.add("tel");
		dataListCell.add("statementUnitPrice");
		dataListCell.add("consumeNum");
		dataListCell.add("consumeNumTotalPrices");
		dataListCell.add("orderCreateDate");
		dataListCell.add("consumeCreateDate");
		dataListCell.add("remarks");
		dataListCell.add("consumeStatus");
		dataListCell.add("consumeType");
		dataListCell.add("company");
		dataListCell.add("kaitongDate");
		dataListCell.add("orderNum");
		dataListCell.add("orderPrice");
		dataListCell.add("indexs");
		List<Map<String,Object>> dataList = new  ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("indexs",1);
		map.put("payid", "产品");
		map.put("cardPwd",1);
		map.put("productName",1);
		map.put("productType",1.1);
		map.put("customerName",1222);
		map.put("tel",1);
		map.put("statementUnitPrice",123);
		map.put("consumeNum",123);
		map.put("consumeNumTotalPrices",144);
		map.put("orderCreateDate","描述");
		map.put("remarks",1);
		map.put("consumeStatus",123);
		map.put("consumeType",123);
		map.put("company",144);
		map.put("kaitongDate","描述");
		map.put("orderNum",144);
		map.put("orderPrice","描述");
		dataList.add(map);
		Map<String,Object> map1 = new HashMap<String, Object>();
		
		
		ExcelHandle handle = new  ExcelHandle();
		handle.writeListData(tempFilePath, dataListCell, dataList,1,false);

		Map<String,Object> dataMap = new  HashMap<String, Object>();
		List<String> dataCell = new ArrayList<String>();
		List<String> dataCell1 = new ArrayList<String>();
	/*	dataCell.add("title");
	
		dataMap.put("title", "三亚畅海游海洋运动俱乐部有限公司 -消费明细");
		
		handle.writeData(tempFilePath, dataCell, dataMap, 1);
		*/

		dataCell1.add("date");
		dataMap.put("date", "所属日期：2016-04-11 至 2016-05-11");
		
		
		handle.writeData(tempFilePath, dataCell1, dataMap, 1,1,"三亚畅海游海洋运动俱乐部有限公司 -消费明细");
		
		File file = new File("d:/data.xls");
		OutputStream os = new FileOutputStream(file);
		//写到输出流并关闭资源
		handle.writeAndClose(tempFilePath, os);
		
		os.flush();
		os.close();
		
		System.out.println("读取写入的数据----------------------------------%%%");
/*		System.out.println("name:"+handle.getValue(tempFilePath, "name", 0, file));*/
//		System.out.println("age:"+handle.getValue(tempFilePath, "age", 0, file));
//		System.out.println("sex:"+handle.getValue(tempFilePath, "sex", 0, file));
//		System.out.println("des:"+handle.getValue(tempFilePath, "des", 0, file));
//		System.out.println("读取写入的列表数据----------------------------------%%%");
//		List<Map<String,Object>> list = handle.getListValue(tempFilePath, dataListCell, 0, file);
//		for(Map<String,Object> data:list){
//			for(String key:data.keySet()){
//				System.out.print(key+":"+data.get(key)+"--");
//			}
//			System.out.println("");
//		}
		
		handle.readClose(tempFilePath);
	}
	
}
