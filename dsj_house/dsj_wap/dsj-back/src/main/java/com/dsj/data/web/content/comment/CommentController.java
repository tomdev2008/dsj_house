package com.dsj.data.web.content.comment;



import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.comment.vo.CommentVo;



@Controller
@RequestMapping(value = "back/**/comment")
public class CommentController {
	private final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	@RequestMapping("commentList")
	@ResponseBody
	public ModelAndView commentList(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("content/comment/comment_list");
		return mav;
	}
	
	@RequestMapping("dataList")
	@ResponseBody
	public PageDateTable<?> dataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = commentService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("评论查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
    /**
     * 评论详情
     * @param id
     * @return
     */
	@RequestMapping("detail")
	@ResponseBody
	public ModelAndView detail(long id){
		ModelAndView mav = new ModelAndView();
		try {
			CommentVo comment = commentService.getVoById(id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        comment.setTimeString(sdf.format(comment.getCreateTime()));
	         
			mav.addObject("comment", comment);
			
			mav.setViewName("content/comment/comment_detail");
		} catch (Exception e) {
			LOGGER.error("查询评论异常", e);
		}
		
		return mav;
	}
	
	/**
	 * 删除评论
	 * @param ids
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResultVo delete(String ids){
		AjaxResultVo result = new AjaxResultVo();
		try {
			commentService.deleteMany(ids);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("删除评论异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	

}
