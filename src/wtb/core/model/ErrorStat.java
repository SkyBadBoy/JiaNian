package wtb.core.model;

public class ErrorStat {
	private long ID;
	private String PKID;
	private String ClassName;
	private String Memo;
	private int Status;
	private String CreateTime;
	private String ModifyTime;
	private int Count;
	private int Type;
	private String SendTime;
	private int TotalCount;
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
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
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
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getSendTime() {
		return SendTime;
	}
	public void setSendTime(String sendTime) {
		SendTime = sendTime;
	}
	public int getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}
	
}
