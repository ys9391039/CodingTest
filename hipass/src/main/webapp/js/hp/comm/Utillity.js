
//오른쪽 클릭 방지
document.oncontextmenu=function(){return false;};
//마우스 드래그 방지
document.ondragstart=function(){return false;};
//선택 방지
document.onselectstart=function(){return false;};

/********************************************************
파일명 : Utillity.js
설 명 : 공통 기능 JavaScript
 수정일       수정자    Version    Function 명
---------- -------- ---------- --------------
2011.06.22   김경보      1.0        최초 생성
*********************************************************/

String.prototype.replaceAll = function (str1,str2){
	var str = this;     
	var result = str.replace(eval("/"+str1+"/gi"),str2);
	return result;
}

/************************************************************************
함수명: iclasAddLoadEvent
설  명: 서버 트래킹 
인 자 : iclasLoad()
사용법 : iclasAddLoadEvent('iclasLoad')
작성일 : 2011-12-08
작성자 : 최성훈
수정일       수정자       수정내용
---------- ------  -------------------
2011.12.08  최성훈   최초작성
************************************************************************/
function iclasAddLoadEvent(func) {
    var oldonload = window.onload;
    if(typeof window.onload != 'function') { window.onload = func; }
    else { window.onload = function() { oldonload(); func(); } }
}

function iclasLoad() {
    try {        	
        var serviceId = 'excard';
        var serverUrl = 'http://wwwlog.ex.co.kr:8090/iclas';
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        var loaded = false;
        script.type = 'text/javascript'; script.charset = 'UTF-8';
        script.onreadystatechange = function () {
            if (this.readyState == 'loaded' || this.readyState == 'complete') {
                if (loaded) { return; } loaded = true; iclas.execute(serviceId, serverUrl);
            }
        }
        script.onload = function () { iclas.execute(serviceId, serverUrl); }
        script.src = serverUrl + '/tracker/tracker.js';
        head.appendChild(script);
    } catch(e) {}
}

/************************************************************************
함수명: call_access_log
설  명: 접속자 통계 모듈 호출 
인 자 : 
사용법 : call_access_log()
작성일 : 2012-12-21
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2012.12.20  최성훈   최초작성
************************************************************************/
function call_access_log(){
	var url_info = document.location.href.split("?")[0].split("/");
	
	$.ajax({
		url : '/cmmn/AjaxAccessLog.do',
		type : 'post',
		async : true,
		dataType: 'json',
		data: {
			access_url : url_info[url_info.length - 1].split("#")[0],
			access_menu : document.title
		}
	});
}

/************************************************************************
함수명: fn_open_popup
설  명: 팝업 호출
인 자 : url(팝업 url), name(팝업명), width(팝업폭), height(팝업높이), scroll(스크롤생성여부)
사용법 : fn_open_popup('popup.do', 'pop', 500, 500, 'yes')
작성일 : 2011-06-22
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.06.22  김경보   최초작성
************************************************************************/
function fn_open_popup(url, name, width, height, scroll, param){
	var top = (screen.height-height)/2;
	var left = (screen.width-width)/2;
	if(param){
		url += "?" + param;
	}
	var pop = window.open(url, name,"top="+ top +", left="+ left +", width="+ width +", height="+ height +", scrollbars="+ scroll +", resizable=no, location=no");
	if(pop){
		pop.focus();
		return name;
	}
}
/************************************************************************
함수명: fn_new_win
설  명: 새창 띄우기
인 자 : url(새창주소), name(새창명)
사용법 : fn_new_win('excard.co.kr', 'excard')
작성일 : 2011-10-18
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.10.18  김경보   최초작성
************************************************************************/
function fn_new_win(url, name){
	if(url != ""){
		window.open(url, name);
	}
}
/************************************************************************
함수명: fn_next_cursor
설  명: 텍스트박스에 일정길이의 값이 입력되면 지정된 다음 태그로 포커스 이동
인 자 : current(현재태그), length(현재태그값 한정 길이), next(포커스 이동 대상 태그)
사용법 : fn_next_cursor(current, 4, next)
작성일 : 2011-07-06
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.06.22  김경보   최초작성
************************************************************************/
function fn_next_cursor(current, length, next){
	if(current.value.length == length){
		next.focus();
	}
}

/************************************************************************
함수명: fn_open_zipcd
설  명: 우편번호 조회 팝업 호출
인 자 : callback(우편번호 선택 후 호출될 메소드명)
사용법 : fn_open_zipcd(setZipcode)
작성일 : 2011-07-06
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.06.22  김경보   최초작성
************************************************************************/
function fn_open_zipcd(callback){
	callback = callback.split("<").join("");
	callback = callback.split(">").join("");
	callback = callback.split("javascript").join("");
	callback = callback.split("Javascript").join("");
	callback = callback.split("JavaScript").join("");
	callback = callback.split("JAVASCRIPT").join("");
	callback = callback.split("script").join("");
	callback = callback.split("SCRIPT").join("");
	callback = callback.split("object").join("");
	callback = callback.split("applet").join("");
	callback = callback.split("embed").join("");
	callback = callback.split("form").join("");
	callback = callback.split("embed").join("");
	callback = callback.split("iframe").join("");
	
	var url = "/cmmn/ZipCodeSearch.do?callback=" + callback;
	fn_open_popup(url, "zipcodePop", 650, 720, "no");
}

/************************************************************************
함수명: fn_open_bizcd
설  명: 업태/업종 조회 팝업 호출
인 자 : callback(업태/업종 선택 후 호출될 메소드명)
사용법 : fn_open_bizcd(setBizcode)
작성일 : 2011-07-06
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.06.22  김경보   최초작성
************************************************************************/
function fn_open_bizcd(callback){
	callback = callback.split("<").join("");
	callback = callback.split(">").join("");
	callback = callback.split("javascript").join("");
	callback = callback.split("Javascript").join("");
	callback = callback.split("JavaScript").join("");
	callback = callback.split("JAVASCRIPT").join("");
	callback = callback.split("script").join("");
	callback = callback.split("SCRIPT").join("");
	callback = callback.split("object").join("");
	callback = callback.split("applet").join("");
	callback = callback.split("embed").join("");
	callback = callback.split("form").join("");
	callback = callback.split("embed").join("");
	callback = callback.split("iframe").join("");
	
	var url = "/cmmn/BizCodeSearch.do?callback=" + callback;
	fn_open_popup(url, "bizcodePop", 400, 440, "no");
}

/************************************************************************
함수명: fn_replaceParam
설  명: 크로스사이트스크립팅 방지
인 자 : str
사용법 : fn_replaceParam(setBizcode)
작성일 : 2014-11-25
작성자 : 이창환
수정일       수정자       수정내용
---------- ------  -------------------
2014.11.25  이창환   최초작성
************************************************************************/
function fn_replaceParam(str){
	str = str.split("<script>").join("");
	str = str.split("<object>").join("");
	str = str.split("<applet>").join("");
	str = str.split("<form>").join("");
	str = str.split("<embed>").join("");
	str = str.split("<iframe>").join("");
	str = str.split("<frame>").join("");
	str = str.split("<base>").join("");
	str = str.split("<body>").join("");
	str = str.split("<frameset>").join("");
	str = str.split("<html>").join("");
	str = str.split("<img>").join("");
	str = str.split("<layer>").join("");
	str = str.split("<ilayer>").join("");
	str = str.split("<meta>").join("");
	str = str.split("<p>").join("");
	str = str.split("<style>").join("");
	str = str.split("<xxx src>").join("");
	str = str.split("<a href>").join("");
	str = str.split("<bgsound>").join("");
	str = str.split("<div>").join("");

	str = str.split("javascript").join("");
	str = str.split("vbscript").join("");
	str = str.split("expression").join("");
	str = str.split("document.cookie").join("");
	str = str.split("document.location").join("");
	str = str.split("document.write").join("");
	str = str.split("location.href").join("");
	str = str.split("onabort").join("");
	str = str.split("onclick").join("");
	str = str.split("ondblclick").join("");
	str = str.split("ondragdrop").join("");
	str = str.split("onerror").join("");
	str = str.split("onfocus").join("");
	str = str.split("onkeydown").join("");
	str = str.split("onkeypress").join("");
	str = str.split("onkeyup").join("");
	str = str.split("onload").join("");
	str = str.split("onmousedown").join("");
	str = str.split("onmousemove").join("");
	str = str.split("onmouseout").join("");
	str = str.split("onmouseover").join("");
	str = str.split("onmouseup").join("");
	str = str.split("onmove").join("");
	str = str.split("onreset").join("");
	str = str.split("onresize").join("");
	str = str.split("onselect").join("");
	str = str.split("onsubmit").join("");
	str = str.split("onunload").join("");
	str = str.split("onblur").join("");
	str = str.split("onchange").join("");

	str = str.split("./").join("");
	str = str.split("%").join("");
	str = str.split("<").join("");
	str = str.split(">").join("");
	str = str.split("&").join("");
	str = str.split("#").join("");
	str = str.split("=").join("");
	str = str.split("OR").join("");
	str = str.split("Or").join("");
	str = str.split("oR").join("");
	str = str.split("or").join("");
	str = str.split("--").join("");

	return str;
}

/************************************************************************
함수명: fn_open_card_search
설  명: 카드번호 조회 팝업 호출
인 자 : callback(카드번호 선택 후 호출될 메소드명)
사용법 : fn_open_card_search(setCardNo)
작성일 : 2011-07-06
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.06.22  김경보   최초작성
************************************************************************/
function fn_open_card_search(callback){
	var popName = fn_open_popup("/html/cmmn/progress.html", "cardSelPop", 620, 600, "no");
	var form = document.hpForm;
	form.target = popName;
	form.action = "/eccd/CardSearch.do?callback=" + callback;
	form.submit();
}

/************************************************************************
함수명: fn_update_company
설  명: 기업정보 수정 팝업 호출
인 자 : callback(기업정보 수정 후 호출될 메소드명), type(유저타입)
사용법 : fn_update_company(setCompInfo, user_type)
작성일 : 2011-07-06
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.11  김경보   최초작성
************************************************************************/
function fn_update_company(callback , type){
	var url = "/corpuser/SelectCompanyInfo.do?callback=" + callback;
	if ( type != '2'){
		fn_open_popup(url, "companyPop", 520, 500, "no");
	}else{
		fn_open_popup(url, "companyPop", 520, 520, "no");
	}
}

/************************************************************************
함수명: fn_update_auto_receipt
설  명: 월합계계산서 자동 발급 정보 팝업 호출
인 자 : callback(기업정보 수정 후 호출될 메소드명)
사용법 : fn_update_auto_receipt(setAutoReceipt)
작성일 : 2011-07-20
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.20  김경보   최초작성
************************************************************************/
function fn_update_auto_receipt(callback){
	var url = "/receipt/ReceiptAutoIssuePopup.do?callback=" + callback;
	fn_open_popup(url, "receiptAutoPop", 520, 420, "no");
}

/************************************************************************
함수명: fn_update_auto_taxbill
설  명: 전자계산서 자동 발급 정보 팝업 호출
인 자 : callback(기업정보 수정 후 호출될 메소드명)
사용법 : fn_update_auto_taxbill(setAutoReceipt)
작성일 : 2015-05-28
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2015.05.28  최성훈   최초작성
************************************************************************/
function fn_update_auto_taxbill(callback){
	var url = "/receipt/TaxBillAutoPopup.do?callback=" + callback;
	fn_open_popup(url, "taxbill_AutoPop", 800, 780, "yes");
}

/************************************************************************
함수명: fn_not_null
설  명: Input 태그에 내용이 없으면  false를 return함.
인 자 : input(입력태그)
사용법 : fn_not_null(input, '대표자명을');
작성일 : 2011-07-08
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.08  김경보   최초작성
************************************************************************/
function fn_not_null(input, str){
	if(input.val().length == 0){
		alert( str + ' 입력하세요.');
		input.focus();
		return false;
	}else{
		return true;
	}
}

/************************************************************************
함수명: fn_check_pwd
설  명: 비밀번호 체크(영숫자)
인 자 : 입력받은 비밀번호
사용법 : fn_check_pwd(pwd);
작성일 : 2011-07-11
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.11  김경보   최초작성
************************************************************************/
function fn_check_pwd(str){
	var temp = "";
	var intCnt = 0;
	for(var i = 0; i < str.length; i++) {
		temp = str.charAt(i);
		if(temp == str.charAt(i+1) && temp == str.charAt(i+2) && temp == str.charAt(i+3)) {
			intCnt = intCnt + 1;
		}
	}
     if(intCnt > 0) {
    	 alert("4번 연속된 동일문자가  있습니다");
    	 return false;
	} else {
	var flag1=false;
	var flag2=false;
	var flag3=false;
    var alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    var num = "1234567890";
    var spe = "~!@#$%^&*()_+|";
    var len = str.length;
    var ch;
    for(i = 0 ; i < len ; i++ ){
        ch=str.charAt(i);
        
        if(ch == "~" || ch == "!" || ch == "@" || ch == "#" || ch == "$" || ch == "%" || ch == "^" ||
           ch == "&" || ch == "*" || ch == "(" || ch == ")" || ch == "_" || ch == "+" || ch == "|" ||
           ch == "?" || ch == "[" || ch == "]"){
           continue;
        }else{
           if(alpha.match(ch)){
              flag1 = true;
              break;
           }
        }
    }
    for(i = 0 ; i < len ; i++ ){
        ch=str.charAt(i);
        
        if(ch == "~" || ch == "!" || ch == "@" || ch == "#" || ch == "$" || ch == "%" || ch == "^" ||
           ch == "&" || ch == "*" || ch == "(" || ch == ")" || ch == "_" || ch == "+" || ch == "|" ||
           ch == "?" || ch == "[" || ch == "]"){
           continue;
        }else{
           if(num.match(ch)){
              flag2 = true;
              break;
           }
        }
    }
    for(i = 0 ; i < len ; i++ ){
        ch=str.charAt(i);
        
        if(ch == "~" || ch == "!" || ch == "@" || ch == "#" || ch == "$" || ch == "%" || ch == "^" ||
           ch == "&" || ch == "*" || ch == "(" || ch == ")" || ch == "_" || ch == "+" || ch == "|" ||
           ch == "?" || ch == "[" || ch == "]"){
            flag3 = true;
            break;
        }
    }
    return ( flag1 && flag2 && flag3 ) ? true : false;
	}
}

/************************************************************************
함수명: fn_check_passwd
설  명: 비밀번호 체크(영,숫자,특문)
인 자 : 입력받은 비밀번호
사용법 : fn_check_passwd(pwd);
작성일 : 2014-04-24
작성자 : 장동림
수정일       수정자       수정내용
---------- ------  -------------------
2014.04.24  장동림   최초작성
************************************************************************/
function fn_check_passwd(str){
	var temp = "";
	var intCnt = 0;
	
	for(var i = 0; i < str.length; i++) {
		temp = str.charAt(i);
		if(temp == str.charAt(i+1) && temp == str.charAt(i+2) && temp == str.charAt(i+3)) {
			intCnt = intCnt + 1;
		}
	}
	if(intCnt > 0) {
		msg_chk = "4번 연속된 동일문자가  있습니다";
		return false;
	}
	
	var flag1=false;
	var flag2=false;
	var flag3=false;
	var alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	var num = "1234567890";
	var len = str.length;
	var ch;
	for(i = 0 ; i < len ; i++ ){
		ch=str.charAt(i);
		if( ch == "~" || ch == "!" || ch == "@" || ch == "#" || ch == "$" || ch == "%" || ch == "^" ||
			ch == "&" || ch == "*" || ch == "(" || ch == ")" || ch == "_" || ch == "+" || ch == "|" ||
			ch == "?" || ch == "[" || ch == "]"){
			flag3 = true;//특문
			continue;
		}else{
			if(alpha.match(ch)){//문자
				flag1 = true;
			}
			if(num.match(ch)){//숫자
				flag2 = true;
			}
			if(flag1 && flag2 && flag3 && true) {
				break;
			}
		}
	}
	if(!flag1)msg_chk = "영문자가 없습니다.";
	if(!flag2)msg_chk = "숫자가 없습니다.";
	if(!flag3)msg_chk = "특수문자가 없습니다.";
	return ( flag1 && flag2 && flag3 && true ) ? true : false;
}

/************************************************************************
함수명: fn_check_id
설  명: 아이디 체크(영숫자+특수기호('-','_'))
인 자 : 입력받은 아이디
사용법 : fn_check_id(id);
작성일 : 2011-07-11
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.11  김경보   최초작성
************************************************************************/
function fn_check_id(id){
	var format = /^[a-zA-Z]([a-zA-Z]*[0-9]*-*_*)*$/;
	if (id !=""){
		return format.test(id);
	}else{
		return false;
	}
}

/************************************************************************
함수명: fn_car_num_chk
설  명: 차량번호 체크(적합:true, 부적합:false)
인 자 : 차량번호(7자리), 차량번호(9자리)
사용법 : fn_car_num_chk('53가2345'); fn_car_num_chk('서울53마2301');
작성일 : 2011-07-13
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.11  김경보   최초작성
************************************************************************/
//차량번호 검사
function fn_car_num_chk(car_num) 
{ 
    var v= car_num; 
    
    var pattern1 = /\d{2}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 12저1234 
    var pattern2 = /[가-힣ㄱ-ㅎㅏ-ㅣ\x20]{1}\d{1}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 서울1치1233 
    var pattern3 = /[가-힣ㄱ-ㅎㅏ-ㅣ\x20]{2}\d{2}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 서울12치1233 

    if (!pattern1.test(v)) { 
        if (!pattern2.test(v)) {
        	if(!pattern3.test(v)){
            	alert("잘못된 차량번호입니다.");
                return false; 
        	}
        } 
    } 
    return true;
} 

/************************************************************************
함수명	: fn_phone_check
설  명: 전화번호체크
인 자 : tel1(전화번호1), tel1(전화번호2), tel1(전화번호3),
사용법 : fn_phone_check(telNo1, telNo2, telNo3)
작성일 : 2011-12-12
작성자 : 최성훈
수정일       수정자       수정내용
---------- ------  -------------------
2011.12.12  최성훈   최초작성
************************************************************************/
function fn_phone_check(tel1, tel2, tel3){
    if(!tel1.val() || !tel2.val() || !tel3.val()) {
        alert("전화번호를 정확히 입력해 주십시오.");
        if(!tel1.val()) tel1.focus();
        else if(!tel2.val()) tel2.focus();
        else tel3.focus();
        return false;
    }else if ( !isNum (trim( tel1.val() + tel2.val() + tel3.val() ))){
    	alert("전화번호는 숫자를 입력하십시오");
    	return false;
    }
    return true;
}
function isNum(inputStr) {
	for (var i = 0; i < inputStr.length; i++) {
		var oneChar = inputStr.substring(i, i+1);
		if (oneChar < "0" || oneChar > "9") {
        return false;
      }
    }
    return true;
}
function trim(inputStr) {
    var tempStr = "";
     for ( var i = 0; i < inputStr.length; i++) {
      var oneChar = inputStr.substring(i,i+1);
      if (oneChar == " ")
        oneChar = "";
      tempStr += oneChar;
    }
    return tempStr;
}

/************************************************************************
함수명: fn_check_isEmail
설  명: 이메일 체크(특수기호(/^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/))
인 자 : 입력받은 이메일
사용법 : fn_check_isEmail(obj);
작성일 : 2011-07-13
작성자 : 최성훈
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.13  최성훈   최초작성
************************************************************************/
function fn_check_isEmail(obj){
	 var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
	 return format.test(obj);
}

/************************************************************************
함수명: fn_set_cookie
설  명: 쿠기 설정
인 자 : 쿠키명 설정값 유효기간(일수)
사용법 : fn_set_cookie('testCookie', 'test', 1);
작성일 : 2011-07-20
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.20  김경보   최초작성
************************************************************************/
function fn_set_cookie(name, value, expiredays){
    var todayDate = new Date();
    todayDate.setDate(todayDate.getDate() + expiredays);
    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";";
    window.close();
}

/************************************************************************
함수명: fn_get_cookie
설  명: 쿠키명에 해당하는 쿠키 취득
인 자 : 쿠키명
사용법 : fn_get_cookie('testCookie');
작성일 : 2011-07-20
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.20  김경보   최초작성
************************************************************************/
function fn_get_cookie(name) { 
	var start;
	var end;
	for(var i = 0; i <= document.cookie.length; i++){
		start = i; 
		end = start + name.length;
		if(document.cookie.substring(start, end) == name){
			start = end + 1;
			end = document.cookie.indexOf(";", end);
			if(end < start){
				end = document.cookie.length;
			}
			return document.cookie.substring(start, end);
		}		
	}
	return ""; 
}

/************************************************************************
함수명: fn_set_date
설  명: 시작일자와 종료일자를 선택된 기간으로 설정
인 자 : 시작일, 종료일, 기간, 기간모드('D:일', 'M:월')
사용법 : fn_set_date(sDate, eDate, 1, 'D');
작성일 : 2011-07-21
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.21  김경보   최초작성
************************************************************************/
function fn_set_date(sDate, eDate, diff, mode){
	
	var arr_eDate = fn_convert_date(new Date());
	$("#" + eDate + "_view").val(arr_eDate[0] + "-" + arr_eDate[1] + "-" + arr_eDate[2]);
	$("#" + eDate).val(arr_eDate[0] + arr_eDate[1] + arr_eDate[2]);
	
	var startDate = new Date(arr_eDate[0], arr_eDate[1] - 1, arr_eDate[2]);
	startDate.setDate(startDate.getDate() + 1);
	
	if(mode == 'D'){
		startDate.setDate(startDate.getDate() - diff);
	}else if('M'){
		startDate.setMonth(startDate.getMonth() - diff);
	}

	var sArr = fn_convert_date(startDate);

	$("#" + sDate + "_view").val(sArr[0] + "-" + sArr[1] + "-" + sArr[2]);
	$("#" + sDate).val(sArr[0] + sArr[1] + sArr[2]);
}

/************************************************************************
함수명: fn_add_date
설  명: 특정일자에 날짜를 더하거나 뺌
인 자 : 기간모드('YYYY,YY:년', 'MM:월', 'DD:일'), 대상일자, 기간
사용법 : fn_add_date('YYYY', '20110901', 1);
작성일 : 2011-07-21
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.21  김경보   최초작성
************************************************************************/
function fn_add_date(mode, date, add_num){
  
	var retValue = "";

	var intYear = eval(date.substring(0, 4));
	var intMon  = eval(date.substring(4, 6)) - 1;
	var intDay  = eval(date.substring(6));
	
	var obj_date = new Date(intYear, intMon, intDay);
	
	mode = mode.toUpperCase();
	if(mode == "YY" || mode == "YYYY")        
		obj_date = new Date(intYear + add_num, intMon, intDay);
	else if(mode == "MM")   
		obj_date = new Date(intYear, intMon + add_num, intDay);
	else if(mode == "DD")   
		obj_date = new Date(intYear, intMon, intDay + add_num);
	else{
		return date;
	}

	
	var arr_date = fn_convert_date(obj_date);
	
	return arr_date[0] + arr_date[1] + arr_date[2];
}

/************************************************************************
함수명: fn_convert_date
설  명: 파라미터값의 날자를 배열 형식의 년월일 반환
인 자 : 날짜
사용법 : fn_convert_date(sDate);
작성일 : 2011-07-21
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.21  김경보   최초작성
************************************************************************/
function fn_convert_date(date){
	var arr = new Array(3);
	arr[0] = date.getFullYear() + "";
	arr[1] = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : "" + (date.getMonth() + 1);
	arr[2] = date.getDate() < 10 ? "0" + date.getDate() : date.getDate() + "";
	return arr;
}

/************************************************************************
함수명: fn_set_that_month
설  명: 당월 1일부터 현재일 년월일 반환
인 자 : 없음
사용법 : fn_set_that_month();
작성일 : 2013-09-25
작성자 : 최성훈
수정일       수정자       수정내용
---------- ------  -------------------
2013.09.25  최성훈   최초작성
************************************************************************/
function fn_set_that_month(){
	var arr_date = fn_convert_date(new Date());
	var today = arr_date[0] + arr_date[1] + arr_date[2];

	//초기화
	$("#sDate_view").val(today.substring(0, 4) + "-" + today.substring(4, 6) + "-" + today.substring(6));
	$("#sDate").val(today.substring(0, 4) + today.substring(4, 6) + today.substring(6));
	$("#eDate_view").val(today.substring(0, 4) + "-" + today.substring(4, 6) + "-" + today.substring(6));
	$("#eDate").val(today.substring(0, 4) + today.substring(4, 6) + today.substring(6));

	//당월일자
	var firstDay = arr_date[0] + arr_date[1] + "01";
	$("#sDate_view").val(arr_date[0] + "-" + arr_date[1] + "-" + "01");
	$("#sDate").val(arr_date[0] + arr_date[1] + "01");
}

/************************************************************************
함수명: fn_set_last_month
설  명: 전월 1일부터 마지막일 년월일 반환
인 자 : 없음
사용법 : fn_set_last_month();
작성일 : 2013-09-25
작성자 : 최성훈
수정일       수정자       수정내용
---------- ------  -------------------
2013.09.25  최성훈   최초작성
************************************************************************/
function fn_set_last_month(){
	var arr_date = fn_convert_date(new Date());
	var today = arr_date[0] + arr_date[1] + arr_date[2];
	var to_date = fn_add_date("MM", today, -1);

	var startDay = to_date.substring(0, 4) + to_date.substring(4, 6) + "01";
	var lastDay = to_date.substring(0, 4) + to_date.substring(4, 6) + (new Date(to_date.substring(0, 4),to_date.substring(4, 6),0)).getDate();

	$("#sDate_view").val(startDay.substring(0, 4) + "-" + startDay.substring(4, 6) + "-" + "01");
	$("#sDate").val(startDay.substring(0, 4) + startDay.substring(4, 6) + "01");

	$("#eDate_view").val(lastDay.substring(0, 4) + "-" + lastDay.substring(4, 6) + "-" + lastDay.substring(6));
	$("#eDate").val(lastDay.substring(0, 4) + lastDay.substring(4, 6) + lastDay.substring(6));
}

/************************************************************************
함수명: fn_sync_height
설  명: 해당 iframe의 높이값을 동적으로 설정
인 자 : iframe 객체, 최소 높이
사용법 : fn_sync_height(obj, 200);
작성일 : 2011-07-21
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.07.21  김경보   최초작성
************************************************************************/
function fn_sync_height(obj, height){
	
	obj.height = 0;
	var sync_height = obj.contentWindow.document.body.scrollHeight;
	
	//최소 높이보다 작으면 최소 높이를 설정한다
	if(sync_height < height){
		sync_height = height;
	}
	obj.height = sync_height;

}

/************************************************************************
함수명: fn_loading
설  명: 로딩바 제어
인 자 : 옵션 - 보임:true, 숨김:false
사용법 : fn_loading(true);
작성일 : 2011-10-10
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.10.10  김경보   최초작성
************************************************************************/
function fn_loading(visible){
	if(visible){
		$("#dialog-loading").dialog({
			modal: true,
			closeOnEscape: false,
			closeText: '',
			height: 15,
			width: 250,
			resizable: false
		});
	}else{
		$("#dialog-loading").dialog("close")
	}
}

/************************************************************************
함수명: fn_swap_img
설  명: 이미지 스와핑
인 자 : obj - 스왑 대상 이미지의 A링크
사용법 : fn_swap_img(obj);
작성일 : 2011-10-17
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.10.17  김경보   최초작성
************************************************************************/
function fn_swap_img(obj){
	
	//이미지 취득
	var img_src = $(obj).find('img').attr("src");
	//확장자분리용 인덱스
	var idx = img_src.lastIndexOf(".");
	//확장자
	var imgType = img_src.substr(idx + 1);
	//이미지명
	var imgName = img_src.substr(0, idx);

	//이미지종류 판단
	if(imgName.lastIndexOf("_over") != -1){
		imgName = imgName.substr(0, imgName.lastIndexOf("_over"));
	}else{
		imgName += "_over";
	}
	$(obj).find('img').attr("src", imgName + "." + imgType);
}

/************************************************************************
함수명: fn_cancel_bubbling
설  명: 버블링 취소 : 특정 이벤트가 발생했을 경우 그이후의 이벤트는 발생하지 않게 한다.
인 자 : 
사용법 : fn_cancle_bubbling();
작성일 : 2011-10-28
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.10.28  김경보   최초작성
************************************************************************/
function fn_cancel_bubbling(){
	if(event.stopPropagation){
		event.stopPropagation();
	}else{
		event.cancelBubble = true;
	}
}

/************************************************************************
함수명: fn_check_byte
설  명: 특정 문자열에 대해 바이트 길이 취득
인 자 : 문자열
사용법 : fn_check_byte('테스트123');
작성일 : 2011-11-02
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2011.11.02  김경보   최초작성
************************************************************************/
function fn_check_byte(str){ 
   var strByte = 0; 

   for(var i = 0; i < str.length; i++){ 
       if(str.charAt(i) >= ' ' && str.charAt(i) <= '~' ) 
           strByte++; 
       else 
           strByte += 2; 
   }
   return strByte;
}

/************************************************************************
함수명: popupResizeHieght
설  명: 팝업 높이 조절
인 자 : 팝업폭(지정)
사용법 : popupResizeHieght(500);
작성일 : 2012-04-30
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2012.04.30  김경보   최초작성
************************************************************************/
function popupResizeHieght(width){
	var odv = $("<div></div");
	$("body").append(odv);
	odv.attr("style", "position:absolute;left:0px;top:0px;width:100%;height:100%;");

	window.resizeBy(width - $(odv).width(), $("body").height() - $(odv).height());
	odv.remove();   
}

/************************************************************************
함수명: getRexpertData
설  명: Rexpert형식(xml)으로 변환
인 자 : 서버로부터 받은 데이타
사용법 : getRexpertData(...);
작성일 : 2012-05-31
작성자 : 김경보
수정일       수정자       수정내용
---------- ------  -------------------
2012.05.31  김경보   최초작성
************************************************************************/
function getRexpertData(str){
	
	//null제거
	str = str.replaceAll("null", "");
	
	var xml = "<root><ds1>";
	var ch;
	var depth;
	var end_tag;
	var buff = "";
	for(var i = 0; i < str.length; i++){ 
		
		ch = str.charAt(i);
		
		if(ch == "[" || ch == "]" || ch == " "){
			continue;
		} 
		
		if(ch == "{"){
			depth = 0;
			xml += "<record>";
			continue;
		}
		if(ch == "}"){
			depth = 0;
			xml += buff;
			xml += end_tag;
			xml += "</record>";
			buff = "";
			continue;
		}
		
		if(ch == "="){
			depth = 1;
			xml += "<" + buff + ">";
			end_tag = "</" + buff + ">";
			buff = "";
			continue;
		}

		if(ch == ","){
			if(depth == 1){
				if(end_tag == "</proc_time>" || end_tag == "</esc_time>"){
					buff = buff.replaceAll("-", "");
					buff = buff.replaceAll(":", "");
				}
				xml += buff;
				xml += end_tag;
				buff = "";
			}
			continue;
		}
		
		buff += str.charAt(i);
	}
	xml += "</ds1></root>";
	
	return xml;
}