package kr.co.excard.hipass.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.math.BigInteger;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;


public class StringUtil {
	
	/**
	 * @param str
	 * @return
	 */
	public static String nullCheck(String str)
	{
		return((str == null || str.equals("null") || str.length() == 0) ? "" : str.trim());
	}
	
	/**
	 * @param request
	 * @param param
	 * @return
	 */
	public static String reqNullCheck(HttpServletRequest request, String param)
	{
		return nullCheck(request.getParameter(param));
	}
	
	/**
	 * 한글 파라미터 처리
	 * @param req
	 * @param paramKey
	 * @return
	 */
	public static String reqNullCheckHangulUTF8(HttpServletRequest req, String paramKey) {

		String searchText = reqNullCheck(req, paramKey);
		String result = "";
		/* set searhText */
		try {
			if(searchText != null) {
				
				//searchText = java.net.URLDecoder.decode(searchText);
				if(false == containsHangul(searchText) ) {
					/* 한글이 포함되지 않은 경우 */
					result = new String(searchText.getBytes("8859_1"),"utf-8");
				} else {
					result = searchText;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 문자열에서 한글 존재여부 확인
	 * @param str
	 * @return
	 */
	public static boolean containsHangul(String str)
	{
	    for(int i = 0 ; i < str.length() ; i++)
	    {
	        char ch = str.charAt(i);
	        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
	        if(UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) ||
	                UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock) ||
	                UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock))
	            return true;
	    }
	    return false;
	}
	
	/**
	 * @param request
	 * @param string
	 * @return
	 */
	public static int reqNullCheckIntVal(HttpServletRequest request, String string) 
	{
		int intVal = 0;
		String str = reqNullCheck(request, string);

		try {
			intVal = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}

		return intVal;
	}
	
	/**
	 * @param request
	 * @param string
	 * @return
	 */
	public static BigInteger reqNullCheckBigIntVal(HttpServletRequest request, String string) 
	{
		BigInteger bigIntVal = BigInteger.ZERO;
		String str = reqNullCheck(request, string);

		try {
			bigIntVal = new BigInteger(str);
		} catch (NumberFormatException e) {
			return BigInteger.ZERO;
		}

		return bigIntVal;
	}
	
    /**
     * 문자열이 비어 있지 않은지 확인.
     * 
     * @param str
     *            원본 문자열.
     * @return true if the String is non-null, and not length zero
     */
    public static boolean isNotEmpty(String str) {
        return ( str != null && str.length() > 0 );
    }
    
    /**
     * Checks if is empty.
     * 
     * @param str
     *            the str
     * @return true, if is empty
     */
    public static boolean isEmpty(String str) {
        return ( str == null || str.length() == 0 );
    }
    
    // 가격 포멧
	public static String formatPrice(long price) {
		NumberFormat fmt = NumberFormat.getInstance();
		return fmt.format(price);
	}
	
	//날짜(시간) 구분자 상수정의
	private static final String[][] div = {
		{".","."," ",".","."},
		{"/","/","",":",":"},
		{"년 ","월 ","일","시 ","분 "},
		{"-","-","",":",":"}
	};
	
	/**
	 * yyyymmddHHMMDD : 년월일시분초에 대한 String을 구분자로 표기하여 가져온다.
	 * @param String dt          : yyyymmddHHMMDD(년월일시분초) 
	 * @param int divNo          : 표기 구분값(0,1,2)
	 * @param boolean isViewTime : 시간 표시여부
	 * @return String            : 구분자를 추가한 날짜
	*/
	public static String getDivDate(String dt, int divNo, boolean isViewTime) {
		
		if(dt == null || dt == "") {
			return "";
		}
		
		StringBuilder result = new StringBuilder();
		
		String year = "";     //년
		String month = "";    //월
		String day = "";      //일
		String hour = "";     //시
		String minute = "";   //분
		String second = "";   //초

		year = dt.substring(0, 4);
		month = dt.substring(4, 6);
		
		if(dt.length() != 8) {
			day = dt.substring(6, 8);
		} else {
			day = dt.substring(6);
		}
		
		if(dt.length() == 14) {
			hour = dt.substring(8, 10);
			minute = dt.substring(10, 12);
			second = dt.substring(13);
		}
		
		result.append(year + div[divNo][0]);
		result.append(month + div[divNo][1]);
		result.append(day + div[divNo][2]);
			
		if(isViewTime) {
			result.append(" " + hour + div[divNo][3]);
			result.append(minute);
		}
		
		return result.toString();
	}
    
}