package wtb.core.model;

import java.util.Date;

public class WeChatBanner {

	public WeChatBanner() {
		super();
	}
	private long ID	;
	private String PKID;
	private String AreaID	;
	private String Title	;
	private Date CreateTime	;		
	private Date ModifyTime	;		
	private int Year	;
	private int Month;
	private int Status			;
	private String URL		;
	private String hasUsed;
	private long QRCodeURLID;
	private long WeChatID;
	
	private WeChatPublic WeChat;
	private Pictures QRCodeURL;
	private BaseInfo BaseInfo;
	private Region Area;
	
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
	
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getHasUsed() {
		return hasUsed;
	}
	public void setHasUsed(String hasUsed) {
		this.hasUsed = hasUsed;
	}
	public long getQRCodeURLID() {
		return QRCodeURLID;
	}
	public void setQRCodeURLID(long qRCodeURLID) {
		QRCodeURLID = qRCodeURLID;
	}
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}
	public WeChatPublic getWeChat() {
		return WeChat==null?new WeChatPublic():WeChat;
	}
	public void setWeChat(WeChatPublic weChat) {
		WeChat = weChat;
	}
	public Pictures getQRCodeURL() {
		return QRCodeURL==null?new Pictures():QRCodeURL;
	}
	public void setQRCodeURL(Pictures qRCodeURL) {
		QRCodeURL = qRCodeURL;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo==null?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public String getAreaID() {
		return AreaID;
	}
	public void setAreaID(String areaID) {
		AreaID = areaID;
	}
	public Region getArea() {
		return Area==null?new Region():Area;
	}
	public void setArea(Region area) {
		Area = area;
	}
	
	
	
}
