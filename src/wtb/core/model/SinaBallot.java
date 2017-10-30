package wtb.core.model;

import java.util.Date;


public class SinaBallot {
	public SinaBallot() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private String Memo;
	private long EssayID;
	private Date CreateTime;
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
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public long getEssayID() {
		return EssayID;
	}
	public void setEssayID(long essayID) {
		EssayID = essayID;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
	
	
	
	
}
