package wtb.core.model;

import java.util.Date;


public class WeChatUser {
	public WeChatUser() {
		super();
	}
	private long ID;
	private String PKID;
	private Date CreateTime;
	private int Status;
	private String OpenID;
	private String UnionID;
	private String NickName;
	private String Language;
	private String Sex;
	private String City;
	private String Province;
	private String HeadImgUrl;
	private String Privilege;
	private String Country;
	private int Flag;
	private String IpAddress;
	private String WeChat;
	
	
	public String getWeChat() {
		return WeChat;
	}
	public void setWeChat(String weChat) {
		WeChat = weChat;
	}
	public String getIpAddress() {
		return IpAddress;
	}
	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}
	public int getFlag() {
		return Flag;
	}
	public void setFlag(int flag) {
		Flag = flag;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
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
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getOpenID() {
		return OpenID;
	}
	public void setOpenID(String openID) {
		OpenID = openID;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getHeadImgUrl() {
		return HeadImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		HeadImgUrl = headImgUrl;
	}
	public String getPrivilege() {
		return Privilege;
	}
	public void setPrivilege(String privilege) {
		Privilege = privilege;
	}
	public String getUnionID() {
		return UnionID;
	}
	public void setUnionID(String unionID) {
		UnionID = unionID;
	}
	
	

	
	
	
	
	
}
