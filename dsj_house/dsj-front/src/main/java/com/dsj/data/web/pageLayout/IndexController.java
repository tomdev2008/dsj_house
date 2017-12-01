package com.dsj.data.web.pageLayout;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.service.FwSpuService;
import com.dsj.modules.fw.vo.FwSpuVo;
import com.dsj.modules.pagelayout.po.PcLabelPo;
import com.dsj.modules.pagelayout.service.PcAgentAddService;
import com.dsj.modules.pagelayout.service.PcAgentService;
import com.dsj.modules.pagelayout.service.PcLabelService;
import com.dsj.modules.pagelayout.service.PcNewHouseService;
import com.dsj.modules.pagelayout.service.PcOldHouseService;
import com.dsj.modules.pagelayout.service.PcRentHouseService;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;
import com.dsj.modules.pagelayout.vo.WarrantOriginVo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.PropertyVo;


@Controller
@RequestMapping
public class IndexController {
	private final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private PcLabelService pcLabelService;
    @Autowired
    private PcNewHouseService pcNewHouseService;
    @Autowired
    private PcOldHouseService pcOldHouseService;
    @Autowired
    private AgentService agentService;
    @Autowired
	private FwSpuService fwSpuService;
    @Autowired
	private PropertyService propertyService;
	 //租房详情页
	@RequestMapping
	public String rentHouseList(Model model,HttpServletRequest request, Long id ){
		//新房标签查询
				List<PcLabelPo> newHouseList=pcLabelService.getLableList();
				model.addAttribute("newHouseList", newHouseList);
				//新房楼盘查询
			    List<NewHouseLabelVo> newHouseDirectoryList=pcNewHouseService.getLableNewHouseListPage();
			    model.addAttribute("newHouseDirectoryList", newHouseDirectoryList);
			    
			  //二手房标签查询
				List<PcLabelPo> oldHouseList=pcLabelService.getLableOldList();
				model.addAttribute("oldHouseList", oldHouseList);
				List<NewHouseLabelVo> oldHouseDirectoryList=pcOldHouseService.getLableOldHouseListPage();
				model.addAttribute("oldHouseDirectoryList", oldHouseDirectoryList);
				
				//租房
//				 List<RentHouseOriginVo> rentHouseLists=rentHouseOriginService.getRentHouseListPage();
//			   model.addAttribute("rentHouseList", rentHouseLists);
				 //权证
			    List<WarrantOriginVo> warrantLists=pcOldHouseService.getWarrantListPage();		 
			    model.addAttribute("warrantLists", warrantLists);
			//经纪人
			List<AgentVo> agentDirectoryList=agentService.getRentHousePage();
			model.addAttribute("agentDirectoryList", agentDirectoryList);
			//banner
			NewHouseLabelVo banner=pcOldHouseService.getBanner();
			model.addAttribute("banner", banner);
			
			HttpSession session = request.getSession();
			session.setAttribute(CommConst.COOKIE_PC_LOGIN_URL, "/");
		return "pageLayout/web-index";
	}
	
	//权证首页
	@RequestMapping("warrant/index")
	public String fwuserIndex(Model model ) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<FwSpuVo> list = null;
			List<PropertyVo> proList = null;
			try {
				list = fwSpuService.getSanFwSpuVoList(map);
				proList = propertyService.getProByTuiJian();
			} catch (Exception e) {
				LOGGER.error("权证商品列表异常", e);
			}
			model.addAttribute("list", list);
			model.addAttribute("proList", proList);
		return "warrant/warrant_index";
	}
}
