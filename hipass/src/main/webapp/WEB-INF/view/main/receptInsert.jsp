<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/plugin/jquery.blockUI.js"></script> 
<script type="text/javascript" src="/js/common.js"></script>
<script type=text/javascript>
function f_saveInfo() {
	var params = {
		billInfo : "추부영업소|041)752-8732|129-82-00103|2015년09월01일 21시42분|서대전|1|1900|현대카드|0190-0200-3890-8992|H071-7101-02253@추부영업소|041)752-8732|129-82-00103|2015년09월01일 21시42분|서대전|1|1900|현대카드|0190-0200-3890-8992|H071-7101-02253"
	}
	
	var request = $.ajax({
		url: '/main/mainAjax.json'
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
				document.location.href="/usepculr/UsePculrReceiptPrint.do"
			else
				alert("요청 중 서버에서 에러가 발생하였습니다.");
		}
		, complete : function() {
			cfHideBlock();
		}
	});
}

</script>
</head>
<body>
<h2>영수증 정보</h2>
<br/>
<br/>
<br/>
<input type="button" value="저장" id="saveInfo" onclick="javascript:f_saveInfo()">
</body>
</html>
