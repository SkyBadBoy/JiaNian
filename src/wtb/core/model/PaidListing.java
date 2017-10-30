package wtb.core.model;

import java.util.Date;

/**
 * 竞价排名
 * @author SASR_Studio
 *
 */
public class PaidListing {
	public PaidListing() {
		super();
	}
	private long ID;
	private String PKID;
	private Date CreateTime;
	private Date ModifyTime;
	private int Status;
	private String IPAddress;
	private long WeChatID;
	private int Year;
	private int Month;
	private int IsTop;
	private String IsTopResult;
	private float Amount;
	private String AreaID;
	private WeChatPublic WeChat;
	private Region Area;
	private BaseInfo BaseInfo;
	
	
	public String getIsTopResult() {
		return IsTopResult;
	}
	public void setIsTopResult(String isTopResult) {
		IsTopResult = isTopResult;
	}
	public Region getArea() {
		return Area==null?new Region():Area;
	}
	public void setArea(Region area) {
		Area = area;
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
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public WeChatPublic getWeChat() {
		return WeChat==null?new WeChatPublic():WeChat;
	}
	public void setWeChat(WeChatPublic weChat) {
		WeChat = weChat;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
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
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
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
	public int getIsTop() {
		return IsTop;
	}
	public void setIsTop(int isTop) {
		IsTop = isTop;
	}
	public float getAmount() {
		return Amount;
	}
	public void setAmount(float amount) {
		Amount = amount;
	}
	
}
