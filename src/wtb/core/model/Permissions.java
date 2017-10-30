package wtb.core.model;

import java.util.Date;

public class Permissions {
	public Permissions() {
		super();
	}
	private long ID;
	private String PKID;
	private String PowerCode;
	private long WeChatGroupID;
	private String Memo;
	private Date CreateTime	;	
	private Date ModifyTime	;	
	private int Status	;
	private String AreaID;
	private WeChatGroup WeChatGroup;

	public String getAreaID() {
		return AreaID;
	}

	public void setAreaID(String areaID) {
		AreaID = areaID;
	}

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

	public String getPowerCode() {
		return PowerCode;
	}

	public void setPowerCode(String powerCode) {
		PowerCode = powerCode;
	}

	public long getWeChatGroupID() {
		return WeChatGroupID;
	}

	public void setWeChatGroupID(long weChatGroupID) {
		WeChatGroupID = weChatGroupID;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
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

	public WeChatGroup getWeChatGroup() {
		return WeChatGroup==null?new WeChatGroup():WeChatGroup;
	}

	public void setWeChatGroup(WeChatGroup weChatGroup) {
		WeChatGroup = weChatGroup;
	}
	
	
	
}
