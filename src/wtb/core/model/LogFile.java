
/**
 * @Author 作者：马健
 * @Phone  联系qq：1039510196
 * @CreateTime 创建时间：2017年6月13日 上午11:51:30
 * @Details
 */
package wtb.core.model;

/**
 * @author Administrator
 *
 */
public class LogFile {
	
	private long ID;  
	private String PKID;
	private String CreateTime;
	private String ModifyTime;
	private int Status;
	private int Type;
	private String Model;
	private int DeviceType;
	private String Url;
	private String Version;
	private String System;
	private String UDID;
	private String DownUrl;
	private String DeviceTypeMemo;
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
	public int getDeviceType() {
		return DeviceType;
	}
	public void setDeviceType(int deviceType) {
		DeviceType = deviceType;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
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
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getDownUrl() {
		return DownUrl;
	}
	public void setDownUrl(String downUrl) {
		DownUrl = downUrl;
	}
	public String getDeviceTypeMemo() {
		return DeviceTypeMemo;
	}
	public void setDeviceTypeMemo(String deviceTypeMemo) {
		DeviceTypeMemo = deviceTypeMemo;
	}
	
	
}
