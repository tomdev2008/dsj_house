package com.dsj.common.web;

import com.dsj.common.command.BaseCommand;
import com.dsj.common.enums.OutputEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.google.common.collect.Lists;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

/**
 * 下载工具类，目前用于Excel文件下载。
 * Created by wdaogang on 2016/10/28.
 */
public class ResponseUtils {


	public static Object response(BaseCommand cmd, String tplPath,Function<PageParam, PageBean> func,String... includes){
        return response(cmd.getOutput(),cmd.asPageParam(),tplPath,func,includes);
    }
    public static Object response(OutputEnum output,PageParam pageParam, String tplPath,Function<PageParam, PageBean> func,String... includes){
        if(OutputEnum.EXCEL.equals(output)){
            return downloadExcel(tplPath,func,includes);
        }else{
            PageBean page= (PageBean) func.apply(pageParam);
            return new PageDateTable<PageBean>(page);
        }
    }

    public static Object downloadExcel(String tplPath,Function<PageParam, PageBean> func,String... includes){
        int pageNumber=1;
        ExcelBuilder excelBuilder=ExcelBuilder.newInstance().setTpl(tplPath).setIncludes(includes);
        PageBean data=null;
        do {
            PageParam pageParam=new PageParam();
            pageParam.setPageNum(pageNumber);
            pageParam.setNeedCount(false);
            pageParam.setNumPerPage(ExcelBuilder.DEFAULT_SPLIT_SIZE);
            data=  func.apply(pageParam);
            excelBuilder.addData(data.getRecordList());
            pageNumber++;
        }while(data.getRecordList().size()!=0);
        return excelBuilder.build();
    }

    /**
     *
     * @param cmd
     * @param tplPath
     * @param page
     * @param includes
     * @return
     * @throws IOException
     */
    public static Object response(BaseCommand cmd, String tplPath,PageBean page,String... includes) throws IOException {
        String nowStr=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        return response(cmd,tplPath,String.format("%s.xlsx",nowStr),page,includes);
    }

    public static Object response(BaseCommand cmd, String tplPath, String filename,PageBean page,String... includes) throws IOException {
        if(OutputEnum.EXCEL.equals(cmd.getOutput())){
            return ResponseUtils.downloadExcel(tplPath,filename,page.getRecordList(),includes);
        }
        return new PageDateTable<PageBean>(page);
    }

    public static Object response(OutputEnum output, String tplPath, String filename,PageBean page,String... includes) throws IOException {
        if(OutputEnum.EXCEL==output){
            return ResponseUtils.downloadExcel(tplPath,filename,page.getRecordList(),includes);
        }
        return new PageDateTable<PageBean>(page);
    }
    /**
     * 下载EXCEL
     * @param tpl 模板文件路径
     * @param filename 显示下载的文件名
     * @param data 列表数据
     * @param includes 显示字段
     * @return 文件字节内容
     * @throws IOException
     */
    public static ResponseEntity downloadExcel(String tpl,String filename,  List data,String... includes) throws IOException {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); //.exe file
        httpHeaders.set("Content-Disposition", "attachment; filename=\""+new String(filename.getBytes(Charset.forName("UTF-8")),"iso-8859-1")+"\"");
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ExcelUtils.exportExcel(tpl, Lists.newArrayList(includes) ,data,outputStream);
        ResponseEntity entity=new ResponseEntity(outputStream.toByteArray(),httpHeaders,HttpStatus.OK);
        return entity;
    } /**
     * 下载EXCEL
     * @param tpl 模板文件路径
     * @param data 列表数据
     * @param includes 显示字段
     * @return 文件字节内容
     * @throws IOException
     */
    public static ResponseEntity downloadExcel(String tpl,List data,String... includes) throws IOException {
        String nowStr=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"))+".xlsx";
        return  downloadExcel( tpl,nowStr,  data,includes);
    }




}
