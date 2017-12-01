package com.dsj.data.web.pageLayout;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.newHouse.guardian.GuardianController;
import com.dsj.modules.pagelayout.po.PcAgentAddPo;
import com.dsj.modules.pagelayout.po.PcAgentPo;
import com.dsj.modules.pagelayout.po.PcLabelPo;
import com.dsj.modules.pagelayout.po.PcNewHousePo;
import com.dsj.modules.pagelayout.po.PcOldHousePo;
import com.dsj.modules.pagelayout.po.PcRentHousePo;
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
import com.dsj.modules.system.vo.AgentVo;

@Controller
@RequestMapping(value = "back/**/pageLayout")
public class PageLayoutController {
	private final Logger LOGGER = LoggerFactory.getLogger(GuardianController.class);
    @Autowired
	private PcLabelService pcLabelService;
    @Autowired
    private PcNewHouseService pcNewHouseService;
    @Autowired
    private PcOldHouseService pcOldHouseService;
    @Autowired
    private PcRentHouseService pcRentHouseService;
    @Autowired
    private RentHouseOriginService rentHouseOriginService;
    @Autowired
    private PcAgentService pcAgentService;
    @Autowired
    private PcAgentAddService pcAgentAddService;
    @Autowired
    private AgentService agentService;
	
	@RequestMapping("pageManageMent")
	public String pageManageMent(Model model){
		//新房标签查询
		List<PcLabelPo> newHouseList=pcLabelService.getLableList();
		model.addAttribute("newHouseList", newHouseList);
		//新房楼盘查询
	    List<NewHouseLabelVo> newHouseDirectoryList=pcNewHouseService.getLableNewHouseList();
	    model.addAttribute("newHouseDirectoryList", newHouseDirectoryList);
			 
		//二手房标签查询
		List<PcLabelPo> oldHouseList=pcLabelService.getLableOldList();
		model.addAttribute("oldHouseList", oldHouseList);
			 List<NewHouseLabelVo> oldHouseDirectoryList=pcOldHouseService.getLableOldHouseList();
			 model.addAttribute("oldHouseDirectoryList", oldHouseDirectoryList);
		
		//租房
//			 List<RentHouseOriginVo> rentHouseLists=rentHouseOriginService.getRentHouseList();
//		model.addAttribute("rentHouseList", rentHouseLists);
	    //权证
	    List<WarrantOriginVo> warrantLists=pcOldHouseService.getWarrantList();		 
	    model.addAttribute("warrantLists", warrantLists);
	    //经纪人
		List<AgentVo> agentDirectoryList=agentService.getRentHouse();
		model.addAttribute("agentDirectoryList", agentDirectoryList);
		//banner
		NewHouseLabelVo banner=pcOldHouseService.getBanner();
		model.addAttribute("banner", banner);
	   
		return "pageLayout/pageManage/pageLayout";
	}
	
	/**
	 * 新房首页编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toNewHouseAdd")
	public String toNewHouseAdd(Long id,Model model){
		PcNewHousePo pcNewHouse=pcNewHouseService.getNewHouse(id);
		model.addAttribute("pcNewHouse", pcNewHouse);
		return "pageLayout/pageManage/toNewHouseAdd";
	}
	/**
	 * 经纪人首页编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toAgentAdd")
	public String toAgentAdd(Long id,Model model){
		PcAgentPo pcAgent=pcAgentService.getAgent(id);
		model.addAttribute("pcAgent", pcAgent);
		return "pageLayout/pageManage/toAgentNewHouse";
	}
	/**
	 * 修改首页提交
	 * @param pcNewHousePo
	 * @return
	 */
	@RequestMapping("updateNewHouse")
	@ResponseBody
	public AjaxResultVo updateNewHouse(PcNewHousePo pcNewHousePo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcNewHouseService.updateNewHouse(pcNewHousePo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	/**
	 * 修改经纪人提交
	 * @param pcAgentPo
	 * @return
	 */
	@RequestMapping("updateAgent")
	@ResponseBody
	public AjaxResultVo updateAgent(PcAgentPo pcAgentPo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcAgentService.updateAgent(pcAgentPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	/**
	 * 发布到前台
	 * @param id
	 * @return
	 */
	@RequestMapping("updateNewHousePage")
	@ResponseBody
	public AjaxResultVo updateNewHousePage(Long id){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			PcNewHousePo pcNewhouse=pcNewHouseService.getNewHouse(id);
			pcNewHouseService.updateNewHousePage(pcNewhouse);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 新房首页编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toOldHouseAdd")
	public String toOldHouseAdd(Long id,Model model){
		PcOldHousePo pcOldHouse=pcOldHouseService.getOldHouse(id);
		model.addAttribute("pcOldHouse", pcOldHouse);
		return "pageLayout/pageManage/toOldHouseAdd";
	}
	
	/**
	 * 修改首页提交
	 * @param pcNewHousePo
	 * @return
	 */
	@RequestMapping("updateOldHouse")
	@ResponseBody
	public AjaxResultVo updateOldHouse(PcOldHousePo pcOldHousePo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcOldHouseService.updateOldHouse(pcOldHousePo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 经纪人首页编辑(二手房)
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toAgentOldHouse")
	public String toAgentOldHouse(Long id,Model model){
		PcAgentPo pcAgent=pcAgentService.getAgentOldHouse(id);
		model.addAttribute("pcAgent", pcAgent);
		return "pageLayout/pageManage/toAgentOldHouse";
	}
	
	/**
	 * 修改经纪人提交(二手房)
	 * @param pcAgentPo
	 * @return
	 */
	@RequestMapping("updateAgentOldHouse")
	@ResponseBody
	public AjaxResultVo updateAgentOldHouse(PcAgentPo pcAgentPo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcAgentService.updateAgentOldHouse(pcAgentPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}

	/**
	 * 发布到前台(二手房)
	 * @param id
	 * @return
	 */
	@RequestMapping("updateOldHousePage")
	@ResponseBody
	public AjaxResultVo updateOldHousePage(Long id){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			PcOldHousePo pcNewhouse=pcOldHouseService.getOldHouse(id);
			pcOldHouseService.updateOldHousePage(pcNewhouse);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 新房首页编辑(租房)
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toRentHouseAdd")
	public String toRentHouseAdd(Long id,Model model){
		PcRentHousePo pcRentHousePo=pcRentHouseService.getRentHouseOne(id);
		model.addAttribute("pcRentHousePo", pcRentHousePo);
		return "pageLayout/pageManage/toRentHouseAdd";
	}
	
	/**
	 * 修改首页提交(租房)
	 * @param pcNewHousePo
	 * @return
	 */
	@RequestMapping("updateRentHouse")
	@ResponseBody
	public AjaxResultVo updateRentHouse(PcRentHousePo pcRentHousePo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcRentHouseService.updateRentHouse(pcRentHousePo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 发布到前台(二手房)
	 * @param id
	 * @return
	 */
	@RequestMapping("updateRentHousePage")
	@ResponseBody
	public AjaxResultVo updateRentHousePage(Long id){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			PcRentHousePo pcRentHousePo=pcRentHouseService.getRentHouseOne(id);
			pcRentHouseService.updateRentHousePage(pcRentHousePo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 新房首页编辑(租房)
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toAgentPcAdd")
	public String toAgentPcAdd(Long id,Model model){
		PcAgentAddPo pcAgentAddPo=pcAgentAddService.getAgentOne(id);
		model.addAttribute("pcAgentAddPo", pcAgentAddPo);
		return "pageLayout/pageManage/toAgentAdd";
	}
	
	/**
	 * 修改经纪人提交(经纪人)
	 * @param pcAgentPo
	 * @return
	 */
	@RequestMapping("updateAgentAdd")
	@ResponseBody
	public AjaxResultVo updateAgentAdd(PcAgentAddPo pcAgentAddPo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcAgentAddService.updateAgentAdd(pcAgentAddPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 发布到前台(经纪人)
	 * @param id
	 * @return
	 */
	@RequestMapping("updateAgentPage")
	@ResponseBody
	public AjaxResultVo updateAgentPage(Long id){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			PcAgentAddPo pcAgentAddPo=pcAgentAddService.getAgentOne(id);
			pcAgentAddService.updateAgentPage(pcAgentAddPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 权证首页编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toWarrantAdd")
	public String toWarrantAdd(Long id,Model model){
		WarrantOriginVo warrantOriginVo=pcOldHouseService.getWarrant(id);
		model.addAttribute("warrantOriginVo", warrantOriginVo);
		return "pageLayout/pageManage/toWarrantAdd";
	}
	
	/**
	 * 修改权证首页提交
	 * @param pcNewHousePo
	 * @return
	 */
	@RequestMapping("updateWarrant")
	@ResponseBody
	public AjaxResultVo updateWarrant(WarrantOriginVo warrantOriginVo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcOldHouseService.updateWarrant(warrantOriginVo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
//	/**
//	 * 权证标签修改
//	 * @param list
//	 * @return
//	 */
//	@RequestMapping("updateWarrantLabel")
//	@ResponseBody
//	public AjaxResultVo updateWarrantLabel(WarrantOriginVo warrantOriginVo){
//		AjaxResultVo ajax = new AjaxResultVo();
//		try {
//			pcOldHouseService.updateWarrant(warrantOriginVo);
//			ajax.setStatusCode(StatusCode.SUCCESS);
//		} catch (Exception e) {
//			ajax.setStatusCode(StatusCode.SERVER_ERROR);
//			LOGGER.error("修改首页模版异常", e);
//		}
//		return ajax;
//	}
	
	/**
	 * 发布到前台(权证)
	 * @param id
	 * @return
	 */
	@RequestMapping("updateWarrantPage")
	@ResponseBody
	public AjaxResultVo updateWarrantPage(Long id){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			WarrantOriginVo warrantOriginVo=pcOldHouseService.getWarrantVo(id);
			pcOldHouseService.updateWarrantPage(warrantOriginVo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * banner首页编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toBannerAdd")
	public String toBannerAdd(Model model){
		NewHouseLabelVo banner=pcOldHouseService.getBanner();
		model.addAttribute("banner", banner);
		return "pageLayout/pageManage/toBannerAdd";
	}
	
	/**
	 * 修改banner首页提交
	 * @param pcNewHousePo
	 * @return
	 */
	@RequestMapping("updateBanner")
	@ResponseBody
	public AjaxResultVo updateBanner(WarrantOriginVo warrantOriginVo){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			pcOldHouseService.updateBanner(warrantOriginVo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 发布到前台(权证)
	 * @param id
	 * @return
	 */
	@RequestMapping("updateBannerPage")
	@ResponseBody
	public AjaxResultVo updateBannerPage(){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			NewHouseLabelVo banner=pcOldHouseService.getBanner();
			pcOldHouseService.updateBannerPage(banner);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}
	
	/**
	 * 新房标签修改
	 * @param list
	 * @return
	 */
	@RequestMapping("updateLabel")
	@ResponseBody
	public AjaxResultVo updateLabel(@RequestBody List<PcLabelPo> list){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			for (PcLabelPo pcLabelPo : list) {
				pcLabelService.updateDynamic(pcLabelPo);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改首页模版异常", e);
		}
		return ajax;
	}

}
