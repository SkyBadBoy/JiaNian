package wtb.core.model;

import java.util.Date;


public class VoteRecords {
	public VoteRecords() {
		super();
	}
	private long ID;
	private String PKID;
	private String UnionID;
	private long NoticeID;
	private String CreateTime;
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
	public String getUnionID() {
		return UnionID;
	}
	public void setUnionID(String unionID) {
		UnionID = unionID;
	}
	public long getNoticeID() {
		return NoticeID;
	}
	public void setNoticeID(long noticeID) {
		NoticeID = noticeID;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
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
