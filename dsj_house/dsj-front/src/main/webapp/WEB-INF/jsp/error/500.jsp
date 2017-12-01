<%@ page language="Java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
  <%  //此处输出异常信息  
      exception.printStackTrace();  
  
      ByteArrayOutputStream ostr = new ByteArrayOutputStream();  
      exception.printStackTrace(new PrintStream(ostr));  
      out.print(ostr);  
  %>  
</body>
</html>