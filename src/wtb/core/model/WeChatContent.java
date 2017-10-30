package wtb.core.model;

public class WeChatContent 
{
	private long ID;
	private String PKID;
	private String Key;
	private String Content;
	private int Type;
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	private String CreateTime;
	private String Rules;
	private String TempRules;
	private String AppID;
	private String Status;
	private String Author;
	private String WID;
	private String MediaId;
	private BaseInfo BaseInfo;
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
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	public String getTempRules() {
		return TempRules;
	}
	public void setTempRules(String tempRules) {
		TempRules = tempRules;
	}
	public String getRules() {
		return Rules;
	}
	public void setRules(String rules) {
		Rules = rules;
	}
	public String getAppID() {
		return AppID;
	}
	public void setAppID(String appID) {
		AppID = appID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getWID() {
		return WID;
	}
	public void setWID(String wID) {
		WID = wID;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
