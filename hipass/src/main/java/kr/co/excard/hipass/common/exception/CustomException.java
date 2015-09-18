package kr.co.excard.hipass.common.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private int errorCode; //에러 코드
	private String errorMsg; //에러 메시지
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public CustomException(int errorCode, String errorMsg) {
		super(errorMsg);
		
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public CustomException(int errorCode) {
		this.errorCode = errorCode;
	}
		
}
