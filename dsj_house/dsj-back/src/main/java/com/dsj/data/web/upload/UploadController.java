package com.dsj.data.web.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.BaseController;
import com.dsj.data.service.UploadService;
import com.dsj.data.web.utils.QiniuUtils;



@Controller
@RequestMapping(value  =  "back/upload")
public class UploadController extends BaseController {
	
	private  final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	UploadService uploadService;
	
	@RequestMapping
	@ResponseBody
	public String upImg(MultipartHttpServletRequest request){
	        MultiValueMap<String,MultipartFile> map = request.getMultiFileMap();
	       String  syspath=System.getProperty("java.io.tmpdir");
	        File fs = null;
	        String extName ="";
	        if(!syspath.endsWith("/")){
	        	syspath+="/";
	        }

	        for (List<MultipartFile> l : map.values()) {
	            MultipartFile  f = l.get(0) ;
	            String newName  = DateUtils.formatDate(new Date(),"yyyyMMddHHmmsss");
	            extName = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
	            fs = new File(syspath+newName+extName);
				logger.info("开始创建临时图片:{}",fs.getAbsolutePath());
	            try {
	            		if(!fs.getParentFile().exists()){
	            			fs.getParentFile().mkdirs();
	            		}
	            		if(!fs.exists()){
							fs.createNewFile();
						}
	                    f.transferTo(fs);
	            } catch (Exception e) {
	            	LOGGER.error("上传出错：",e.getMessage(),e);
	                return null;
	            }
	        }

			String key= null;
	        try {
	        	//七牛
	        	//key = QiniuUtils.upload(fs);
	        	
	        	//阿里云
	        	


				byte[] fileContent= FileUtils.readFileToByteArray(fs);
	            
				key=uploadService.uploadImg(fileContent,extName);
				if(StringUtils.isNotBlank(key)){
					key = ConfigUtils.instance.getOssAccessUrl()+key;
				}
				logger.info("开始上传图片:key->{}",key);
	        } catch (Exception e) {
	        	LOGGER.error("上传出错：",e.getMessage(),e);
	          
	        }

	        return key;
	   }
}
