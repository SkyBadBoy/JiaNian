package wtb.core.model;

import java.util.Date;
import java.util.List;


public class WeChatBind {
	public WeChatBind() {
		super();
	}
	private long ID;
	private String PKID;
	private String OpenID;
	private String UnionID;
	private long StudentID;
	private int Type;
	private Date CreateTime;
	private Date ModifyTime;
	private int Status;
	private String Memo;
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
	public String getOpenID() {
		return OpenID;
	}
	public void setOpenID(String openID) {
		OpenID = openID;
	}
	public String getUnionID() {
		return UnionID;
	}
	public void setUnionID(String unionID) {
		UnionID = unionID;
	}
	public long getStudentID() {
		return StudentID;
	}
	public void setStudentID(long studentID) {
		StudentID = studentID;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
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
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	
		
}
