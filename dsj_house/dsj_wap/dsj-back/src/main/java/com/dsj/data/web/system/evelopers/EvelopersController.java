package com.dsj.data.web.system.evelopers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.EvelopersService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 * 开发商管理
 */
@Controller
@RequestMapping(value = "back/**/system/evelopers")
public class EvelopersController {

	private final Logger LOGGER = LoggerFactory.getLogger(EvelopersController.class);

	@Autowired
	private EvelopersService evelopersService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private UserService userService;

	@RequestMapping({"evelopers_list",""})
	public String toEvelopersList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "system/evelopers/evelopers_list";
	}
	
	@RequestMapping("evelopers_auth_list")
	public String toEvelopersAuthList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "system/evelopers/evelopers_auth_list";
	}
	
	@RequestMapping("evelopers_wait")
	public String evelopers_wait(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "system/evelopers/evelopers_waitsubmit_list";
	}
	

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> adminList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = evelopersService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("开发商账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("evelopers_add")
	public String toEvelopersAdd(Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		model.addAttribute("firstAreaList", firstAreaList);
		return "system/evelopers/evelopers_add";
	}

	@RequestMapping("save_evelopers_add")
	@ResponseBody
	public AjaxResultVo saveEvelopersAdd(HttpServletRequest request, EvelopersVo vo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("username", vo.getUsername());
			hashMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
			UserPo userPo1 = userService.getBy(hashMap);
			if(null!=userPo1){
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
				ajax.setMessage("此账号已被占用");
			}else{
				hashMap.clear();
				hashMap.put("phone", vo.getOperationPhone());
				hashMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
				UserPo userPo = userService.getBy(hashMap);
				if(null!=userPo){
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("此手机号已被占用");
				}else{
					vo.setCreatePerson(ShiroUtils.getSessionUser().getId().intValue());
					vo.setPassword(CommConst.INIT_PWD);
					vo.setStatus(UserStatusEnum.NO_AUDIT.getValue());
					evelopersService.saveEvelopersAdd(vo);
					ajax.setStatusCode(StatusCode.SUCCESS);
				}
			}
			
			
			
		} catch (Exception e) {
			LOGGER.error("添加开发商账号异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("evelopers_update")
	public String toEvelopersUpdate(Model model, Long id) {
		EvelopersVo evelopersVo = evelopersService.getVoById(id);
		model.addAttribute("evelopersVo", evelopersVo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		List<AreaPo> twoAreaList = null;
		List<AreaPo> threeAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
			map.put("parentId", evelopersVo.getAreaOneId());
			twoAreaList = areaService.listBy(map);
			map.put("parentId", evelopersVo.getAreaTwoId());
			threeAreaList = areaService.listBy(map);
			model.addAttribute("threeAreaList", threeAreaList);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		model.addAttribute("firstAreaList", firstAreaList);
		model.addAttribute("twoAreaList", twoAreaList);
		model.addAttribute("threeAreaList", threeAreaList);
		String[] loupanNameList = evelopersVo.getLoupanName().split(",");
		model.addAttribute("loupanNameList", loupanNameList);
		return "system/evelopers/evelopers_update";
	}
	
	@RequestMapping("evelopers_auth")
	public String toEvelopersAuth(Model model, Long id) {
		EvelopersVo evelopersVo = evelopersService.getVoById(id);
		model.addAttribute("evelopersVo", evelopersVo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		List<AreaPo> twoAreaList = null;
		List<AreaPo> threeAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
			map.put("parentId", evelopersVo.getAreaOneId());
			twoAreaList = areaService.listBy(map);
			map.put("parentId", evelopersVo.getAreaTwoId());
			threeAreaList = areaService.listBy(map);
			model.addAttribute("threeAreaList", threeAreaList);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		model.addAttribute("firstAreaList", firstAreaList);
		model.addAttribute("twoAreaList", twoAreaList);
		model.addAttribute("threeAreaList", threeAreaList);
		return "system/evelopers/evelopers_auth";
	}

	@RequestMapping("save_evelopers_update")
	@ResponseBody
	public AjaxResultVo saveEvelopersUpdate(HttpServletRequest request, EvelopersVo vo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("username", vo.getUsername());
			hashMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
			hashMap.put("id", vo.getUserId());
			UserPo userPo1 = userService.getBy(hashMap);
			if(null!=userPo1){
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
				ajax.setMessage("此账号已被占用");
			}else{
				hashMap.clear();
				hashMap.put("phone", vo.getOperationPhone());
				hashMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
				hashMap.put("id", vo.getUserId());
				UserPo userPo = userService.getBy(hashMap);
				if(null!=userPo){
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("此手机号已被占用");
				}else{
					vo.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
					evelopersService.saveEvelopersUpdate(vo);
					ajax.setStatusCode(StatusCode.SUCCESS);
				}
			}
		} catch (Exception e) {
			LOGGER.error("修改开发商账号异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("evelopers_audit_yes")
	@ResponseBody
	public AjaxResultVo evelopersAuditYes(String ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersAudit(ids, UserStatusEnum.YES.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("审核异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("evelopers_audit_no")
	@ResponseBody
	public AjaxResultVo evelopersAudiNo(String ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersAudit(ids, UserStatusEnum.NO.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("审核异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("evelopers_del")
	@ResponseBody
	public AjaxResultVo delEvelopers(String ids) {

		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersDeleteFlag(ids, DeleteStatusEnum.DEL.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("开发商账号删除异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("evelopers_reset")
	@ResponseBody
	public AjaxResultVo evelopersReset(Long id) {

		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersReset(id);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("密码重置异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	/*@RequestMapping("export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String fullFileName = request.getSession().getServletContext().getRealPath("/") + "\\WEB-INF\\classes\\docx\\";
		String fullFileName = "\\root\\data\\excel";
		DownloadOtherUtil.downLoadFile(ConfigUtils.instance.getChengNuoBook(), "docx", fullFileName, response);
	}*/
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
