<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	String errorYn = (String)request.getAttribute("errorYn");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>CozSample_3.1</title>
</head>
<body>
login.jsp<br/>
errorYn : <%= errorYn%><br/>
<img src="/images/logo.png"/>
</body>
</html>