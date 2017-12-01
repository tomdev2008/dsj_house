package com.dsj.common.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.dsj.common.utils.excel.ExcelHandle;

/**
 * Created by wdaogang on 2016/10/28.
 */
public class ExcelUtils {

    public static void exportExcel(String tplPath, List<String> includes, List data, OutputStream os) throws IOException {
        ExcelHandle excelHandle=new ExcelHandle();
        excelHandle.writeListData(tplPath,includes,data,0,false);
        excelHandle.writeAndClose(tplPath,os);
    }
}
