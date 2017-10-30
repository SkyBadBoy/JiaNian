package wtb.core.model;

public class Version {
	public long ID;
	public String PKID;
	public int ClientType;//客户端类型
	public String Number;//客户端版本
	public String ReleaseTime;
	public String CreateTime;
	public String ModifyTime;
	public int Status;
	public int Deleted;
	public String FilePath;
	public int UpdateType;//升级类型 0 不升级   1升级   2强制升级
	public String Content;
	public String Build;//构建版本
	public String UpdateTypeMemo;//构建版本
	public String ClientTypeMemo;//构建版本
	
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
	public int getClientType() {
		return ClientType;
	}
	public void setClientType(int clientType) {
		ClientType = clientType;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getReleaseTime() {
		return ReleaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		ReleaseTime = releaseTime;
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
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getDeleted() {
		return Deleted;
	}
	public void setDeleted(int deleted) {
		Deleted = deleted;
	}
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public int getUpdateType() {
		return UpdateType;
	}
	public void setUpdateType(int updateType) {
		UpdateType = updateType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getBuild() {
		return Build;
	}
	public void setBuild(String build) {
		Build = build;
	}
	public String getUpdateTypeMemo() {
		return UpdateTypeMemo;
	}
	public void setUpdateTypeMemo(String updateTypeMemo) {
		UpdateTypeMemo = updateTypeMemo;
	}
	public String getClientTypeMemo() {
		return ClientTypeMemo;
	}
	public void setClientTypeMemo(String clientTypeMemo) {
		ClientTypeMemo = clientTypeMemo;
	}
	
	
}
