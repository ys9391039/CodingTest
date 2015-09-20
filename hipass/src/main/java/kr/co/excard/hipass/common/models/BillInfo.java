package kr.co.excard.hipass.common.models;


public class BillInfo extends BaseModel {
	
	private String serviceName;
	private String regionName;
	private String tgTel;
	private String tgAccount;
	private String payDate;
	private String tgName;
	private String billAmount;
	private String tgType1;
	private String tgType2;
	private Integer carType;
	private String billAmount1;
	private String billAmount2;
	private String totalAmount;
	private String taxAmount;
	private String cardName;
	private String cardNumber;
	private String txSerial;
	
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
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
	public String getTgType1() {
		return tgType1;
	}
	public void setTgType1(String tgType1) {
		this.tgType1 = tgType1;
	}
	public String getTgType2() {
		return tgType2;
	}
	public void setTgType2(String tgType2) {
		this.tgType2 = tgType2;
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
	public String getBillAmount1() {
		return billAmount1;
	}
	public void setBillAmount1(String billAmount1) {
		this.billAmount1 = billAmount1;
	}
	public String getBillAmount2() {
		return billAmount2;
	}
	public void setBillAmount2(String billAmount2) {
		this.billAmount2 = billAmount2;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
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
