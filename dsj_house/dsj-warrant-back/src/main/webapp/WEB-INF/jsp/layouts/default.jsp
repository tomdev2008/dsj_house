 <%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<head>
	<%@ include file="csslib.jsp" %>
	<%@ include file="scriptlib.jsp"%>
	<title>大搜家管理后台</title>
</head>

<body class="desktop-detected">
		<%@ include file="dialog.jsp"%>
		
		<%@ include file="left.jsp"%>
		<!-- #MAIN PANEL -->
		<div id="main" role="main">
			<%@ include file="header.jsp"%>
           <sitemesh:body/>
		</div>
		<%@ include file="foot.jsp"%>
</body>
</html>