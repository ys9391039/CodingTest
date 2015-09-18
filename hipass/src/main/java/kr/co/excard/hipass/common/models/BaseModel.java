package kr.co.excard.hipass.common.models;

public class BaseModel {
	private int start = 1; // sql limit문의 시작 번호 (pageNo와 listSize로 자동 계산)
	private int listSize = 0; // 화면의 리스트 갯수  sql limit문의 끝 번호
	private int totalCount = 0;
	
	public void setPosition(int pageNo, int listSize){
		this.start = (pageNo - 1)  * listSize;
		this.listSize = listSize;
	}
	
	public int getStart() {
		return start;
	}
	
	public  int getListSize() {
		return listSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}	
}