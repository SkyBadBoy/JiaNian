package wtb.core.model;

import java.util.Date;
import java.util.List;


public class Activity {
	public Activity() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private String Content;
	private long ImageID;
	private int Hot;
	private int Weight;
	private Date CreateTime;
	private Date ModifyTime;
	private int Status;
	private long WeChatID;
	private int Praise;
	private String EndTime;
	private String IsHot;
	
	private ProdPictures Image;
	private WeChatPublic WeChat;
	private BaseInfo BaseInfo;
	
	private String Sponsor;//���췽
	private String SBrief;//���췽���
	private String SImage;//���췽ͼƬ
	private ProdPictures SPicture;
	
	
	private int ApplyCount;
	private int ApplyLimit;
	private double ApplyMoney;
	private int PayType;
	
	private List<ProdPictures> Pictures;
	
	public String getIsHot() {
		return IsHot;
	}
	public void setIsHot(String isHot) {
		IsHot = isHot;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo==null?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}
	public int getPraise() {
		return Praise;
	}
	public void setPraise(int praise) {
		Praise = praise;
	}
	public WeChatPublic getWeChat() {
		return WeChat==null?new WeChatPublic():WeChat;
	}
	public void setWeChat(WeChatPublic weChat) {
		WeChat = weChat;
	}
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	
	
	public ProdPictures getImage() {
		return Image==null?new ProdPictures():Image;
	}
	public void setImage(ProdPictures image) {
		Image = image;
	}
	public long getImageID() {
		return ImageID;
	}
	public void setImageID(long imageID) {
		ImageID = imageID;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getHot() {
		return Hot;
	}
	public void setHot(int hot) {
		Hot = hot;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
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
	public int getApplyCount() {
		return ApplyCount;
	}
	public void setApplyCount(int applyCount) {
		ApplyCount = applyCount;
	}
	public int getApplyLimit() {
		return ApplyLimit;
	}
	public void setApplyLimit(int applyLimit) {
		ApplyLimit = applyLimit;
	}
	
	public double getApplyMoney() {
		return ApplyMoney;
	}
	public void setApplyMoney(double applyMoney) {
		ApplyMoney = applyMoney;
	}
	public String getSponsor() {
		return Sponsor;
	}
	public void setSponsor(String sponsor) {
		Sponsor = sponsor;
	}
	public String getSBrief() {
		return SBrief;
	}
	public void setSBrief(String sBrief) {
		SBrief = sBrief;
	}
	public String getSImage() {
		return SImage;
	}
	public void setSImage(String sImage) {
		SImage = sImage;
	}
	public ProdPictures getSPicture() {
		return SPicture;
	}
	public void setSPicture(ProdPictures sPicture) {
		SPicture = sPicture;
	}
	public List<ProdPictures> getPictures() {
		return Pictures;
	}
	public void setPictures(List<ProdPictures> pictures) {
		Pictures = pictures;
	}
	public int getPayType() {
		return PayType;
	}
	public void setPayType(int payType) {
		PayType = payType;
	}
	
	
	
	
}
