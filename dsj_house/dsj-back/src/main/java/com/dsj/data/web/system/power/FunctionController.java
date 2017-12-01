package com.dsj.data.web.system.power;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.modules.system.po.FunctionPo;
import com.dsj.modules.system.service.FunctionService;
@Controller
@RequestMapping(value = "back/**/function")
public class FunctionController {
	private final Logger LOGGER = LoggerFactory.getLogger(FunctionController.class);

	@Autowired
	private FunctionService functionService;
    /**
     * 菜单查询
     * @param req
     * @return
     */
	@RequestMapping("findAllFunction")
	public String findAllFunction(Model model,Long id){
		//所有菜单
		try {
			List<FunctionPo> list = new ArrayList<FunctionPo>();
			if (StringUtils.isNotEmpty(id.toString())) {
				list = functionService.findAllFunction(id);
			}
			model.addAttribute("roleId", id);
			model.addAttribute("functionList", list);
		} catch (Exception e) {
			LOGGER.error("菜单查询出错",e);
		}
	   return "system/roleFunction/function";
	}
	
}
