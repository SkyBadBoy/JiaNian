package wtb.core.model;


import wtb.smUtil.SmBaseGlobal;
public class Students implements java.io.Serializable{
	public Students() {
		super();
	}
	private long ID;
	private String PKID;
	private String Name;
	private String Age;
	private String Sex;
	private String IsSex;
	private String Level;
	private Long ImageID;
	private Long QRImageID;
	private String Phone;
	private String School;
	private String No;
	private String CreateTime;
	private String PassWord;
	private int Status;
	private String ImageUrl;
	private String AreaID;
	private Pictures Image;
	private Pictures QRImage;
	private BaseInfo BaseInfo;
	private String LoginName;
	private Region Area;
	private String OpenID;
	private String TempOpenID;
	private String UnionID;
	private long WBID;
	private int Students_IP;
	private long Integration;
	private String ParentName;
	private String Email;
	private String Grade;
	private String Habit;
	private int ischange;
	private int isadd;
	private int Chief;
	private int Official;
	private String ChiefName;
	private String OfficialName;
	private String Order;
	private long NoticeCount;
	private int HonorCount;
	private int SendCount;//短信通知数量
	private String BirthDayRemind;//上次发送生日提醒
	private String DiamondLevel;
	
	private int Count;
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
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
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	
	public String getIsSex() {
		return IsSex;
	}
	public void setIsSex(String isSex) {
		IsSex = isSex;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public Long getImageID() {
		return ImageID;
	}
	public void setImageID(Long imageID) {
		ImageID = imageID;
	}
	public Long getQRImageID() {
		return QRImageID;
	}
	public void setQRImageID(Long qRImageID) {
		QRImageID = qRImageID;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getImageUrl() {
		return (ImageUrl==null || ImageUrl.isEmpty())?SmBaseGlobal.WeChatAPIURL+"WeNewsAgency/"+ SmBaseGlobal.UserDefaultImageUrl:ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getAreaID() {
		return AreaID;
	}
	public void setAreaID(String areaID) {
		AreaID = areaID;
	}
	public Pictures getImage() {
		return Image;
	}
	public void setImage(Pictures image) {
		Image = image;
	}
	public Pictures getQRImage() {
		return QRImage;
	}
	public void setQRImage(Pictures qRImage) {
		QRImage = qRImage;
	}
	public BaseInfo getBaseInfo() {
		return (BaseInfo==null)?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public Region getArea() {
		return (Area==null)?new Region():Area;
	}
	public void setArea(Region area) {
		Area = area;
	}
	public String getOpenID() {
		return OpenID;
	}
	public void setOpenID(String openID) {
		OpenID = openID;
	}
	
	public String getUnionID() {
		return UnionID;
	}
	public void setUnionID(String unionID) {
		UnionID = unionID;
	}
	public long getWBID() {
		return WBID;
	}
	public void setWBID(long wBID) {
		WBID = wBID;
	}
	public int getStudents_IP() {
		return Students_IP;
	}
	public void setStudents_IP(int students_IP) {
		Students_IP = students_IP;
	}
	public long getIntegration() {
		return Integration;
	}
	public void setIntegration(long integration) {
		Integration = integration;
	}
	public int getIschange() {
		return ischange;
	}
	public void setIschange(int ischange) {
		this.ischange = ischange;
	}
	public int getIsadd() {
		return isadd;
	}
	public void setIsadd(int isadd) {
		this.isadd = isadd;
	}
	public String getParentName() {
		return ParentName;
	}
	public void setParentName(String parentName) {
		ParentName = parentName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getHabit() {
		return Habit;
	}
	public void setHabit(String habit) {
		Habit = habit;
	}
	public long getNoticeCount() {
		return NoticeCount;
	}
	public void setNoticeCount(long noticeCount) {
		NoticeCount = noticeCount;
	}
	public int getChief() {
		return Chief;
	}
	public void setChief(int chief) {
		Chief = chief;
	}
	public int getOfficial() {
		return Official;
	}
	public void setOfficial(int official) {
		Official = official;
	}
	public String getChiefName() {
		if(Chief==SmBaseGlobal.ChiefType.Chief.getid()){
			return "社长";
		}else if(Chief==SmBaseGlobal.ChiefType.DeputyChief.getid()){
			return "副社长";
		}else if(Chief==SmBaseGlobal.ChiefType.Editorial.getid()){
			return "编委";
		}else {
			return "普通成员";
		}
		
	}

	public String getOfficialName() {
		return (Official==1)?"正式社员":"非正式社员";
	}
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
	public int getHonorCount() {
		return HonorCount;
	}
	public void setHonorCount(int honorCount) {
		HonorCount = honorCount;
	}
	public int getSendCount() {
		return SendCount;
	}
	public void setSendCount(int sendCount) {
		SendCount = sendCount;
	}
	public String getBirthDayRemind() {
		return BirthDayRemind;
	}
	public void setBirthDayRemind(String birthDayRemind) {
		BirthDayRemind = birthDayRemind;
	}
	public String getDiamondLevel() {
		if(Integration>=500000){
			DiamondLevel="5";
		}else if(Integration>=200000){
			DiamondLevel="4";
		}else if(Integration>=100000){
			DiamondLevel="3";
		}else if(Integration>=50000){
			DiamondLevel="2";
		}else if(Integration>=20000){
			DiamondLevel="1";
		}else{
			DiamondLevel= "0";
		}
		return DiamondLevel;
	}
	public void setDiamondLevel(String diamondLevel) {
		DiamondLevel = diamondLevel;
	}
	public String getTempOpenID() {
		return TempOpenID;
	}
	public void setTempOpenID(String tempOpenID) {
		TempOpenID = tempOpenID;
	}

	
	
	
	
}
