package wtb.core.model;

import java.util.Date;

import org.activiti.engine.identity.Picture;

public class WeChatGroup {
	public WeChatGroup() {
		super();
	}
	private long ID	;
	private String PKID;
	private long WeChatID	;
	private String Title	;
	private String Code		;
	private String Industry	;
	private Date CreateTime	;		
	private Date ModifyTime	;		
	private String Memo;
	private long BannerURLID;
	private long QRCodeURLID;
	private int Status	;
	private int WeChatCounter;
	private String AreaID;
	
	private Region Area;
	private BaseInfo BaseInfo;
	private Pictures BannerURL;
	private Pictures QRCodeURL;
	
	public Region getArea() {
		return Area==null?new Region():Area;
	}
	public void setArea(Region area) {
		Area = area;
	}
	public String getAreaID() {
		return AreaID;
	}
	public void setAreaID(String areaID) {
		AreaID = areaID;
	}
	public int getWeChatCounter() {
		return WeChatCounter;
	}
	public void setWeChatCounter(int weChatCounter) {
		WeChatCounter = weChatCounter;
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
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getIndustry() {
		return Industry;
	}
	public void setIndustry(String industry) {
		Industry = industry;
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
	public long getBannerURLID() {
		return BannerURLID;
	}
	public void setBannerURLID(long bannerURLID) {
		BannerURLID = bannerURLID;
	}
	public long getQRCodeURLID() {
		return QRCodeURLID;
	}
	public void setQRCodeURLID(long qRCodeURLID) {
		QRCodeURLID = qRCodeURLID;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo==null?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public Pictures getBannerURL() {
		return BannerURL==null?new Pictures():BannerURL;
	}
	public void setBannerURL(Pictures bannerURL) {
		BannerURL = bannerURL;
	}
	public Pictures getQRCodeURL() {
		return QRCodeURL==null?new Pictures():QRCodeURL;
	}
	public void setQRCodeURL(Pictures qRCodeURL) {
		QRCodeURL = qRCodeURL;
	}
	

	
}
