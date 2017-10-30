
/**
 * @Author 作者：马健
 * @Phone  联系qq：1039510196
 * @CreateTime 创建时间：2017年6月13日 下午3:48:32
 * @Details
 */
package wtb.core.model;

/**
 * @author Administrator
 *
 */
public class Device {
	private long ID;
	private String PKID;
	private String Name;
	private long UserID;
	private String Version;
	private int IsUsing;
	private String CreateTime;
	private String ModifyTime;
	private int Status;
	private int Type;
	private String Model;
	private String System;
	private String UDID;
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
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public int getIsUsing() {
		return IsUsing;
	}
	public void setIsUsing(int isUsing) {
		IsUsing = isUsing;
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
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getSystem() {
		return System;
	}
	public void setSystem(String system) {
		System = system;
	}
	public String getUDID() {
		return UDID;
	}
	public void setUDID(String uDID) {
		UDID = uDID;
	}
	
	
	
	
}
