<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/common.css" />
<link rel="stylesheet" type="text/css" href="/css/main.css" />
<link rel="stylesheet" type="text/css" href="/css/order.css" />
<link rel="stylesheet" type="text/css" href="/css/associates.css" />
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/plugin/jquery.blockUI.js"></script> 
<script type="text/javascript" src="/js/common.js"></script>
<script type=text/javascript>
function f_saveInfo() {
	
	var regionNames = document.getElementsByName('regionName');
	var tgTels = document.getElementsByName('tgTel');
	var tgAccounts = document.getElementsByName('tgAccount');
	var payDates = document.getElementsByName('payDate');
	var tgNames = document.getElementsByName('tgName');
	var billAmounts = document.getElementsByName('billAmount');
	
	billInfoList = "";
	billInfo = "";
	for(var i=0;i<20;i++){
		var regionName =regionNames[i];
		var tgTel =tgTels[i];
		var tgAccount =tgAccounts[i];
		var payDate =payDates[i];
		var tgName =tgNames[i];
		var billAmount =billAmounts[i];
		if (regionName.value != ""){
			billInfo = regionName.value+"|"+tgTel.value+"|"+tgAccount.value+"|"+payDate.value+"|"+tgName.value+"|"+billAmount.value;
			//alert(billInfo);
			if (i==0)
				billInfoList = billInfo;
			else
				billInfoList = billInfoList + "@" + billInfo;
		}
	}
	
	//alert(billInfoList);
	
	var params = {
		//billInfo : "추부영업소|041)752-8732|129-82-00103|2015년09월01일 21시42분|서대전|1|1900|현대카드|0190-0200-3890-8992|H071-7101-02253@추부영업소|041)752-8732|129-82-00103|2015년09월01일 21시42분|서대전|1|1900|현대카드|0190-0200-3890-8992|H071-7101-02253"
		billInfo : billInfoList
	}	
	//return false;
	
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
<table border="1" width="1200">
	<colgroup>
		<col width="5%" />
		<col width="15%" />
		<col width="15%" />
		<col width="15%" />
		<col width="*" />
		<col width="15%" />
		<col width="15%" />
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>영업소</th>
			<th>전화번호</th>
			<th>사업자번호</th>
			<th>날짜</th>
			<th>톨게이트</th>
			<th>요금</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">1</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">2</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">3</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">4</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">5</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">6</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">7</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">8</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">9</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">10</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">11</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">12</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">13</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">14</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">15</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">16</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">17</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">18</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">19</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
		<tr>
			<td align="center">20</td>
			<td align="center"><input type="text" name="regionName" size="25"></td>
			<td align="center"><input type="text" name="tgTel" size="25"></td>
			<td align="center"><input type="text" name="tgAccount" size="25"></td>
			<td align="center"><input type="text" name="payDate" size="35"></td>
			<td align="center"><input type="text" name="tgName" size="25"></td>
			<td align="center"><input type="text" name="billAmount" size="25"></td>
		</tr>
	</tbody>
</table>
<br/>
<center>
<input type="button" value="저장하기" id="saveInfo" onclick="javascript:f_saveInfo()">
</center>
</body>
</html>
