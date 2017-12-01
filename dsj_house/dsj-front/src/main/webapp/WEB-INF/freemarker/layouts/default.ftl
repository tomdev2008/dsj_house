<!DOCTYPE html>
<#include "/WEB-INF/freemarker/common/taglibs.ftl">
<html  lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <meta charset="UTF-8">
	<title>${title}</title>
	<link rel="icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon" />
	<script src="${ctx }/static/front/js/fles.js"></script>
	<#include "css.ftl">
	<#include "script.ftl">
	${head}
</head>
<body >
  	<div id="dsj">
	<#include "header.ftl">
    ${body}
	<#include "footer.ftl">
	<#include "im.ftl">
	</div>
</body>
</html>