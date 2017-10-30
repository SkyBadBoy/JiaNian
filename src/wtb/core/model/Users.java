package wtb.core.model;

import java.util.Date;

public class Users implements java.io.Serializable{
	public Users() {
		super();
	}
	private long ID;
	private String PKID;
	private String LoginName;
	private String PassWord;
	private int Level;
	private Date CreateTime	;	
	private Date ModifyTime	;	
	private int Status	;
	private String Name	;
	private long ImageID;
	private long WeChatID;
	private Long AreaID;
	private String Phone	;
	private String Person	;
	
	private Pictures Image;
	private BaseInfo BaseInfo;
	private BaseInfo AdminLevel;
	private Region Area;
	
	
	
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getPerson() {
		return Person;
	}
	public void setPerson(String person) {
		Person = person;
	}
	public Region getArea() {
		if(Area==null){
			Area=new Region();
		}
		return Area;
	}
	public void setArea(Region area) {
		
		Area = area;
	}
	public Long getAreaID() {
		return AreaID;
	}
	public void setAreaID(Long areaID) {
		AreaID = areaID;
	}
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}
	public BaseInfo getAdminLevel() {
		return AdminLevel==null?new BaseInfo():AdminLevel;
	}
	public void setAdminLevel(BaseInfo adminLevel) {
		AdminLevel = adminLevel;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public long getImageID() {
		return ImageID;
	}
	public void setImageID(long imageID) {
		ImageID = imageID;
	}
	public Pictures getImage() {
		return Image==null?new Pictures():Image;
	}
	public void setImage(Pictures image) {
		Image = image;
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
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
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
	
}
