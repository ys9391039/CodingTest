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
	
	var carType = $('#carType').val();
	var cardName = $('#cardName').val();
	var cardNumber = $('#cardNumber').val();
	var receipts = $('#receipts').val();
	
	if (cardName == "") {alert("신용카드 이름을 입력하세요."); return}
	if (cardNumber == "") {alert("신용카드 번호를 입력하세요."); return}
	//alert(cardName);
	//return false;
	
	var params = {
			carType : carType
			,cardName : cardName
			,cardNumber : cardNumber
	}	
	
	var request = $.ajax({
		url: '/main/step1Ajax.json'
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
				document.location.href="/main/step2.do?receipts="+receipts;
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

</script>
</head>
<body>
<table border="0" width="600">
	<tbody>
		<tr>
			<td align="center" height="50"><b>기본정보</b></td>
		</tr>	
	</tbody>
</table>
<br/>
<table border="1" width="600">
	<colgroup>
		<col width="30%" />
		<col width="70%" />
	</colgroup>
	<thead>
		<tr>
			<th>항목</th>
			<th>내용</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">차종</td>
			<td align="left">&nbsp;<select name="carType" id="carType">
										    <option value="1">1</option>
										    <option value="2">2</option>
										    <option value="3">3</option>
										    <option value="4">4</option>
										    <option value="5">5</option>
										    <option value="6">6</option>
										</select>종</td>
		</tr>
		<tr>
			<td align="center">신용카드 이름</td>
			<td align="left">&nbsp;<input type="text" name="cardName" id="cardName" size="20"> 예)삼성카드</td>
		</tr>
		<tr>
			<td align="center">신용카드 번호</td>
			<td align="left">&nbsp;<input type="text" name="cardNumber" id="cardNumber" size="25"> 예)1234-5678-2233-4455</td>
		</tr>
		<tr>
			<td align="center">영수증 개수</td>
			<td align="left">&nbsp;<select name="receipts" id="receipts">
										    <option value="10">10</option>
										    <option value="20" selected>20</option>
										    <option value="30">30</option>
										    <option value="50">50</option>
										</select>개</td>
		</tr>
	</tbody>
</table>
<br/>
<table border="0" width="600">
	<tbody>
		<tr>
			<td align="center" height="50"><input type="button" value="다음 단계로" id="saveInfo" onclick="javascript:f_saveInfo()"></td>
		</tr>	
	</tbody>
</table>
</body>
</html>
