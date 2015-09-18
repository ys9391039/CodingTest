function getByteLength(obj) {
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

function phone_format(num){
	return num.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
}

function phone_format_mask(num){
	return num.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-****-$3");
}


//var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})?[0-9]{3,4}-?[0-9]{4}$/;
var regExp = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/;

function checkPhoneNumber(txt) {

	if (regExp.test(txt+''))
	{
		return true;
	}
	return false;
}

/**
 * 영문만 존재하는지 체크
 * @param str
 * @param errMessage
 */
function checkOnlyEngWithNumber(str) {
	
	for(var i = 0; i < str.length; i++) {
		var chk = str.substring(i,i+1); 
		if(!chk.match(/[0-9]|[a-z]|[A-Z]/)) {
			return false;
		} 
	}
	
	return true;
}


