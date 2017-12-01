package com.dsj.data.web.user;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.BaseController;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.system.service.EvelopersService;
import com.dsj.modules.system.vo.UserVo;


/**
 * 登录
 */
@Controller
@RequestMapping(value = "back/user")
public class UserController extends BaseController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private EvelopersService evelopersService;
	
	@RequestMapping("getUser")
	@ResponseBody
	public UserVo getUser(Model model) {
		UserVo vo=new UserVo();
		BeanUtils.copyProperties( ShiroUtils.getSessionUser(),vo);
		vo.setDeveloperProductName(ConfigUtils.instance.getDeveloperProductName());
		return vo;
	}
	
	
	@RequestMapping("export")
	@ResponseBody
	public void download(HttpServletRequest request, HttpServletResponse response){  
        //String fileName = ConfigUtils.instance.getChengNuoBook();  
		String fileName = "dsj_promise.doc";  
        try {  
            request.setCharacterEncoding("utf-8");  
            fileName = new String(fileName.getBytes("iso-8859-1"), "utf-8");  
            //获取文件路径  
            String filePath = "/root/data/excel/"+fileName;  
            filePath = filePath == null ? "" : filePath;  
               //设置向浏览器端传送的文件格式  
                        response.setContentType("application/x-download");  
  
                        fileName = URLEncoder.encode(fileName, "UTF-8");  
                response.addHeader("Content-Disposition", "attachment;filename="  
                        + fileName);  
                FileInputStream fis = null;  
                OutputStream os = null;  
                try {  
                    os = response.getOutputStream();  
                    fis = new FileInputStream(filePath);  
                    byte[] b = new byte[1024 * 10];  
                    int i = 0;  
                    while ((i = fis.read(b)) > 0) {  
                        os.write(b, 0, i);  
                    }  
                    os.flush();  
                    os.close();  
                } catch (Exception e) {  
                	LOGGER.error("error:{}",e);
                    e.printStackTrace();  
                } finally {  
                    if (fis != null) {  
                        try {  
                            fis.close();  
                        } catch (IOException e) { 
                        	LOGGER.error("error:{}",e);
                            e.printStackTrace();  
                        }  
                    }  
                    if (os != null) {  
                        try {  
                            os.close();  
                        } catch (IOException e) { 
                        	LOGGER.error("error:{}",e);
                            e.printStackTrace();  
                        }  
                    }  
                }  
        } catch (UnsupportedEncodingException e) {  
        	LOGGER.error("error:{}",e);
            e.printStackTrace();  
        }  
    }
}
