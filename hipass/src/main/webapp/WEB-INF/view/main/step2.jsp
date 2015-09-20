<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<%
	int receipts = Integer.parseInt((String)request.getAttribute("receipts"));
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/basic.css" />
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/plugin/jquery.blockUI.js"></script> 
<script type="text/javascript" src="/js/common.js"></script>
<script type=text/javascript>
function f_saveInfo() {
	
	var serviceNames = document.getElementsByName('serviceName');
	var regionNames = document.getElementsByName('regionName');
	var tgTels = document.getElementsByName('tgTel');
	var tgAccounts = document.getElementsByName('tgAccount');
	var payDates = document.getElementsByName('payDate');
	var tgNames = document.getElementsByName('tgName');
	var billAmounts = document.getElementsByName('billAmount');
	
	var tgType1s = document.getElementsByName('tgType1');
	var tgType2s = document.getElementsByName('tgType2');
	var tgType3s = document.getElementsByName('tgType3');
	var tgType4s = document.getElementsByName('tgType4');
	var tgType5s = document.getElementsByName('tgType5');
	var billAmount1s = document.getElementsByName('billAmount1');
	var billAmount2s = document.getElementsByName('billAmount2');
	var billAmount3s = document.getElementsByName('billAmount3');
	var billAmount4s = document.getElementsByName('billAmount4');
	var billAmount5s = document.getElementsByName('billAmount5');
	var totalAmounts = document.getElementsByName('totalAmount');
	var taxAmounts = document.getElementsByName('taxAmount');
	var tgSerials = document.getElementsByName('tgSerial');
	
	
	for(var i=0; i<<%=receipts%>; i++){
		var serviceName =serviceNames[i];
		var regionName =regionNames[i];
		var tgTel =tgTels[i];
		var tgAccount =tgAccounts[i];
		var payDate =payDates[i];
		var tgName =tgNames[i];
		var billAmount =billAmounts[i];
		var tgType1 =tgType1s[i];
		var tgType2 =tgType2s[i];
		var tgType3 =tgType3s[i];
		var tgType4 =tgType4s[i];
		var tgType5 =tgType5s[i];
		var billAmount1 =billAmount1s[i];
		var billAmount2 =billAmount2s[i];
		var billAmount3 =billAmount3s[i];
		var billAmount4 =billAmount4s[i];
		var billAmount5 =billAmount5s[i];
		var totalAmount =totalAmounts[i];
		var taxAmount =taxAmounts[i];
		var tgSerial =tgSerials[i];
		
		var count = i+1;
		if (serviceName.value!=""){
			if(regionName.value=="") {alert(count+"번째행, 영업소를 입력하세요."); return;};
			if(tgTel.value=="") {alert(count+"번째행, 전화번호를 입력하세요."); return;};
			if(tgAccount.value=="") {alert(count+"번째행, 사업자번호를 입력하세요."); return;};
			if(payDate.value=="") {alert(count+"번째행, 날짜를 입력하세요."); return;};
			if(billAmount.value=="") {alert(count+"번째행, 요금을 입력하세요."); return;};
			if(!cfCheckOnlyNumber(billAmount.value)) {alert(count+"번째행, 요금은 숫자만 입력하세요."); return;};
			
			if(tgType1.value=="") {alert(count+"번째행, 기본톨게이트를 입력하세요."); return;};
			if(billAmount1.value=="") {alert(count+"번째행, 기본톨게이트 요금을 입력하세요."); return;};
			if(!cfCheckOnlyNumber(billAmount1.value)) {alert(count+"번째행, 기본톨게이트 요금은 숫자만 입력하세요."); return;};
			
			if(tgType2.value!="" || billAmount2.value!=""){
				if(billAmount2.value=="") {alert(count+"번째행, 추가톨게이트 요금1을 입력하세요."); return;};
				if(!cfCheckOnlyNumber(billAmount2.value)) {alert(count+"번째행, 추가톨게이트 요금1은 숫자만 입력하세요."); return;};
			}
			if(tgType3.value!="" || billAmount3.value!=""){
				if(billAmount3.value=="") {alert(count+"번째행, 추가톨게이트 요금2을 입력하세요."); return;};
				if(!cfCheckOnlyNumber(billAmount3.value)) {alert(count+"번째행, 추가톨게이트 요금2은 숫자만 입력하세요."); return;};
			}
			if(tgType4.value!="" || billAmount4.value!=""){
				if(billAmount4.value=="") {alert(count+"번째행, 추가톨게이트 요금3을 입력하세요."); return;};
				if(!cfCheckOnlyNumber(billAmount4.value)) {alert(count+"번째행, 추가톨게이트 요금3은 숫자만 입력하세요."); return;};
			}
			if(tgType5.value!="" || billAmount5.value!=""){
				if(billAmount5.value=="") {alert(count+"번째행, 추가톨게이트 요금4을 입력하세요."); return;};
				if(!cfCheckOnlyNumber(billAmount5.value)) {alert(count+"번째행, 추가톨게이트 요금4은 숫자만 입력하세요."); return;};
			}
			
			if(totalAmount.value=="") {alert(count+"번째행, 공급가액을 입력하세요."); return;};
			if(!cfCheckOnlyNumber(totalAmount.value)) {alert(count+"번째행, 공급가액은 숫자만 입력하세요."); return;};
			
			if(taxAmount.value=="") {alert(count+"번째행, 부가세를 입력하세요."); return;};
			if(!cfCheckOnlyNumber(taxAmount.value)) {alert(count+"번째행, 부가세는 숫자만 입력하세요."); return;};
			
			if(tgSerial.value=="") {alert(count+"번째행, 일련번호를 입력하세요."); return;};
		}
	}
	
	billInfoList = "";
	billInfo = "";
	for(var i=0; i<<%=receipts%>; i++){
		var serviceName =serviceNames[i];
		var regionName =regionNames[i];
		var tgTel =tgTels[i];
		var tgAccount =tgAccounts[i];
		var payDate =payDates[i];
		var tgName =tgNames[i];
		var billAmount =billAmounts[i];
		var tgType1 =tgType1s[i];
		var tgType2 =tgType2s[i];
		var tgType3 =tgType3s[i];
		var tgType4 =tgType4s[i];
		var tgType5 =tgType5s[i];
		var billAmount1 =billAmount1s[i];
		var billAmount2 =billAmount2s[i];
		var billAmount3 =billAmount3s[i];
		var billAmount4 =billAmount4s[i];
		var billAmount5 =billAmount5s[i];
		var totalAmount =totalAmounts[i];
		var taxAmount =taxAmounts[i];
		var tgSerial =tgSerials[i];
		
		if (regionName.value != ""){
			billInfo = serviceName.value+"|"+regionName.value+"|"+tgTel.value+"|"+tgAccount.value+"|"+payDate.value+"|"+tgName.value+"|"+billAmount.value+"|";
			billInfo += tgType1.value+"|"+billAmount1.value+"|";
			billInfo += totalAmount.value+"|"+taxAmount.value+"|"+tgSerial.value+"|";
			billInfo += tgType2.value+"|"+tgType3.value+"|"+tgType4.value+"|"+tgType5.value+"|";
			billInfo += billAmount2.value+"|"+billAmount3.value+"|"+billAmount4.value+"|"+billAmount5.value+"|#";
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
		url: '/main/step2Ajax.json'
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
				alert("요청 중 서버에서 에러가 발생하였습니다.(errorCode:["+json.resultCode+"],"+json.resultMsg+")");
		}
		, complete : function() {
			cfHideBlock();
		}
	});
}
</script>
</head>
<body>
<table border="0" width="1200">
	<tbody>
		<tr>
			<td align="center" height="50"><b>영수증 정보</b></td>
		</tr>	
	</tbody>
</table>
<table border="1" width="1450">
	<colgroup>
		<col width="4%" />
		<col width="9%" />
		<col width="6%" />
		<col width="6%" />
		<col width="*" />
		<col width="6%" />
		<col width="6%" />
		<col width="7%" />
		<col width="7%" />
		<col width="7%" />
		<col width="7%" />
		<col width="7%" />
		<col width="6%" />
		<col width="10%" />
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th><font color="RED">관할사업소/<br/>영업소</font></th>
			<th><font color="RED">전화번호</font></th>
			<th><font color="RED">사업자번호</font></th>
			<th><font color="RED">날짜</font></th>
			<th>입구영업소</th>
			<th><font color="RED">요금</font></th>
			<th><font color="RED">기본톨게이트/<br/>요금</font></th>
			<th>추가톨게이트1/<br/>요금1</th>
			<th>추가톨게이트2/<br/>요금2</th>
			<th>추가톨게이트3/<br/>요금3</th>
			<th>추가톨게이트4/<br/>요금4</th>
			<th><font color="RED">공급가액/<br/>부가세</font></th>
			<th><font color="RED">일련번호</font></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align='center'>예시</td>
			<td align='center'><input type='text' name='' size='20' value='한국도로공사' disabled><br/>
				<input type='text' name='' size='18' value='추부영업소' disabled></td>
			<td align='center'><input type='text' name='' size='15' value='041)752-8732' disabled></td>
			<td align='center'><input type='text' name='' size='15' value='129-82-00103' disabled></td>
			<td align='center'><input type='text' name='' size='30' value='2015년09월01일 21시42분' disabled></td>
			<td align='center'><input type='text' name='' size='15' value='서대전' disabled></td>
			<td align='center'><input type='text' name='' size='10' value='1900' disabled></td>
			<td align='center'><input type='text' name='' size='11' value='KEC' disabled><br/>
				<input type='text' name='' size='10' value='1900' disabled></td>
			<td align='center'><input type='text' name='' size='11' value='NCE' disabled><br/>
				<input type='text' name='' size='10' value='100' disabled></td>
			<td align='center'><input type='text' name='' size='11' value='' disabled><br/>
				<input type='text' name='' size='10' value='' disabled></td>
			<td align='center'><input type='text' name='' size='11' value='' disabled><br/>
				<input type='text' name='' size='10' value='' disabled></td>
			<td align='center'><input type='text' name='' size='11' value='' disabled><br/>
				<input type='text' name='' size='10' value='' disabled></td>
			<td align='center'><input type='text' name='' size='11' value='1900' disabled><br/>
				<input type='text' name='' size='10' value='0' disabled></td>
			<td align='center'><input type='text' name='' size='20' value='H071-7101-02253' disabled></td>
		</tr>
		<!--<tr>
			<td align='center'>0</td>
			<td align='center'><input type='text' name='serviceName' size='20' value='한국도로공사'><br/>
				<input type='text' name='regionName' size='18' value='추부영업소'></td>
			<td align='center'><input type='text' name='tgTel' size='15' value='041)752-8732'></td>
			<td align='center'><input type='text' name='tgAccount' size='15' value='129-82-00103'></td>
			<td align='center'><input type='text' name='payDate' size='30' value='2015년09월01일 21시42분'></td>
			<td align='center'><input type='text' name='tgName' size='15' value='서대전'></td>
			<td align='center'><input type='text' name='billAmount' size='10' value='1900'></td>
			<td align='center'><input type='text' name='tgType1' size='11' value='KEC'><br/>
				<input type='text' name='billAmount1' size='10' value='1900'></td>
			<td align='center'><input type='text' name='tgType2' size='11' value='NCE'><br/>
				<input type='text' name='billAmount2' size='10' value='100'></td>
			<td align='center'><input type='text' name='tgType3' size='11' value=''><br/>
				<input type='text' name='billAmount3' size='10' value=''></td>
			<td align='center'><input type='text' name='tgType4' size='11' value=''><br/>
				<input type='text' name='billAmount4' size='10' value=''></td>
			<td align='center'><input type='text' name='tgType5' size='11' value=''><br/>
				<input type='text' name='billAmount5' size='10' value=''></td>
			<td align='center'><input type='text' name='totalAmount' size='11' value='1900'><br/>
				<input type='text' name='taxAmount' size='10' value='0'></td>
			<td align='center'><input type='text' name='tgSerial' size='20' value='H071-7101-02253'></td>
		</tr>-->
	<%
		for (int i=1; i<=receipts; i++) {
	%>
		<tr>
			<td align='center'><%=i %></td>
			<td align='center'><input type='text' name='serviceName' size='20' value=''><br/>
				<input type='text' name='regionName' size='18' value=''></td>
			<td align='center'><input type='text' name='tgTel' size='15' value=''></td>
			<td align='center'><input type='text' name='tgAccount' size='15' value=''></td>
			<td align='center'><input type='text' name='payDate' size='30' value=''></td>
			<td align='center'><input type='text' name='tgName' size='15' value=''></td>
			<td align='center'><input type='text' name='billAmount' size='10' value=''></td>
			<td align='center'><input type='text' name='tgType1' size='11' value=''><br/>
				<input type='text' name='billAmount1' size='10' value=''></td>
			<td align='center'><input type='text' name='tgType2' size='11' value=''><br/>
				<input type='text' name='billAmount2' size='10' value=''></td>
			<td align='center'><input type='text' name='tgType3' size='11' value=''><br/>
				<input type='text' name='billAmount3' size='10' value=''></td>
			<td align='center'><input type='text' name='tgType4' size='11' value=''><br/>
				<input type='text' name='billAmount4' size='10' value=''></td>
			<td align='center'><input type='text' name='tgType5' size='11' value=''><br/>
				<input type='text' name='billAmount5' size='10' value=''></td>
			<td align='center'><input type='text' name='totalAmount' size='11' value=''><br/>
				<input type='text' name='taxAmount' size='10' value='0'></td>
			<td align='center'><input type='text' name='tgSerial' size='20' value=''></td>
		</tr>
	<%} %>
	</tbody>
</table>
<table border="0" width="1200">
	<tbody>
		<tr>
			<td align="center" height="50"><input type="button" value="영수증 출력화면으로" id="saveInfo" onclick="javascript:f_saveInfo()"></td>
		</tr>	
	</tbody>
</table>
</body>
</html>
