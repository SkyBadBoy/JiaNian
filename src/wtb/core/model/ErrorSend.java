package wtb.core.model;

public class ErrorSend {
	private long ID;
	private String PKID;
	private String Account;
	private String Phone;
	private String Message;
	private String Status;
	private String CreateTime;
	private String Count;
	private String IsStatus;
	private String TypeMemo;
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
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getIsStatus() {
		return IsStatus;
	}
	public void setIsStatus(String isStatus) {
		IsStatus = isStatus;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getTypeMemo() {
		return TypeMemo;
	}
	public void setTypeMemo(String typeMemo) {
		TypeMemo = typeMemo;
	}
	
	
}
