package com.dsj.data.web.qrcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.data.web.utils.QRCodeUtil;

@Controller
@RequestMapping(value = "qr")
public class QrcodeController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(QrcodeController.class);
	
	@Value("${newHouseQrcodePrefix}")
	String newHouseQrcodePrefix;

	@RequestMapping(value = "qrcode")
	public void doPost(HttpServletRequest request, HttpServletResponse response,String houseId) throws ServletException, IOException {
		LOGGER.info(newHouseQrcodePrefix+houseId);
		BufferedImage image = QRCodeUtil.zxingCodeCreate(newHouseQrcodePrefix+houseId , 282,283);
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		out.close();
	}
}
