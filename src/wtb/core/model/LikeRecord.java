package wtb.core.model;

import java.util.Date;


public class LikeRecord {
	public LikeRecord() {
		super();
	}

	private long ID;
	private String PKID;
	private Long NoticesID;
	private String Memo;
	private String UserID;
	private int Deleted;
	private Date CreateTime;
	private Date ModifyTime;
	private int Status;
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
	public Long getNoticesID() {
		return NoticesID;
	}
	public void setNoticesID(Long noticesID) {
		NoticesID = noticesID;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public int getDeleted() {
		return Deleted;
	}
	public void setDeleted(int deleted) {
		Deleted = deleted;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Date getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		ModifyTime = modifyTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
	
	
}
