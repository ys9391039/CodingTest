<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:eval expression="@configProp" var="configProp" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>통행료 지불 excard 서비스 - 영수증</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
<link rel="stylesheet" type="text/css" href="/css/cupertino/jquery-ui-1.8.13.custom.css"/>
<link rel="shortcut icon" type="image/x-icon" href="/img/comm/icon_hipass.ico"/>
<script type="text/javascript" src="/js/hp/comm/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="/js/hp/comm/json2.js"></script>
<script type="text/javascript" src="/js/hp/comm/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript" src="/js/hp/comm/Utillity.js"></script>
<script type="text/javascript" src="/RexServer30/rexscript/rexpert.js"></script>
<script type="text/javascript" src="/RexServer30/rexscript/rexpert_properties.js"></script>
<script type="text/javascript" src="https://ck.softforum.co.kr/CKKeyPro/research_ex/CKKeyPro.js"></script>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/plugin/jquery.blockUI.js"></script> 
<script type="text/javascript" src="/js/common.js?${configProp['system.appVersion']}"></script>
<script type="text/javascript">
//<![CDATA[
	var contextpath = "";

	//인쇄
	function printDiv(){
       	window.print(); 
	} 

	//보고서 인쇄
	function fn_print_receipt(){

		//레포트 생성 객체
		var oReport = GetfnParamSet("영수증");

		//레포트 파일명 설정
		oReport.rptname = "hp/UsePculrTabSearchForReceipt";

		//레포트 파라메터 설정
		if('tV4rILt8IncwJZU3sW1kwygUiQKoJXobyQRPXr27qH9lkpciE42j1VnYs9ew2lcvRNR36j32M0IEl0hF+wcQ5Q=='.length > 0){
			oReport.param("user_key").value = "tV4rILt8IncwJZU3sW1kwygUiQKoJXobyQRPXr27qH9lkpciE42j1VnYs9ew2lcvRNR36j32M0IEl0hF+wcQ5Q==";
		}else{
			oReport.param("user_key").value = "";
		}

		oReport.param("user_type").value = "1";
		oReport.param("sDate").value = "20150616";
		oReport.param("eDate").value = "20150915";
		oReport.param("ecd_no").value = "";
		oReport.param("hp_card_kind").value = "";
		oReport.param("card_com").value = "";
		oReport.param("date_type").value = "work";
		oReport.param("order_item").value = "date";
		oReport.param("order_type").value = "desc";
		oReport.param("in_ic_code").value = "";
		oReport.param("out_ic_code").value = "";
		oReport.param("row_nums").value = "";
		
		//레포트 출력 완료 후
		oReport.event.finishdocument = fnReportEvent;
		oReport.event.finishprint = fnReportEvent;
		
		//레포트 실행
		oReport.open();
	}
	
	//영수증 출력시 엑셀, 한글 파일 export 막음
	function fnReportEvent(oRexCtl, sEvent, oArgs) {
		if (sEvent == "finishdocument") {
			oRexCtl.SetCSS("appearance.toolbar.button.exportxls.visible=0");
			oRexCtl.SetCSS("appearance.toolbar.button.exporthwp.visible=0");
			oRexCtl.UpdateCSS();
		}else if (sEvent="finishprint"){
			var parameters = $("#hpForm").serialize();
			$.ajax({
				url : 'https://www.excard.co.kr/usepculr/UsePculrReceiptPrintIss.do',
				type : 'post',
				async : true,
				data: parameters,
				dataType: 'json',
				success: function(data){
				},
				error : function(msg){
				}
			}); 
		}
	}
//]]>

	$(document).ready( function() {
		f_getList();
	});
	
	function f_getList() {
		var params = {
		}
		
		var request = $.ajax({
			url: '/usepculr/UsePculrReceiptPrintAjax.json'
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
					makeResult(json);
				else
					alert("요청 중 서버에서 에러가 발생하였습니다.(errorCode:["+json.resultCode+"],"+json.resultMsg+")");
			}
			, complete : function() {
				cfHideBlock();
			}
		});
	}
	
	function makeResult(json){
		html = '';
		var rowCount = 0;
		$.each(json.billInfoList, function(i, billInfo){
			html +='<div class=bill>';
			html +='<table border=0 cellSpacing=0 cellPadding=0 width=200><tbody>';
			html +='<tr><td style=\"font-size: 16px\" height=20 vAlign=center align=middle><strong>하이패스는</strong> </td></tr>';
			html +='<tr><td style=\"font-size: 16px\" height=20 vAlign=center align=middle><strong>빠르고 편리합니다</strong> </td></tr>';
			html +='<tr><td height=10></td></tr>';
			html +='<tr><td style=\"font-size: 20px\" height=30 vAlign=center align=middle><strong>영수증</strong> </td></tr>';
			html +='<tr><td><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody>';
			html +='<tr><td style=\"font-size: 12px\" height=18 width=80 align=middle><strong>'+billInfo.serviceName+'</strong> </td>';
			html +='<td style=\"font-size: 12px\" align=middle><strong>'+billInfo.regionName+'</strong> </td></tr></tbody></table></td></tr>';
			html +='<tr><td style=\"font-size: 12px\" height=16 align=middle>TEL : '+billInfo.tgTel+' </td></tr>';
			html +='<tr><td style=\"font-size: 12px\" height=16 align=middle>사업자번호 : '+billInfo.tgAccount+' </td></tr>';
			html +='<tr><td style=\"font-size: 12px\" height=20 align=middle>'+billInfo.payDate+' </td></tr>';
			if (billInfo.tgName != null && billInfo.tgName != "")
				html +='<tr><td style=\"font-size: 14px\" height=20 align=middle><strong>입구영업소 : '+billInfo.tgName+'</strong> </td></tr>';
			html +='<tr><td><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody>';
			html +='<tr><td style=\"font-size: 14px\" height=18 width=80 align=middle><strong>'+billInfo.carType+' 종</strong> </td><td style=\"font-size: 13px\" align=middle><strong>'+billInfo.billAmount+' 원(카드)</strong> </td></tr></tbody></table></td></tr>';
			html +='<tr><td height=18><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody><tr><td height=14 width=80 align=middle>'+billInfo.tgType1+' </td><td align=middle>'+billInfo.billAmount1+' 원(카드) </td></tr></tbody></table></td></tr>';
			if (billInfo.tgType2 != null && billInfo.tgType2 != "")
				html +='<tr><td height=18><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody><tr><td height=14 width=80 align=middle>'+billInfo.tgType2+' </td><td align=middle>'+billInfo.billAmount2+' 원(카드) </td></tr></tbody></table></td></tr>';
			if (billInfo.tgType3 != null && billInfo.tgType3 != "")
				html +='<tr><td height=18><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody><tr><td height=14 width=80 align=middle>'+billInfo.tgType3+' </td><td align=middle>'+billInfo.billAmount3+' 원(카드) </td></tr></tbody></table></td></tr>';
			if (billInfo.tgType4 != null && billInfo.tgType4 != "")
				html +='<tr><td height=18><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody><tr><td height=14 width=80 align=middle>'+billInfo.tgType4+' </td><td align=middle>'+billInfo.billAmount4+' 원(카드) </td></tr></tbody></table></td></tr>';
			if (billInfo.tgType5 != null && billInfo.tgType5 != "")
				html +='<tr><td height=18><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody><tr><td height=14 width=80 align=middle>'+billInfo.tgType5+' </td><td align=middle>'+billInfo.billAmount5+' 원(카드) </td></tr></tbody></table></td></tr>';
			html +='<tr><td><table border=0 cellSpacing=0 cellPadding=0 width=200><tbody><tr><td style=\"font-size: 11px\" height=16 align=middle>공급가액 : '+billInfo.totalAmount+' 원 </td><td style=\"font-size: 11px\" align=middle>부가세 : '+billInfo.taxAmount+' 원 </td></tr></tbody></table></td></tr>';
			html +='<tr><td align=middle>'+billInfo.cardName+' </td></tr>';
			html +='<tr><td align=middle>'+billInfo.cardNumber+' </td></tr>';
			html +='<tr><td style=\"font-size: 14px\" height=25 align=middle>'+billInfo.txSerial+' </td></tr></tbody></table>';
			html +='</div>';
			
			rowCount++;
			if ((rowCount % 9) == 0 && rowCount > 0){
				html += '<div class=\"breakhere\"></DIV>';
				html += '<!--[if IE 7]><br style=\"height:0px;line-height:0\"/><![endif]-->';
			}
				
		});
		
		// 게시판 목록 갱신
		$('#listView').html(html);
		
	}
</script>

<style type="text/css">* {
	margin: 0px
}
table {
	text-align: center; font-family: "돋움", "굴림"; color: #000; font-size: 12px; text-decoration: none
}
.bill {
	background-image: url(/images/comm/logo00.png); border-bottom: #464646 1px solid; border-left: #464646 1px solid; width: 200px; background-repeat: no-repeat; background-position: 50% 45%; float: left; height: 320px; border-top: #464646 1px solid; border-right: #464646 1px solid
}
div.breakhere {
	page-break-before: always; width: 0px; height: 0px
}
</style>

<style type="text/css" media="print">.printDefault {
	text-align: center; display: none
}
</style>

</head>
<body>
<div class="printDefault">
<table style="margin-left: 20px" border="0" cellspacing="0" cellpadding="0" width="606">
<tbody>
<tr>
<td height="40" valign="middle" align="left"><img title="영수증인쇄" border="0" alt="영수증인쇄" src="/images/comm/img_billprt_title.jpg"/> </td>
<td height="40" valign="middle" align="right"><a onblur="fn_swap_img(this);" onfocus="fn_swap_img(this);" onmouseover="fn_swap_img(this);" onmouseout="fn_swap_img(this);" onclick="javascript:printDiv(); return false;" href="#mab"><img title="영수증인쇄" border="0" alt="영수증인쇄" src="/images/comm/btn_billprt.jpg"/></a> <a onblur="fn_swap_img(this);" onfocus="fn_swap_img(this);" onmouseover="fn_swap_img(this);" onmouseout="fn_swap_img(this);" onclick="javascript:fn_print_receipt(); return false;" href="#mab"><img title="보고서출력" border="0" alt="보고서출력" src="/images/comm/btn_rexprt.jpg"/></a> <a onblur="fn_swap_img(this);" onfocus="fn_swap_img(this);" onmouseover="fn_swap_img(this);" onmouseout="fn_swap_img(this);" onclick="javascript:window.close(); return false;" href="#mab"><img title="취소" border="0" alt="취소" src="/images/comm/btn_cancel05.jpg"/></a> </td>
</tr>
</tbody>
</table>
</div>
<div style="TEXT-ALIGN: center; WIDTH: 620px; MARGIN-LEFT: 30px" id="print1">
<div id="listView"></div>
</div><!--object id="factory" style="dispaly:none;" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="http://www.meadroid.com/scriptx/ScriptX.cab#Version=6,1,429,14"/-->
</body>
</html>