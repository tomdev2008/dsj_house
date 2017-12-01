package com.dsj.data.wap.qrcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.data.wap.utils.UserUtil;
import com.dsj.data.web.utils.QRCodeUtil;	


@Controller
@RequestMapping(value = "qr")
public class QrcodeController {
	private final Logger LOGGER = LoggerFactory.getLogger(QrcodeController.class);
	
	
	@RequestMapping(value = "qrcode")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
		
		Integer userId = UserUtil.getCurrentUser(request).getId().intValue();
		//http://211.159.180.106:7070/dsj-agent-back/login/to_login
//http://localhost:8080/dsj-front/remark/page?userId=482
	BufferedImage image = QRCodeUtil.zxingCodeCreate("http://www.dasoujia.com/remark/page?userId="+userId,282,283 );
	  OutputStream out = response.getOutputStream();
	  ImageIO.write(image,"jpg",out);
	  out.close();
	}
}
