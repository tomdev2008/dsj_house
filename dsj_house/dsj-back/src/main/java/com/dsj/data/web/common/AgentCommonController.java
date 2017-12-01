package com.dsj.data.web.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.rentHouse.RentHouseOriginController;
import com.dsj.modules.oldhouse.vo.SelectVo;
import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;

@Controller
@RequestMapping(value = "back/**/common/agent")
public class AgentCommonController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentCommonController.class);

	@Autowired
	private AgentService agentService;
	
	/**
	 * 房源创建时关联的经纪人查询
	 */
	@RequestMapping("getAgentUser")
	@ResponseBody
	public AjaxResultVo getAgentUser(String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("delFlag", DeleteStatusEnum.NDEL.getValue() );
				map.put("userType", UserType.AGENT.getValue());
				map.put("name", name);
				map.put("auditStatus", AgentStatus.AUDIT_SUCCESS.getCode());
				
				List<AgentVo> list = agentService.getAgentBySelect(map);
				for (AgentVo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getUserId());
					vo.setText(po.getName()+"-"+po.getTellPhone()+"-"+po.getCompanyName());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
}
