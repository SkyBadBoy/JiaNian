package wtb.core.model;

public class WeChatCustom
{
	private long ID;
	private String PKID;  
	private String Name;
	private String Type;
	private String URL;
	private String Key;
	private long ParentID;
	private String CreateTime; 
	private int Status;
	private Long mGroup; 

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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public long getParentID() {
		return ParentID;
	}
	public void setParentID(long parentID) {
		ParentID = parentID;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public Long getmGroup() {
		return mGroup;
	}
	public void setmGroup(Long mGroup) {
		this.mGroup = mGroup;
	}
	

	
}
