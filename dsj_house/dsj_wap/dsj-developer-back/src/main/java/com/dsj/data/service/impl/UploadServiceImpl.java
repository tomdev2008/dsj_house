package com.dsj.data.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.data.service.UploadService;
import com.dsj.data.web.upload.UploadController;
import com.dsj.data.web.utils.upload.ImageFileUploadUtil;

/**
 * Created by liu on 2016/11/8.
 */
@Service
public class UploadServiceImpl implements UploadService {
	private  final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    private ImageFileUploadUtil fileUtil;

    /**
     * 上传文件
     * @param bytes
     * @return 返回阿里oss访问key，  返回null为失败，
     */
    @Override
    public String uploadImg(byte[] bytes,String extName){

        return fileUtil.saveImg(bytes,extName);
    }


    /**
     * 获取图片的浏览地址
     * @param key
     * @return
     */
    @Override
    public String getImgUrl(String key) {
        return fileUtil.getImgUrl(key);
    }



	 @Override
	 public String uploadHeadProtrait(String base64){
		
		InputStream is=baseToInputStream(base64.split(",")[1]);
		 String  syspath=System.getProperty("java.io.tmpdir");
	        File fs = null;
	        String extName ="";
	        if(!syspath.endsWith("/")){
	        	syspath+="/";
	        }
	         extName =base64.split(";")[0].split(":")[1].replace("image/", "");
	        String newName  = DateUtils.formatDate(new Date(),"yyyyMMddHHmmsss")+"."+extName;
           
	        saveToImgByStr(is,syspath,newName);
			//logger.info("开始创建临时图片:{}",fs.getAbsolutePath());
            try {
            	fs=new File(syspath+newName);
            		if(!fs.getParentFile().exists()){
            			fs.getParentFile().mkdirs();
            		}
            		if(!fs.exists()){
						fs.createNewFile();
					}
            		
            } catch (Exception e) {
            	LOGGER.error("上传出错：",e.getMessage(),e);
                return null;
            }
			String key= null;
            try {
	        	//七牛
	        	//key = QiniuUtils.upload(fs);
	        	
	        	//阿里云
				byte[] fileContent= FileUtils.readFileToByteArray(fs);
				key=uploadImg(fileContent,"."+extName);
				if(StringUtils.isNotBlank(key)){
					key = ConfigUtils.instance.getOssAccessUrl()+key;
				}
				LOGGER.info("开始上传图片:key->{}",key);
	        } catch (Exception e) {
	        	LOGGER.error("上传出错：",e.getMessage(),e);
	          
	        }
            return key;
	}


public void inputstreamtofile(InputStream ins,File file) throws IOException{
	OutputStream os = new FileOutputStream(file);
	int bytesRead = 0;
	byte[] buffer = new byte[8192];
	while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
	os.write(buffer, 0, bytesRead);
	}
	os.close();
	ins.close();
}

public static InputStream baseToInputStream(String base64string){  
	ByteArrayInputStream stream = null;
    try {
    	Base64 decoder = new Base64(); 
    	byte[] bytes1 = decoder.decode(base64string);
    	stream = new ByteArrayInputStream(bytes1);  
    } catch (Exception e) {
    	// TODO: handle exception
    }
    return stream;  
 } 

   /**
 * 将接收的字符串转换成图片保存
 * @param imgStr 二进制流转换的字符串
 * @param imgPath 图片的保存路径
 * @param imgName 图片的名称
 * @return
 *      1：保存正常
 *      0：保存失败
 */
public static int saveToImgByStr(InputStream in,String imgPath,String imgName){
    int stateInt = 1;
        try {
             
            // 将字符串转换成二进制，用于显示图片 
            // 将上面生成的图片格式字符串 imgStr，还原成图片显示 
 
            File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
            FileOutputStream fos=new FileOutputStream(file);
               
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
            fos.close();
            in.close();
 
        } catch (Exception e) {
            stateInt = 0;
            e.printStackTrace();
        } finally {
        }
    return stateInt;
}

}
