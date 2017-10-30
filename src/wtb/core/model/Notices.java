package wtb.core.model;

import java.util.Date;
import java.util.List;

public class Notices implements java.io.Serializable{
	public Notices() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private String Content;
	private String PublishUser;
	private String Author;
	private String CreateTime;
	private Date CreateDate;
	private String ModifyTime;
	private int Type;
	private int Status;
	private long AreaID;
	private int ClickCount;
	private int IsDel;
	private int IsSchool;  
	private int Region;
	private int IsCity;
	private int IsPro;
	private int WhoSend;
	private long Like;
	private int Count;
	private String Level;
	private String Name;
	private Students Student;
	private int IsActivity;
	private long ClickCountTemp;
	private int VoteCount;
	private long VoteID;
	private long CommentCount;
	private long ShareCount;
	private String CaptainComment;
	private String Memo;
	private int IsTop;
	private int Day;
	private int Month;
	private int Year;
	private List<ProdPictures> Pictures;
	
	private int IsLike;
	private int IsVote;
	private List<Comment> Comment;
	private int ContentType;  //0:老版本  1html
	public Students getStudent() {
		return Student==null?new Students():Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	/**
	 * 发布人
	 */
	private Users Publisher;
	private BaseInfo BaseInfo;
	
	private Long ImageID;
	private ProdPictures Image;
	
	private Region SRegion;
	public long getAreaID() {
		return AreaID;
	}
	public void setAreaID(long areaID) {
		AreaID = areaID;
	}
	public Long getImageID() {
		return ImageID;
	}
	public void setImageID(Long imageID) {
		ImageID = imageID;
	}
	public ProdPictures getImage() {
		return Image==null?new ProdPictures():Image;
	}
	public void setImage(ProdPictures image) {
		Image = image;
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
	public Users getPublisher() {
		return Publisher==null?new Users():Publisher;
	}
	public void setPublisher(Users publisher) {
		Publisher = publisher;
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
	

	public String getPublishUser() {
		return PublishUser;
	}
	public void setPublishUser(String publishUser) {
		PublishUser = publishUser;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
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
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getClickCount() {
		return ClickCount;
	}
	public void setClickCount(int clickCount) {
		ClickCount = clickCount;
	}
	public int getIsDel() {
		return IsDel;
	}
	public void setIsDel(int isDel) {
		IsDel = isDel;
	}
	
	public int getIsSchool() {
		return IsSchool;
	}
	public void setIsSchool(int isSchool) {
		IsSchool = isSchool;
	}
	public int getRegion() {
		return Region;
	}
	public void setRegion(int region) {
		Region = region;
	}
	public int getIsCity() {
		return IsCity;
	}
	public void setIsCity(int isCity) {
		IsCity = isCity;
	}
	public int getIsPro() {
		return IsPro;
	}
	public void setIsPro(int isPro) {
		IsPro = isPro;
	}
	public int getWhoSend() {
		return WhoSend;
	}
	public void setWhoSend(int whoSend) {
		WhoSend = whoSend;
	}
	public Region getSRegion() {
		return (SRegion==null)?new Region():SRegion;
	}
	public void setSRegion(Region sRegion) {
		SRegion = sRegion;
	}
	public long getLike() {
		return Like;
	}
	public void setLike(long like) {
		Like = like;
	}
	public int getIsActivity() {
		return IsActivity;
	}
	public void setIsActivity(int isActivity) {
		IsActivity = isActivity;
	}
	public long getClickCountTemp() {
		return ClickCountTemp;
	}
	public void setClickCountTemp(long clickCountTemp) {
		ClickCountTemp = clickCountTemp;
	}
	public int getVoteCount() {
		return VoteCount;
	}
	public void setVoteCount(int voteCount) {
		VoteCount = voteCount;
	}
	public long getVoteID() {
		return VoteID;
	}
	public void setVoteID(long voteID) {
		VoteID = voteID;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public long getCommentCount() {
		return CommentCount;
	}
	public void setCommentCount(long commentCount) {
		CommentCount = commentCount;
	}
	public List<ProdPictures> getPictures() {
		return Pictures;
	}
	public void setPictures(List<ProdPictures> pictures) {
		Pictures = pictures;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public int getDay() {
		return Day;
	}
	public void setDay(int day) {
		Day = day;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public int getIsLike() {
		return IsLike;
	}
	public void setIsLike(int isLike) {
		IsLike = isLike;
	}
	public List<Comment> getComment() {
		return Comment;
	}
	public void setComment(List<Comment> comment) {
		Comment = comment;
	}
	public int getIsVote() {
		return IsVote;
	}
	public void setIsVote(int isVote) {
		IsVote = isVote;
	}
	public long getShareCount() {
		return ShareCount;
	}
	public void setShareCount(long shareCount) {
		ShareCount = shareCount;
	}
	public String getCaptainComment() {
		return CaptainComment;
	}
	public void setCaptainComment(String captainComment) {
		CaptainComment = captainComment;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public int getIsTop() {
		return IsTop;
	}
	public void setIsTop(int isTop) {
		IsTop = isTop;
	}
	public int getContentType() {
		return ContentType;
	}
	public void setContentType(int contentType) {
		ContentType = contentType;
	}
	
	
}
