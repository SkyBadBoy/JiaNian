package wtb.core.model;

import java.util.Date;

public class WeChatGroupPart {
	public WeChatGroupPart() {
		super();
	}
	private long ID	;
	private String PKID;
	private long WeChatID	;
	private long GroupID	;
	private Date CreateTime	;		
	private Date ModifyTime	;		;
	private String Memo;
	private int Status	;
	private WeChatPublic WeChat	;
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
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}
	public long getGroupID() {
		return GroupID;
	}
	public void setGroupID(long groupID) {
		GroupID = groupID;
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
	public WeChatPublic getWeChat() {
		return WeChat==null?new WeChatPublic():WeChat;
	}
	public void setWeChat(WeChatPublic weChat) {
		WeChat = weChat;
	}
	
	
	
}
