package com.dsj.data.web.about;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "about")
public class AboutController {
	private final Logger LOGGER = LoggerFactory.getLogger(AboutController.class);
	
	@RequestMapping("page")
	public String page(){
		return "aboutUs/aboutus";
	} 
	
}
