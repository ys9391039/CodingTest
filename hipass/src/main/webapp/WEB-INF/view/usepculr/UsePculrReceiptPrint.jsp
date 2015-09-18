<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>통행료 지불 excard 서비스 - 영수증</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK rel=stylesheet type=text/css href="https://www.excard.co.kr/css/cupertino/jquery-ui-1.8.13.custom.css"><LINK rel="shortcut icon" type=image/x-icon href="https://www.excard.co.kr/img/comm/icon_hipass.ico">
<SCRIPT language=javascript type=text/javascript src="https://www.excard.co.kr/js/hp/comm/jquery-1.5.1.min.js"></SCRIPT>
<SCRIPT language=javascript type=text/javascript src="https://www.excard.co.kr/js/hp/comm/json2.js"></SCRIPT>
<SCRIPT language=javascript type=text/javascript src="https://www.excard.co.kr/js/hp/comm/jquery-ui-1.8.13.custom.min.js"></SCRIPT>
<SCRIPT language=javascript type=text/javascript src="https://www.excard.co.kr/js/hp/comm/Utillity.js"></SCRIPT>
<SCRIPT language=javascript type=text/javascript src="https://www.excard.co.kr/RexServer30/rexscript/rexpert.js"></SCRIPT>
<SCRIPT language=javascript type=text/javascript src="https://www.excard.co.kr/RexServer30/rexscript/rexpert_properties.js"></SCRIPT>
<SCRIPT language=javascript type=text/javascript src="https://ck.softforum.co.kr/CKKeyPro/research_ex/CKKeyPro.js"></SCRIPT>
<OBJECT style="DISPLAY: none" id=TouchEnKey codeBase="https://ck.softforum.co.kr/CKKeyPro/research_ex/TouchEnkey3.1.0.21_32k.cab#version=3,1,0,21" hspace=0 vspace=0 classid=clsid:6CE20149-ABE3-462E-A1B4-5B549971AA38 width=0><PARAM NAME="ImageURL" VALUE=""><PARAM NAME="Xgate" VALUE=""><PARAM NAME="DefaultEnc" VALUE=""><PARAM NAME="TrayIcon" VALUE=""><PARAM NAME="AllowDuplicates" VALUE=""><PARAM NAME="DefaultPaste" VALUE=""><PARAM NAME="TrayPopup" VALUE=""><PARAM NAME="ReportErrors" VALUE=""><PARAM NAME="PKI" VALUE=""><PARAM NAME="ReportDataTypeMismatch" VALUE=""><PARAM NAME="ClearBufferOnEmpty" VALUE=""><PARAM NAME="UseIntranet" VALUE=""><PARAM NAME="ShowTrayIconOnFocus" VALUE=""><PARAM NAME="Hardware" VALUE=""><PARAM NAME="KeyboardOnly" VALUE=""><PARAM NAME="GenerateEvent" VALUE=""><PARAM NAME="Verify" VALUE=""><PARAM NAME="IgnoreProgress" VALUE=""><PARAM NAME="ArrowKey" VALUE=""><PARAM NAME="CSP" VALUE=""><PARAM NAME="IMPROVE" VALUE=""><PARAM NAME="Advice" VALUE=""><PARAM NAME="Lang" VALUE=""><PARAM NAME="Reader" VALUE=""><PARAM NAME="IDBase" VALUE=""></OBJECT>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/plugin/jquery.blockUI.js"></script> 
<script type="text/javascript" src="/js/common.js"></script>
<SCRIPT language=javascript type=text/javascript>
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
					alert("요청 중 서버에서 에러가 발생하였습니다.");
			}
			, complete : function() {
				cfHideBlock();
			}
		});
	}
	
	function makeResult(json){
		html = '';
		
		$.each(json.billInfoList, function(i, billInfo){
			html +='<DIV class=bill>';
			html +='<TABLE border=0 cellSpacing=0 cellPadding=0 width=200><TBODY>';
			html +='<TR><TD style="FONT-SIZE: 16px" height=20 vAlign=center align=middle><STRONG>하이패스는</STRONG> </TD></TR>';
			html +='<TR><TD style="FONT-SIZE: 16px" height=20 vAlign=center align=middle><STRONG>빠르고 편리합니다</STRONG> </TD></TR>';
			html +='<TR><TD height=10></TD></TR>';
			html +='<TR><TD style="FONT-SIZE: 20px" height=30 vAlign=center align=middle><STRONG>영수증</STRONG> </TD></TR>';
			html +='<TR><TD><TABLE border=0 cellSpacing=0 cellPadding=0 width=200><TBODY>';
			html +='<TR><TD style="FONT-SIZE: 12px" height=18 width=80 align=middle><STRONG>한국도로공사</STRONG> </TD>';
			html +='<TD style="FONT-SIZE: 12px" align=middle><STRONG>'+billInfo.regionName+'</STRONG> </TD></TR></TBODY></TABLE></TD></TR>';
			html +='<TR><TD style="FONT-SIZE: 12px" height=16 align=middle>TEL : '+billInfo.tgTel+' </TD></TR>';
			html +='<TR><TD style="FONT-SIZE: 12px" height=16 align=middle>사업자번호 : '+billInfo.tgAccount+' </TD></TR>';
			html +='<TR><TD style="FONT-SIZE: 12px" height=20 align=middle>'+billInfo.payDate+' </TD></TR>';
			html +='<TR><TD style="FONT-SIZE: 14px" height=20 align=middle><STRONG>입구영업소 : '+billInfo.tgName+'</STRONG> </TD></TR>';
			html +='<TR><TD><TABLE border=0 cellSpacing=0 cellPadding=0 width=200><TBODY>';
			html +='<TR><TD style="FONT-SIZE: 14px" height=18 width=80 align=middle><STRONG>'+billInfo.carType+' 종</STRONG> </TD>';
			html +='<TD style="FONT-SIZE: 13px" align=middle><STRONG>'+billInfo.billAmount+' 원(카드)</STRONG> </TD></TR></TBODY></TABLE></TD></TR>';
			html +='<TR><TD height=18><TABLE border=0 cellSpacing=0 cellPadding=0 width=200><TBODY>';
			html +='<TR><TD height=14 width=80 align=middle>KEC </TD>';
			html +='<TD align=middle>'+billInfo.billAmount+' 원(카드) </TD></TR></TBODY></TABLE></TD></TR><TR>';
			html +='<TD><TABLE border=0 cellSpacing=0 cellPadding=0 width=200><TBODY>';
			html +='<TR><TD style="FONT-SIZE: 11px" height=16 align=middle>공급가액 : '+billInfo.billAmount+' 원 </TD>';
			html +='<TD style="FONT-SIZE: 11px" align=middle>부가세 : 0 원 </TD></TR></TBODY></TABLE></TD></TR>';
			html +='<TR><TD align=middle>'+billInfo.cardName+' </TD></TR>';
			html +='<TR><TD align=middle>'+billInfo.cardNumber+' </TD></TR>';
			html +='<TR><TD style="FONT-SIZE: 14px" height=25 align=middle>'+billInfo.txSerial+' </TD></TR></TBODY></TABLE>';
			html +='</DIV>';
		});
		
		// 게시판 목록 갱신
		$('#listView').html(html);
		
	}
</SCRIPT>

<STYLE type=text/css>* {
	MARGIN: 0px
}
TABLE {
	TEXT-ALIGN: center; FONT-FAMILY: "돋움", "굴림"; COLOR: #000; FONT-SIZE: 12px; TEXT-DECORATION: none
}
.bill {
	BACKGROUND-IMAGE: url(https://www.excard.co.kr/images/comm/logo00.png); BORDER-BOTTOM: #464646 1px solid; BORDER-LEFT: #464646 1px solid; WIDTH: 200px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 50% 45%; FLOAT: left; HEIGHT: 320px; BORDER-TOP: #464646 1px solid; BORDER-RIGHT: #464646 1px solid
}
DIV.breakhere {
	PAGE-BREAK-BEFORE: always; WIDTH: 0px; HEIGHT: 0px
}
</STYLE>

<STYLE type=text/css media=print>.printDefault {
	TEXT-ALIGN: center; DISPLAY: none
}
</STYLE>

<FORM id=hpForm method=post name=hpForm action=#hpForm><INPUT id=card_kind value=all type=hidden name=card_kind><INPUT id=card_com value=all type=hidden name=card_com><INPUT id=ecd_no value=all type=hidden name=ecd_no><INPUT id=sDate value=20150616 type=hidden name=sDate><INPUT id=eDate value=20150915 type=hidden name=eDate><INPUT id=date_type value=work type=hidden name=date_type><INPUT id=pageSize value=10 type=hidden name=pageSize><INPUT id=pageNo type=hidden name=pageNo><INPUT id=order_item value=date type=hidden name=order_item><INPUT id=order_type value=desc type=hidden name=order_type><INPUT id=row_nums type=hidden name=row_nums><INPUT id=is_all value=1 type=hidden name=is_all></HEAD>
<BODY><INPUT style="DISPLAY: none" value="쿼리 전송" type=submit> </FORM>
<DIV class=printDefault>
<TABLE style="MARGIN-LEFT: 20px" border=0 cellSpacing=0 cellPadding=0 width=606>
<TBODY>
<TR>
<TD height=40 vAlign=center align=left><IMG title=영수증인쇄 border=0 alt=영수증인쇄 src="https://www.excard.co.kr/images/comm/img_billprt_title.jpg"> </TD>
<TD height=40 vAlign=center align=right><A onblur=fn_swap_img(this); onfocus=fn_swap_img(this); onmouseover=fn_swap_img(this); onmouseout=fn_swap_img(this); onclick="javascript:printDiv(); return false;" href="#mab"><IMG title=영수증인쇄 border=0 alt=영수증인쇄 src="https://www.excard.co.kr/images/comm/btn_billprt.jpg"></A> <A onblur=fn_swap_img(this); onfocus=fn_swap_img(this); onmouseover=fn_swap_img(this); onmouseout=fn_swap_img(this); onclick="javascript:fn_print_receipt(); return false;" href="#mab"><IMG title=보고서출력 border=0 alt=보고서출력 src="https://www.excard.co.kr/images/comm/btn_rexprt.jpg"></A> <A onblur=fn_swap_img(this); onfocus=fn_swap_img(this); onmouseover=fn_swap_img(this); onmouseout=fn_swap_img(this); onclick="javascript:window.close(); return false;" href="#mab"><IMG title=취소 border=0 alt=취소 src="https://www.excard.co.kr/images/comm/btn_cancel05.jpg"></A> </TD></TR></TBODY></TABLE></DIV>
<DIV style="TEXT-ALIGN: center; WIDTH: 620px; MARGIN-LEFT: 30px" id=print1>
<div id="listView"></div>
</DIV><!--object id="factory" style="dispaly:none;" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="http://www.meadroid.com/scriptx/ScriptX.cab#Version=6,1,429,14"/--></BODY></HTML>