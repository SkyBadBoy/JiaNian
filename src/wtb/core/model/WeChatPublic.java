package wtb.core.model;

import java.util.*;

import org.activiti.engine.identity.Picture;
/**
 * WeChatBanner
 */
public class WeChatPublic {
	
	private long ID	;
	private String PKID;
	private String WeChat	;
	private String Company	;
	private String Content	;
	private String CreateTime	;	
	private String ModifyTime	;	
	private String AddressStr	;	
	/**
	 * 二维码
	 */
	private long QRCodeURLID;	
	/**
	 * 状态说明 1表示正常 2表示已被加盟 0表示删除
	 * @return
	 */
	private int Status	;
	/**
	 * 个人主页
	 */
	private String WebSiteURL	; 
	/**
	 * logo图片
	 */
	private Long LogoID;
	/**
	 * 帐号主体
	 */
	private String ContactPerson;
	/**
	 * 帐号主题手机号
	 */
	private String ContactPhone;
	/**
	 * 组织机构吗
	 */
	private String Organizational;
	/**
	 * 营业许可号
	 */
	private String BusinessLicense;
	/**
	 * 绑定邮箱
	 */
	private String EMail;
	/**
	 * 公众号类型 1订阅号 2服务号 3企业号
	 */
	private int PublicType;
	/**
	 * 注册主题 1政府 2媒体 3企业 4其他组织 5个人
	 */
	private int RegisterSubject;
	/**
	 * 组织机构全称
	 */
	private String OrgName;
	/**
	 * 广告图片
	 */
	private Long BannerID;
	
	private String Memo;
	/*
	 * 详细地址
	 */
	private String Address;
	/**
	 * 纬度
	 */
	private String Lng;
	/**
	 * 经度
	 */
	private String Lat;
	/**
	 * 所在区
	 */
	private String AreaID;
	/**
	 * 所在城市
	 */
	private Region Area;
	/**
	 * 权重,用于排序
	 */
	private long Weight;
	
	private long ClickCount;
	private long Praise;
	
	private int Bind;
	private String IsBind;
	
	private BaseInfo BaseInfo	;
	private BaseInfo PublicTypeInfo;
	private BaseInfo RegisterSubjectInfo;
	private ProdPictures Banner;
	private ProdPictures Logo;
	private ProdPictures QRCodeURL;
	
	private long NoticeCount;
	private long StudentCount;
	public long getClickCount() {
		return ClickCount;
	}
	public void setClickCount(long clickCount) {
		ClickCount = clickCount;
	}
	public long getPraise() {
		return Praise;
	}
	public void setPraise(long praise) {
		Praise = praise;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getLng() {
		return Lng;
	}
	public void setLng(String lng) {
		Lng = lng;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	
	
	public String getAreaID() {
		return AreaID;
	}
	public void setAreaID(String areaID) {
		AreaID = areaID;
	}
	public Region getArea() {
		return Area;
	}
	public void setArea(Region area) {
		Area = area;
	}
	public long getWeight() {
		return Weight;
	}
	public void setWeight(long weight) {
		Weight = weight;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
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
	public String getWeChat() {
		return WeChat;
	}
	public void setWeChat(String weChat) {
		WeChat = weChat;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
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
	public String getWebSiteURL() {
		return WebSiteURL;
	}
	public void setWebSiteURL(String webSiteURL) {
		WebSiteURL = webSiteURL;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public String getContactPhone() {
		return ContactPhone;
	}
	public void setContactPhone(String contactPhone) {
		ContactPhone = contactPhone;
	}
	public String getOrganizational() {
		return Organizational;
	}
	public void setOrganizational(String organizational) {
		Organizational = organizational;
	}
	public String getBusinessLicense() {
		return BusinessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		BusinessLicense = businessLicense;
	}
	public String getEMail() {
		return EMail;
	}
	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public int getPublicType() {
		return PublicType;
	}
	public void setPublicType(int publicType) {
		PublicType = publicType;
	}
	public int getRegisterSubject() {
		return RegisterSubject;
	}
	public void setRegisterSubject(int registerSubject) {
		RegisterSubject = registerSubject;
	}
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	public Long getBannerID() {
		return BannerID;
	}
	public void setBannerID(Long bannerID) {
		BannerID = bannerID;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo==null?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public ProdPictures getBanner() {
		return Banner==null?new ProdPictures():Banner;
	}
	public void setBanner(ProdPictures banner) {
		Banner = banner;
	}
	public BaseInfo getPublicTypeInfo() {
		return PublicTypeInfo==null?new BaseInfo():PublicTypeInfo;
	}
	public void setPublicTypeInfo(BaseInfo publicTypeInfo) {
		PublicTypeInfo = publicTypeInfo;
	}
	public BaseInfo getRegisterSubjectInfo() {
		return RegisterSubjectInfo==null?new BaseInfo():RegisterSubjectInfo;
	}
	public void setRegisterSubjectInfo(BaseInfo registerSubjectInfo) {
		RegisterSubjectInfo = registerSubjectInfo;
	}
	public Long getLogoID() {
		return LogoID;
	}
	public void setLogoID(Long logoID) {
		LogoID = logoID;
	}
	public ProdPictures getLogo() {
		return Logo==null?new ProdPictures():Logo;
	}
	public void setLogo(ProdPictures logo) {
		Logo = logo;
	}
	public ProdPictures getQRCodeURL() {
		return QRCodeURL==null?new ProdPictures():QRCodeURL;
	}
	public void setQRCodeURL(ProdPictures qRCodeURL) {
		QRCodeURL = qRCodeURL;
	}
	public int getBind() {
		return Bind;
	}
	public void setBind(int bind) {
		Bind = bind;
	}
	public String getIsBind() {
		return IsBind;
	}
	public void setIsBind(String isBind) {
		IsBind = isBind;
	}
	public long getNoticeCount() {
		return NoticeCount;
	}
	public void setNoticeCount(long noticeCount) {
		NoticeCount = noticeCount;
	}
	public long getStudentCount() {
		return StudentCount;
	}
	public void setStudentCount(long studentCount) {
		StudentCount = studentCount;
	}
	public String getAddressStr() {
		return AddressStr;
	}
	public void setAddressStr(String addressStr) {
		AddressStr = addressStr;
	}	
	
	
	
	
	
	
}
