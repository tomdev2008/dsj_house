package com.dsj.data.web.utils.upload;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.data.web.utils.QiniuUtils;


//import util.other.Utils;

/**
 * 工具类 - 上传图片文件处理，文件名路径处理
 *
 * */
@Component
public class ImageFileUploadUtil {

	private static Logger logger= LoggerFactory.getLogger(ImageFileUploadUtil.class);

	// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
	public  static  String getRandomCode(){
		String s = DateUtils.formatDate(new Date(), "yyyyMMddHHmmsss");
		Double d = Math.random();
		if (d < 0.1) d = d + 0.1;
		int t = (int) (d*100000);
		return s+Integer.toString(t);
	}

	/**
	 *
	 * @功能：上传oss
	 * @作者: zgz
	 * @参数： @param fileRoot
	 * @参数： @param imagePath
	 * @参数： @param imagePathFileName
	 * @参数： @param fileUrlConfig
	 * @参数： @param imageInfoPath
	 * @参数： @return
	 * @返回值：String
	 * @日期: 2016-6-30 下午8:01:52
	 */
	public static String uploadImageFile(OSSClient OC,String fileRoot,File imagePath,String imagePathFileName ,String imageInfoPath,String oss_bucket)  {
		if (imagePath != null) {
			try {
				// 需要修改文件的后缀名称
				String newName = imagePathFileName.substring(imagePathFileName.lastIndexOf("."));
				// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
				String randomCode= getRandomCode();
				//原始图片文件编译后的新名称
				newName = randomCode + newName;
				//定义新文件保存实际路径+配置文件systemConfig.properties中定义的文件生成目录规则：年月日
				String newImagePath = fileRoot + "/"+ imageInfoPath +  "/" ;
				//定义文件对象，需要加上盘符
				File savefile = new File(("/tmp/fileUploadRoot/"+ newImagePath), newName);
				//判断文件是否存在，不存在则新创建
				if (!savefile.getParentFile().exists()) {
					savefile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(imagePath, savefile);
				imagePathFileName = newImagePath + newName;
				String md5key = OSSUnit.uploadObject2OSS(OC, savefile, oss_bucket,imagePathFileName);
				//2016-6-21 zgz add 删除本地
				savefile.delete();
				return imagePathFileName;
			} catch (IOException e) {
				e.printStackTrace();
				return "false_error";//上传失败
			}
		}
		return imagePathFileName;
	}

	public OSSClient getClient(){
		return new OSSClient(ConfigUtils.instance.getOssEndPoint(), ConfigUtils.instance.getAccessId(), ConfigUtils.instance.getAccessKey()) ;
	}

	public OSSObject getObject(String key){
		return getClient().getObject(ConfigUtils.instance.getBucket(), key);
	}


	public String saveImg( File file ,String extName){

		String newName = getRandomCode();

		String s = uploadImageFile(getClient(), "upload", file,newName+extName , "pic",ConfigUtils.instance.getBucket());

		if(s.equals("false_error")){
			return null;
		}
		return s;
	}
	
	public String saveImg( InputStream is,String imgType){
		try {
			return	OSSUnit.uploadInputStream2OSS(getClient(), is, ConfigUtils.instance.getBucket(), "upload/pic",imgType);
		} catch (IOException e) {
			e.printStackTrace();
			return "false_error";
		}
		//return s;
	}
	

	public String saveImg( byte[] bytes,String extName){
		File file=new File(System.getProperty("java.io.tmpdir"), RandomStringUtils.randomAlphanumeric(20));
		try {

			FileUtils.writeByteArrayToFile(file, bytes);
			return saveImg(file,extName);
		} catch (Exception e) {
			logger.warn("保存文件失败！{}",e.getMessage());
			return null;
		}
	}



	public String getImgUrl(String key) {
		return String.format(ConfigUtils.instance.getOssAccessUrl(),key);
	}

}
