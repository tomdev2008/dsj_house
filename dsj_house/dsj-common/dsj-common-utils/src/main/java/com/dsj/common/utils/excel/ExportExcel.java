package com.dsj.common.utils.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档
 * 
 * @author cengl
 * @version v1.0
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> {
	public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String sheet, String[] headers,
			Collection<T> dataset, OutputStream out) {
		exportExcel(sheet, headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out, String pattern) {
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,
			Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index += 1;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					// if (value instanceof Integer) {
					// int intValue = (Integer) value;
					// cell.setCellValue(intValue);
					// } else if (value instanceof Float) {
					// float fValue = (Float) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(fValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Double) {
					// double dValue = (Double) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(dValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Long) {
					// long longValue = (Long) value;
					// cell.setCellValue(longValue);
					// }
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
								1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(
								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					}  else if (value instanceof BigDecimal) {
						cell.setCellValue(((BigDecimal) value).intValue());
					}else if (value instanceof Float) {
						cell.setCellValue(((Float) value).intValue());
					}else {
						// 其它数据类型都当作字符串简单处理
						textValue = value == null ? "" : value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {

			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(String filePath, String[] sheetName,
			List<? extends Object[]> title,
			List<? extends List<? extends Object[]>> data)
			throws FileNotFoundException, IOException {
		// 创建并获取工作簿对象
		Workbook wb = getWorkBook(sheetName, title, data);
		// 写入到文件
		FileOutputStream out = new FileOutputStream(filePath);
		wb.write(out);
		out.close();
	}

	public static Workbook getWorkBook(String[] sheetName,
			List<? extends Object[]> title,
			List<? extends List<? extends Object[]>> data)
			throws FileNotFoundException, IOException {
		// 创建工作簿，支持2007及以后的文档格式
		Workbook wb = new XSSFWorkbook();
		// 创建一个工作表sheet
		Sheet sheet = null;
		// 申明行
		Row row = null;
		// 申明单元格
		Cell cell = null;
		// 单元格样式
		CellStyle titleStyle = wb.createCellStyle();
		CellStyle cellStyle = wb.createCellStyle();
		/*
		 * //字体样式 Font font=wb.createFont(); //粗体
		 * font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		 * titleStyle.setFont(font);
		 */
		// 水平居中
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 垂直居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 水平居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 垂直居中
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 标题数据
		Object[] title_temp = null;
		// 行数据
		Object[] rowData = null;
		// 工作表数据
		List<? extends Object[]> sheetData = null;
		// 遍历sheet
		for (int sheetNumber = 0; sheetNumber < sheetName.length; sheetNumber++) {
			// 创建工作表
			sheet = wb.createSheet();
			// 设置工作表名称
			wb.setSheetName(sheetNumber, sheetName[sheetNumber]);
			// 设置标题
			title_temp = title.get(sheetNumber);
			row = sheet.createRow(0);
			// 写入标题
			for (int i = 0; i < title_temp.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(titleStyle);
				cell.setCellValue(title_temp[i].toString());
			}
			try {
				sheetData = data.get(sheetNumber);
			} catch (Exception e) {
				continue;
			}
			// 写入行数据
			for (int rowNumber = 0; rowNumber < sheetData.size(); rowNumber++) {
				// 如果没有标题栏，起始行就是0，如果有标题栏，行号就应该为1
				row = sheet.createRow(title_temp == null ? rowNumber
						: (rowNumber + 1));
				rowData = sheetData.get(rowNumber);
				for (int columnNumber = 0; columnNumber < rowData.length; columnNumber++) {
					cell = row.createCell(columnNumber);
					cell.setCellStyle(cellStyle);

					if (!rowData[columnNumber].toString().contains("-")
							&& !rowData[columnNumber].toString().contains("星期")) {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						if (!rowData[columnNumber].toString().equals("")&&!rowData[columnNumber].toString().equals("总计")) {
							cell.setCellValue(Integer
									.parseInt(rowData[columnNumber].toString()));
						} else {
							cell.setCellValue(rowData[columnNumber].toString());
						}

					} else {
						cell.setCellValue(rowData[columnNumber].toString());
					}

				}
			}
		}
		return wb;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		String[] title1 = { "第一列a", "第二列b", "第三列c" };
		String[] title2 = { "第一列d", "第二列e", "第三列f" };
		String[] title3 = { "第一列h", "第二列i", "第三列j" };
		List<String[]> titles = new ArrayList<String[]>();
		titles.add(title1);
		titles.add(title2);
		titles.add(title3);
		String[] data1 = { "i", "j", "k" };
		String[] data2 = { "m", "n", "o" };
		String[] data3 = { "x", "y", "z" };
		List<String[]> data = new ArrayList<String[]>();
		data.add(data1);
		data.add(data2);
		data.add(data3);
		List<List<String[]>> data_ = new ArrayList<List<String[]>>();
		data_.add(data);
		String[] sheetName = { "第一张表", "第二张表", "第三张表" };
		new ExportExcel().writeToFile("D:\\xx.xlsx", sheetName, titles, data_);
	}

	/*
	 * public static void main(String[] args) { // 测试学生 // ExportExcel<Student>
	 * ex = new ExportExcel<Student>(); // String[] headers = // { "学号", "姓名",
	 * "年龄", "性别", "出生日期" }; // List<Student> dataset = new
	 * ArrayList<Student>(); // dataset.add(new Student(10000001, "张三", 20,
	 * true, new Date())); // dataset.add(new Student(20000002, "李四", 24, false,
	 * new Date())); // dataset.add(new Student(30000003, "王五", 22, true, new
	 * Date())); // 测试图书 // ExportExcel<Book> ex2 = new ExportExcel<Book>(); //
	 * String[] headers2 = // { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
	 * "图书出版社", "封面图片" }; // List<Book> dataset2 = new ArrayList<Book>(); // try
	 * // { // BufferedInputStream bis = new BufferedInputStream( // new
	 * FileInputStream("D://book.bmp")); // byte[] buf = new
	 * byte[bis.available()]; // while ((bis.read(buf)) != -1) // { // // // }
	 * // dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567", //
	 * "清华出版社", buf)); // dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f,
	 * "1234567", // "阳光出版社", buf)); // dataset2.add(new Book(3, "DOM艺术",
	 * "lenotang", 300.33f, "1234567", // "清华出版社", buf)); // dataset2.add(new
	 * Book(4, "c++经典", "leno", 400.33f, "1234567", // "清华出版社", buf)); //
	 * dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567", // "汤春秀出版社",
	 * buf));
	 * 
	 * // OutputStream out = new FileOutputStream("E://a.xls"); // OutputStream
	 * out2 = new FileOutputStream("E://b.xls"); // ex.exportExcel(headers,
	 * dataset, out); // ex2.exportExcel(headers2, dataset2, out2); //
	 * out.close(); // JOptionPane.showMessageDialog(null, "导出成功!"); //
	 * System.out.println("excel导出成功！"); // } // catch (FileNotFoundException e)
	 * // { // e.printStackTrace(); // } // catch (IOException e) // { //
	 * e.printStackTrace(); // } }
	 */

}