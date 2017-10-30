package wtb.core.model;

import java.util.List;

public class Scenic {
	public static final String ScenicClass = "Scenic";
    public static final String attributeScenicID = "id";
    public static final String attributeScenicName = "name";
    public static final String attributeScenicPhone = "phone";
    public static final String attributeScenicBusiness = "business";
    public static final String attributeScenicCreateTime = "createtime";
    public static final String attributeScenicModifyTime = "modifytime";
    public static final String attributeScenicBusinessid = "businessid";
    public static final String attributeScenicContent = "content";
    public static final String attributeScenicStatus = "status";
    public static final String attributeScenicAreaid = "areaid";
    public static final String attributeScenicRand = "Rand";
	private long id;
	private String PKID;
	private String name;
	private String phone;
	private String business;
	private String businessid;
	private String areaid;
	private String content;
	private int status;
	private String createtime;
	private String modifytime;
	private String address;
	private long userid;
	private Pictures businessImage;//;营业执照图片
	private List<ProdPictures> Pictures;//景区的图片
	private BaseInfo baseInfo;
	
	
	private String Images;//接口接受字段

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPKID() {
		return PKID;
	}

	public void setPKID(String pKID) {
		PKID = pKID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public Pictures getBusinessImage() {
		return businessImage;
	}

	public void setBusinessImage(Pictures businessImage) {
		this.businessImage = businessImage;
	}

	public List<ProdPictures> getPictures() {
		return Pictures;
	}

	public void setPictures(List<ProdPictures> pictures) {
		Pictures = pictures;
	}

	public String getImages() {
		return Images;
	}

	public void setImages(String images) {
		Images = images;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

}
