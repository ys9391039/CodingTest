
/***************************************************************
 * 바이트 기준 길이 체크
 * 
 * @param 문자열
 * @return 길이
****************************************************************/
function cfGetByteLength(obj) {
	var len = 0;
	str = obj.value;
	if (str == null) return ;
	
	for (var i = 0; i < str.length; i++) {
		var chr = escape(str.charAt(i));
		
		if (chr.length == 1) {
			len++;
		} else if (chr.indexOf("%u") != -1) {
			len += 2;
		} else if (chr.indexOf("%") != -1) {
			len += chr.length / 3;
		}
	}
	
	return len;	
}


/***************************************************************
 * 전화번호 형식 표시
 * 
 * @param 전화번호
 * @return 변형된 전화번호
****************************************************************/
function cfPhoneFormat(num){
	return num.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
}


/***************************************************************
 * 전화번호 마스킹 표시
 * 
 * @param 전화번호
 * @return 마스킹 처리된 전화번호
****************************************************************/
function cfPhoneFormatMask(num){
	return num.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-****-$3");
}


/***************************************************************
 * 전화번호 형식 체크
 * 
 * @param 전화번호
 * @return boolean
****************************************************************/
//var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})?[0-9]{3,4}-?[0-9]{4}$/;
var regExp = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/;
function cfCheckPhoneNumber(txt) {

	if (regExp.test(txt+''))
	{
		return true;
	}
	return false;
}

/***************************************************************
 * 영문/숫자만 존재하는지 체크
 * @param str
 * @param errMessage
 ****************************************************************/
function cfCheckOnlyEngWithNumber(str) {
	
	for(var i = 0; i < str.length; i++) {
		var chk = str.substring(i,i+1); 
		if(!chk.match(/[0-9]|[a-z]|[A-Z]/)) {
			return false;
		} 
	}
	
	return true;
}

/***************************************************************
 * 숫자만 존재하는지 체크
 * @param str
 * @param errMessage
 ****************************************************************/
function cfCheckOnlyNumber(str) {
	
	for(var i = 0; i < str.length; i++) {
		var chk = str.substring(i,i+1); 
		if(!chk.match(/[0-9]/)) {
			return false;
		} 
	}
	
	return true;
}

/***************************************************************
 * 화면을 회색 레이어로 블록 처리하는 메소드
 * 
 * @param boolean isLoading : 로딩 이미지를 띄울지 여부(true/false)
 * @return void
****************************************************************/
function cfShowBlock(isLoading) {
	
	if(isLoading) {
	
		var loadingDiv = "<img id=\"loadingImg\" src=\"/images/loading/bigWaiting.gif\" width=\"32\" height=\"32\" style=\"display:none\" />";
		$(loadingDiv).appendTo('body');
		
		$.blockUI({message: $('#loadingImg'),
            css: {
                top:  ($(window).height() - 32) /2 + 'px',
                left: ($(window).width() - 32) /2 + 'px',
                width: '32px'
            }}
        );
		
	} else {
		
		$.blockUI({message: null});
	
	}
	
}

/***************************************************************
 * 화면을 회색 레이어로 블록 처리하는 메소드
 * 
 * @param void
 * @return void
****************************************************************/
function cfHideBlock() {
	$.unblockUI();
}

/***************************************************************
 * 페이징 처리된 HTML 생성 함수
 * 
 * @param 전체게시글수, 현재페이지번호, 한페이지게시글수, ajax처리함수
 * @return html
****************************************************************/
function cfGetPagingHtml(totalCnt, pageNo, pageSize, getListFncName) {

	var htmlStr = "";       //반환할 페이징 처리 html
	
	var iTotalCnt = parseInt(totalCnt + "");
	var iPageNo = parseInt(pageNo + "");
	var iPageSize = parseInt(pageSize + "");
	
	var displayPageCnt = 10; //화면에 보여줄 페이지 번호 개수(<<1 2 3 4 5 6 7 8 9 10 >> 일때 10개)
	var displayPageBlock = 0; //현재 페이지 블럭의 첫번째 페이지 번호 1~10=1, 11~20=11, 21~30=21
	var totalPageCnt = 0;   //전체 페이지 수
				
	//전체 페이지 수 계산
	totalPageCnt = Math.floor(iTotalCnt / iPageSize);
	if((iTotalCnt % iPageSize) > 0) {
		totalPageCnt++;
	}
	
	// 첫번째 블럭 계산
	displayPageBlock = Math.floor(((iPageNo-1)/displayPageCnt)) * displayPageCnt + 1;
	
	if(displayPageBlock > 1) {
		htmlStr += "<a href=\"javascript:" + getListFncName + "(" + (displayPageBlock - displayPageCnt)  + ");\">◁</a>";
	}
	
	htmlStr += "<span>";		
	var i = 1;
	while(i <= displayPageCnt && displayPageBlock <= totalPageCnt) {
		if(displayPageBlock == iPageNo) {
			htmlStr += displayPageBlock;
		}else{
			htmlStr += " <a href=\"javascript:" + getListFncName + "("+(displayPageBlock)+");\">"+ displayPageBlock + "</a> ";
		}
		i++;
		displayPageBlock++;
	}	
	htmlStr += "</span>";
	
	if(displayPageBlock < totalPageCnt) {
		htmlStr += "<a href=\"javascript:" + getListFncName + "(" + displayPageBlock + ");\">▷</a>";
	}
	
	return htmlStr;
}

/***************************************************************
 * Date format 메소드
 * 
 * @param void
 * @return 날짜형식의 문자열
****************************************************************/
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";

    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;

    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};


