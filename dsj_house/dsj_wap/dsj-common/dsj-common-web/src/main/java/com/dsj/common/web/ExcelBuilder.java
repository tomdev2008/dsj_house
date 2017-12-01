package com.dsj.common.web;

import com.dsj.common.utils.ZipUtils;
import com.google.common.collect.Lists;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdg on 2016/11/28.
 */
public class ExcelBuilder {

    private static Logger logger= LoggerFactory.getLogger(ExcelBuilder.class);


    private String tpl;

    private String filename;

    public static Integer DEFAULT_SPLIT_SIZE=5000;

    private int splitLimit=DEFAULT_SPLIT_SIZE;

    private List append=new ArrayList();

    private String[] includes=new String[0];

    private List<File> tempExcelList=new ArrayList<>();

    private ExcelBuilder(){}

    public static ExcelBuilder newInstance(){
        return new ExcelBuilder();
    }


    public ExcelBuilder setTpl(String tpl) {
        this.tpl = tpl;
        return this;
    }

    public ExcelBuilder setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public ExcelBuilder setIncludes(String... includes) {
        this.includes = includes;
        return this;
    }

    public ExcelBuilder setSplitLimit(int splitLimit) {
        this.splitLimit = splitLimit;
        return this;
    }

    public ExcelBuilder addData(List data){

        //如果数据大于splitLimit，那么进行分割
        int tempLength = splitLimit - append.size();
        if (data.size() <= tempLength) {
            append.addAll(data);
        } else {
            append.addAll(data.subList(0, tempLength));
            //将数据写入XML,
            File file=writeExcel(append);
            //并将XML文件句柄暂存起来
            tempExcelList.add(file);
            //清空append
            append.clear();
            //并将append添加多余的数据
            append.addAll(data.subList(tempLength,data.size()));
        }
        return this;

    }



    public Object build(){
        if(filename==null){
            String nowStr= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
            filename=nowStr;
        }
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); // file
        String curFilename=null;

        if(append.size()>0){
            File file=writeExcel(append);
            tempExcelList.add(file);
        }
        if(tempExcelList.size()==0){
            File file=writeExcel(append);
            tempExcelList.add(file);
        }

        File dest=null;
        if(tempExcelList.size()>1){
           //进行合并
            curFilename=String.format("%s%s",filename,".zip");
            dest=new File(System.getProperty("java.io.tmpdir"),String.format("excel_%s.zip",System.currentTimeMillis()));
            ZipUtils.zip(dest,tempExcelList.toArray(new File[tempExcelList.size()]));
        }else{
            dest=tempExcelList.get(0);
            curFilename=String.format("%s%s",filename,".xlsx");
        }

        String isoFileName=new String(curFilename.getBytes(Charset.forName("utf-8")),Charset.forName("iso-8859-1"));
        httpHeaders.set("Content-Disposition", "attachment; filename=\""+isoFileName+"\"");

        return new ResponseEntity(new FileSystemResource(dest),httpHeaders, HttpStatus.OK);
    }


    private File writeExcel(List data){
        String filename=String.format("excel_%s.xlsx",System.currentTimeMillis());
        File file = new File(System.getProperty("java.io.tmpdir"),filename);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ExcelUtils.exportExcel(tpl, Lists.newArrayList(includes), data, outputStream);

            FileUtils.writeByteArrayToFile(file, outputStream.toByteArray());
        }catch (Exception e){
            logger.warn("导出EXCEL出错 ",e);
            throw new ExcelGenException(e);
        }
        return file;
    }

}
