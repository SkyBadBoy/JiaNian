package wtb.core.model;

import java.util.Date;
import java.util.List;


public class Product {
	public Product() {
		super();
	}
	private long ID;
	private String PKID;
	private String Name;
	private String Content;
	private String Specifications;
	private String Author;
	private float Price;
	private long Praise;
	private int Hot;
	private long WeChatID;
	private String CreateTime;
	private Date ModifyTime;
	private int Status;
	private int IsNew;
	private int Weight;
	private long ClickCount;
	private long LikeCount;
	private long ImageID;
	private String URL;
	private String Memo;
	
	private WeChatPublic WeChat;
	private ProdPictures Image;
	private BaseInfo baseInfo;
	
	private int ContentType;
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public BaseInfo getBaseInfo() {
		return baseInfo==null?new BaseInfo():baseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
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
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getSpecifications() {
		return Specifications;
	}
	public void setSpecifications(String specifications) {
		Specifications = specifications;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public long getPraise() {
		return Praise;
	}
	public void setPraise(long praise) {
		Praise = praise;
	}
	public int getHot() {
		return Hot;
	}
	public void setHot(int hot) {
		Hot = hot;
	}
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}

	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
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
	public int getIsNew() {
		return IsNew;
	}
	public void setIsNew(int isNew) {
		IsNew = isNew;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
	public long getClickCount() {
		return ClickCount;
	}
	public void setClickCount(long clickCount) {
		ClickCount = clickCount;
	}
	public WeChatPublic getWeChat() {
		return WeChat==null?new WeChatPublic():WeChat;
	}
	public void setWeChat(WeChatPublic weChat) {
		WeChat = weChat;
	}
	public ProdPictures getImage() {
		return Image==null?new ProdPictures():Image;
	}
	public void setImage(ProdPictures image) {
		Image = image;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public long getLikeCount() {
		return LikeCount;
	}
	public void setLikeCount(long likeCount) {
		LikeCount = likeCount;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public int getContentType() {
		return ContentType;
	}
	public void setContentType(int contentType) {
		ContentType = contentType;
	}
	
	
	
}
