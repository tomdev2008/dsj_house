package com.dsj.data.web.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.BaseController;
import com.dsj.data.web.service.UploadService;



@Controller
@RequestMapping(value  =  "front/upload")
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
	
	@RequestMapping("head_portrait")
	@ResponseBody
	public Map<String,String> headPortrait(String smallBase64,String bigBase64){
	
            
		/*
		String key=uploadService.saveImg(is,smallBase64.split(";")[0].split(":")[1].replace("image/", ""));
		if(StringUtils.isNotBlank(key)){
			key = ConfigUtils.instance.getOssAccessUrl()+key;
			System.out.println(key);
		}*/
		Map<String,String> map=new HashMap<String,String>();
		String smallImage=uploadService.uploadHeadProtrait(smallBase64);
		String bigImage=uploadService.uploadHeadProtrait(bigBase64);
		map.put("smallImage",smallImage );
		map.put("bigImage", bigImage);
		return map;
	}
	
	
	
}
