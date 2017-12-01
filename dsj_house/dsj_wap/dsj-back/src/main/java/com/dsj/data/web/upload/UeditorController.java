package com.dsj.data.web.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.data.web.utils.Uploader;

@Controller
@RequestMapping(value  =  "ueditor/upload")
public class UeditorController {
	
	private  final Logger LOGGER = LoggerFactory.getLogger(Uploader.class);

	@RequestMapping("image")
	@ResponseBody
	public void ossSignature(HttpServletResponse response,HttpServletRequest request) throws Exception {
		try{
			request.setCharacterEncoding(Uploader.ENCODEING);
			response.setCharacterEncoding(Uploader.ENCODEING);
			Uploader up = new Uploader(request);
			String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
			up.setAllowFiles(fileType);
			up.setMaxSize(500 * 1024); //单位KB
			up.upload();
			response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
		}catch(Exception e){
			LOGGER.error("上传出错了：",e);
		}
	}

}
