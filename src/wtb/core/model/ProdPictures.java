package wtb.core.model;

import java.util.Date;

public class ProdPictures implements java.io.Serializable{
	
	public ProdPictures() {
		super();
	}
	private long ID;
	private String PKID;
	private String Url;
	private String RealUrl;
	private String Memo;
	private int Status;
	private long ProductID	;	
	private Date ModifyTime	;	
	private Date CreateTime	;
	private int Type;
	private int IsEnable;
	private String IsEnables;
	private long WeChatID;
	private String tempUrl;
	private BaseInfo BaseInfo;
	private String PUrl;
	
	private int ResoType;
	private long ResourceID;
	
	private String Title;
	
	public String getTempUrl() {
		return tempUrl;
	}

	public void setTempUrl(String tempUrl) {
		this.tempUrl = tempUrl;
	}
	
	public long getWeChatID() {
		return WeChatID;
	}

	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public BaseInfo getBaseInfo() {
		return BaseInfo==null?new BaseInfo():BaseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}

	public String getPKID() {
		return PKID;
	}

	public void setPKID(String pKID) {
		PKID = pKID;
	}
	private WeChatPublicSimple WeChat;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getRealUrl() {
		return RealUrl;
	}

	public void setRealUrl(String realUrl) {
		RealUrl = realUrl;
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

	public void setStatus(int state) {
		Status = state;
	}

	public long getProductID() {
		return ProductID;
	}

	public void setProductID(long productID) {
		ProductID = productID;
	}

	public Date getModifyTime() {
		return ModifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		ModifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public WeChatPublicSimple getWeChat() {
		return WeChat==null?new WeChatPublicSimple():WeChat;
	}

	public void setWeChat(WeChatPublicSimple weChat) {
		WeChat = weChat;
	}

	public int getIsEnable() {
		return IsEnable;
	}

	public void setIsEnable(int isEnable) {
		IsEnable = isEnable;
	}

	public String getIsEnables() {
		return IsEnables;
	}

	public void setIsEnables(String isEnables) {
		IsEnables = isEnables;
	}

	public String getPUrl() {
		return PUrl;
	}

	public void setPUrl(String pUrl) {
		PUrl = pUrl;
	}

	public int getResoType() {
		return ResoType;
	}

	public void setResoType(int resoType) {
		ResoType = resoType;
	}

	public long getResourceID() {
		return ResourceID;
	}

	public void setResourceID(long resourceID) {
		ResourceID = resourceID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
	
	
	
}
