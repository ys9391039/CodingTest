<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:eval expression="@configProp" var="configProp" scope="request"/>
<%@ page import="com.sample.common.Constants" %>
<%
	String errorYn = (String)request.getAttribute("errorYn");
	String userName = (String)request.getAttribute("userName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>SampleProject</title>
</head>
<body>
main.jsp<br/>
errorYn : <%= errorYn%><br/>
userName : <%= userName%><br/>
fileUploadTempDir : ${configProp['system.fileUploadTempDir']}<br/>
<img src="/images/logo.png"/>
</body>
</html>