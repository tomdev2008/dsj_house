package com.dsj.modules.oldHouseParser.utils;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;
import java.io.InputStream;  
  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
import com.aliyun.oss.OSSClient;  
import com.aliyun.oss.model.Bucket;  
import com.aliyun.oss.model.OSSObject;  
import com.aliyun.oss.model.ObjectMetadata;  
import com.aliyun.oss.model.PutObjectResult;  
/**   
 * @作用：阿里云OSS文件上传
 * @功能：
 * @作者: zgz
 * @日期：2016-6-15 上午11:05:30 
 * @版本：V1.0   
 */
public class OSSUnit {  
      
    private static final Logger LOG = LoggerFactory.getLogger(OSSUnit.class);  
      
    /** 
     * 获取阿里云OSS客户端对象 
     * */  
    public static final OSSClient getOSSClient(String ENDPOINT,String ACCESS_KEY_ID,String ACCESS_KEY_SECRET){  
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);  
    }  
      
    /** 
     * 新建Bucket  --Bucket权限:私有 
     * @param bucketName bucket名称 
     * @return true 新建Bucket成功 
     * */  
    public static final boolean createBucket(OSSClient client, String bucketName){  
        Bucket bucket = client.createBucket(bucketName);   
        return bucketName.equals(bucket.getName());  
    }  
      
    /** 
     * 删除Bucket  
     * @param bucketName bucket名称 
     * */  
    public static final void deleteBucket(OSSClient client, String bucketName){  
        client.deleteBucket(bucketName);   
        LOG.info("删除" + bucketName + "Bucket成功");  
    }  
      
    /** 
     * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代 
     * @param client OSS客户端 
     * @param file 上传文件 
     * @param bucketName bucket名称 
     * @param diskName 上传文件的目录  --bucket下文件的路径 
     * @return String 唯一MD5数字签名 
     * @throws IOException 
     * */  
	public static final String uploadObject2OSS(OSSClient client, File file,
			String bucketName, String diskName) throws IOException {
		String resultStr = null;
		InputStream is = new FileInputStream(file);
		String fileName = file.getName();
		Long fileSize = file.length();
		if (diskName.startsWith("/")){
			diskName = diskName.substring(1);
		}
		// 创建上传Object的Metadata
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(is.available());
		metadata.setCacheControl("no-cache");
		metadata.setHeader("Pragma", "no-cache");
		metadata.setContentEncoding("utf-8");
		metadata.setContentType(getContentType(fileName));
		metadata.setContentDisposition("filename/filesize=" + fileName + "/"
				+ fileSize + "Byte.");
		// 上传文件
		PutObjectResult putResult = client.putObject(bucketName, diskName, is,
				metadata);
		// 解析结果
		resultStr = putResult.getETag();

		return resultStr;
	}

	   /** 
     * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代 
     * @param client OSS客户端 
     * @param file 上传文件 
     * @param bucketName bucket名称 
     * @param diskName 上传文件的目录  --bucket下文件的路径 
     * @return String 唯一MD5数字签名 
     * @throws IOException 
     * */  
	public static final String uploadInputStream2OSS(OSSClient client, InputStream is,
			String bucketName, String diskName) throws IOException  {
		String resultStr = null;
		String fileName=diskName.split("/")[diskName.split("/").length-1];
		if (diskName.startsWith("/")){
			diskName = diskName.substring(1);
		}
		// 创建上传Object的Metadata
		ObjectMetadata metadata = new ObjectMetadata();
		try {
			metadata.setContentLength(is.available());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		metadata.setCacheControl("no-cache");
		metadata.setHeader("Pragma", "no-cache");
		metadata.setContentEncoding("utf-8");
		metadata.setContentType(getContentType(fileName));
		System.out.println(is.available());
		metadata.setContentDisposition("filename/filesize=" + fileName + "/"
				+ is.available() + "Byte.");
		// 上传文件
		PutObjectResult putResult = client.putObject(bucketName, diskName, is,
				metadata);
		// 解析结果
		resultStr = putResult.getETag();

		return resultStr;
	}
      
      
    /**  
     * 根据key获取OSS服务器上的文件输入流 
     * @param client OSS客户端 
     * @param bucketName bucket名称 
     * @param diskName 文件路径 
     * @param key Bucket下的文件的路径名+文件名 
     */    
     public static final InputStream getOSS2InputStream(OSSClient client, String bucketName, String diskName, String key){   
        OSSObject ossObj = client.getObject(bucketName, diskName + key);  
        return ossObj.getObjectContent();     
     }    
      
   /**  
    * 根据key删除OSS服务器上的文件  
    * @param client OSS客户端 
    * @param bucketName bucket名称 
    * @param diskName 文件路径 
    * @param key Bucket下的文件的路径名+文件名 
    */    
      public static void deleteFile(OSSClient client, String bucketName, String diskName, String key){    
        client.deleteObject(bucketName, diskName + key);   
        LOG.info("删除" + bucketName + "下的文件" + diskName + key + "成功");  
      }    
       
    /**  
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType  
     * @param fileName 文件名 
     * @return 文件的contentType    
     */    
     public static final String getContentType(String fileName){    
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);  
        if("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";  
        if("gif".equalsIgnoreCase(fileExtension)) return "image/gif";  
        if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)  || "png".equalsIgnoreCase(fileExtension) ) return "image/jpeg";  
        if("html".equalsIgnoreCase(fileExtension)) return "text/html";  
        if("txt".equalsIgnoreCase(fileExtension)) return "text/plain";  
        if("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";  
        if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) return "application/vnd.ms-powerpoint";  
        if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) return "application/msword";  
        if("xml".equalsIgnoreCase(fileExtension)) return "text/xml";  
        return "text/html";    
     }    
  
}  