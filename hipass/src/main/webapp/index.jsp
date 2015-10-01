<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/basic.css" />
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/plugin/jquery.blockUI.js"></script> 
<script type="text/javascript" src="/js/common.js"></script>
<script type=text/javascript>
function f_saveInfo() {
	var userId = $('#userId').val();
	var userPassword = $('#userPassword').val();
	
	if (userId == "") {alert("아이디를 입력하세요."); return}
	if (userPassword == "") {alert("패스워드를 입력하세요."); return}
	
	var params = {
			userId : userId
			,userPassword : userPassword
	}
	
	var request = $.ajax({
		url: '/main/login.json'
		, type : 'POST'
		, timeout: 30000
		, data : params
		, dataType : 'json'
		, beforeSend: function(xmlHttpRequest) {
			cfShowBlock(true);
		}
		, error: function(xhr, textStatus, errorThrown) {
			alert("일시적인 오류가 발생했습니다.");
		}
		, success : function(json) {
			if (json.resultCode == 0) 
				document.location.href="/main/step1.do";
			else{
				alert("요청 중 서버에서 에러가 발생하였습니다.(errorCode:["+json.resultCode+"],"+json.resultMsg+")");
				return;
			}
		}
		, complete : function() {
			cfHideBlock();
		}
	});
}

$(document).ready( function() {
	//alert(location.protocol);
	if (location.protocol == "http:")
		location.href = "https://"+location.host;

	$('#userPassword').keydown(function(e) {
	    if (e.keyCode == 13) {
	    	f_saveInfo();
	    }
	});	
});

</script>
</head>
<body>
<table border="0" width="500">
	<tbody>
		<tr>
			<td align="center" height="50"><b>Have a nice day!!</b></td>
		</tr>	
	</tbody>
</table>

<table border="1" width="500">
	<colgroup>
		<col width="30%" />
		<col width="70%" />
	</colgroup>
	<tbody>
		<tr>
			<td align="center">ID</td>
			<td align="left">&nbsp;<input type="text" name="userId" id="userId" size="20"></td>
		</tr>
		<tr>
			<td align="center">Password</td>
			<td align="left">&nbsp;<input type="password" name="userPassword" id="userPassword" size="20"></td>
		</tr>
	</tbody>
</table>
<table border="0" width="500">
	<tbody>
		<tr>
			<td align="center" height="50"><input type="button" value="로그인" id="saveInfo" onclick="javascript:f_saveInfo()"></td>
		</tr>	
	</tbody>
</table>
</body>
</html>
