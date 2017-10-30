package wtb.core.model;

public class OnlineCount {
	private String ID;
	private String CreateTime;
	private int Type;
	private long Count;
	private long NewUserCount;
	private long AccessUserCount;
	private String ModifyTime;
	private int Status;
	private String Memo;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public long getCount() {
		return Count;
	}
	public void setCount(long count) {
		Count = count;
	}
	public long getNewUserCount() {
		return NewUserCount;
	}
	public void setNewUserCount(long newUserCount) {
		NewUserCount = newUserCount;
	}
	public long getAccessUserCount() {
		return AccessUserCount;
	}
	public void setAccessUserCount(long accessUserCount) {
		AccessUserCount = accessUserCount;
	}
	public String getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(String modifyTime) {
		ModifyTime = modifyTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	
	

}
