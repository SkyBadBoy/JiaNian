package wtb.core.model;

import java.util.Date;


public class ClickList {
	public ClickList() {
		super();
	}
	private long ID;
	private String PKID;
	private String IPAddress;
	private long BeLongID;
	private int Type;
	private Date CreateTime;
	private Date ModifyTime;
	private int Status;
	private String MacAddress;
	
	
	public String getMacAddress() {
		return MacAddress;
	}
	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public long getBeLongID() {
		return BeLongID;
	}
	public void setBeLongID(long beLongID) {
		BeLongID = beLongID;
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
	
	
	
	
	
}
