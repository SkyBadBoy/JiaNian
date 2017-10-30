package wtb.core.model;

public class PayRecord {
	/*
	 * PayRecord_ID
PayRecord_UserID
PayRecord_OrderID
PayRecord_PayMethod
PayRecord_PayReason
PayRecord_Money
PayRecord_Tarde
PayRecord_CreateTime
PayRecord_ModifyTime
PayRecord_Version
PayRecord_Status
PayRecord_Deleted
PayRecord_BeLongID

	 * 
	 * */
	private long ID ;
	private String PKID;
	private String UserID;
	private String OrderID;
	private String PayMethod;
	private String PayReason;
	private String Money;
	private String CreateTime;
	private String ModifyTime;
	private String Version;
	private String Tarde;
	private int Status;
	private int Deleted;
	private String BeLongID;
	private int Type;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getPayMethod() {
		return PayMethod;
	}
	public void setPayMethod(String payMethod) {
		PayMethod = payMethod;
	}
	public String getPayReason() {
		return PayReason;
	}
	public void setPayReason(String payReason) {
		PayReason = payReason;
	}
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(String modifyTime) {
		ModifyTime = modifyTime;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getDeleted() {
		return Deleted;
	}
	public void setDeleted(int deleted) {
		Deleted = deleted;
	}
	public String getBeLongID() {
		return BeLongID;
	}
	public void setBeLongID(String beLongID) {
		BeLongID = beLongID;
	}
	public String getTarde() {
		return Tarde;
	}
	public void setTarde(String tarde) {
		Tarde = tarde;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	
	
}
