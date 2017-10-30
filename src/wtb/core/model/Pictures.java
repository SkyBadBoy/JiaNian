package wtb.core.model;

import java.util.Date;

public class Pictures implements java.io.Serializable{
	public Pictures() {
		super();
	}
	private long ID;
	private String PKID;
	private String Url;
	private String RealUrl;
	private String Memo;
	private long Status;
	private long WeChatID	;	
	private Date ModifyTime	;	
	private Date CreateTime	;
	
	private BaseInfo BaseInfo;
	private String ImageURL;

	
	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
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

	public long getStatus() {
		return Status;
	}

	public void setStatus(long state) {
		Status = state;
	}

	public long getWeChatID() {
		return WeChatID;
	}

	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
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
	
	
	
}
