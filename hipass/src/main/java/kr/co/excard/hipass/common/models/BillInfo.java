package kr.co.excard.hipass.common.models;


public class BillInfo extends BaseModel {
	
	private String regionName;
	private String tgTel;
	private String tgAccount;
	private String payDate;
	private String tgName;
	private Integer carType;
	private String billAmount;
	private String cardName;
	private String cardNumber;
	private String txSerial;
	
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getTgTel() {
		return tgTel;
	}
	public void setTgTel(String tgTel) {
		this.tgTel = tgTel;
	}
	public String getTgAccount() {
		return tgAccount;
	}
	public void setTgAccount(String tgAccount) {
		this.tgAccount = tgAccount;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getTgName() {
		return tgName;
	}
	public void setTgName(String tgName) {
		this.tgName = tgName;
	}

	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getTxSerial() {
		return txSerial;
	}
	public void setTxSerial(String txSerial) {
		this.txSerial = txSerial;
	}
	

	
}
