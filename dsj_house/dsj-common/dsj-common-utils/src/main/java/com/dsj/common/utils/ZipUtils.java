package com.dsj.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private static Logger logger= LoggerFactory.getLogger(ZipUtils.class);
     /**@param srcFiles 需压缩的文件路径及文件名
     * @param desFile 保存的文件名及路径
     * @return  如果压缩成功返回true
     */
     public static boolean zip(File desFile,File... srcFiles) {
          boolean isSuccessful = false;
          String[] fileNames = new String[srcFiles.length];
          for (int i = 0; i < srcFiles.length; i++) {
               fileNames[i] = srcFiles[i].getName();
          }

          try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
                ZipOutputStream zos = new ZipOutputStream(bos);
                String entryName = fileNames[0];
                for (int i = 0; i < fileNames.length; i++) {
                     entryName = fileNames[i];
                     // 创建Zip条目
                     ZipEntry entry = new ZipEntry(entryName);
                     zos.putNextEntry(entry);
                     BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFiles[i]));
                     byte[] b = new byte[1024];
                    int len=-1;
                     while ( (len=bis.read(b, 0, 1024)) != -1) {
                          zos.write(b, 0, len);
                     }
                     bis.close();
                     zos.closeEntry();
                }

                zos.flush();
                zos.close();
                isSuccessful = true;
          } catch (IOException e) {
              logger.error("压缩文件出错：%s",desFile.getAbsolutePath(),e);
          }

          return isSuccessful;
     }
}
