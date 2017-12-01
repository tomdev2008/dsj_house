 <%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<head>
	<%@ include file="csslib.jsp" %>
	<title>权证管理后台</title>
</head>

<body >
		<%@ include file="dialog.jsp"%>
		<%@ include file="header.jsp"%>
		<%@ include file="left.jsp"%>
		<div id="main" role="main">
           <sitemesh:body/>
		</div>
		<%@ include file="foot.jsp"%>
</body>
</html>