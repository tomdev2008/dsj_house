
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.dsj.data.web.utils.Uploader"%>

<%
	request.setCharacterEncoding(Uploader.ENCODEING);
	response.setCharacterEncoding(Uploader.ENCODEING);
	Uploader up = new Uploader(request);
	String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	up.setAllowFiles(fileType);
	up.setMaxSize(500 * 1024); //单位KB
	up.upload();
	response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'大搜家','state':'"+up.getState()+"'}");
 %>
