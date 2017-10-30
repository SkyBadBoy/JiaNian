package wtb.core.model;

import java.util.Date;
import java.util.List;


public class Video {
	public Video() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private String Content;
	private long ImageID;
	private int Weight;
	private String CreateTime;
	private Date CreateDate;
	private String ModifyTime;
	private int Status;
	private long WeChatID;
	private int Praise;
	private String Url;
	private String TempUrl;
	private String VID;
	private long ClickCount;
	private String Author;
	private ProdPictures Image;
	private Students Student;
	private BaseInfo BaseInfo;
	private WeChatInfo WeChat;
	private long Count;
	private long BelongID;
	private long AreaID;
	private long UserID;
	private long VoteCount;
	private long VoteID;
	private long LikeCount;
	private long CommentCount;
	private int IsActivity;
	private int UserType;
	private int Hot;
	private String AreaName;
	private int Type;
	private List<Comment> Comment;
	private int IsLike;
	private int IsVote;
	private int Day;
	private int Month;
	private int Year;
	private String Level;
	private double FileSize;
	private long FileTime;
	
	private Region SRegion;
	
	
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
	public long getImageID() {
		return ImageID;
	}
	public void setImageID(long imageID) {
		ImageID = imageID;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
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
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getVID() {
		return VID;
	}
	public void setVID(String vID) {
		VID = vID;
	}
	public ProdPictures getImage() {
		return Image;
	}
	public void setImage(ProdPictures image) {
		Image = image;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public long getClickCount() {
		return ClickCount;
	}
	public void setClickCount(long clickCount) {
		ClickCount = clickCount;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public Students getStudent() {
		return Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	public WeChatInfo getWeChat() {
		return (WeChat==null)?new WeChatInfo():WeChat;
	}
	public void setWeChat(WeChatInfo weChat) {
		WeChat = weChat;
	}
	public long getAreaID() {
		return AreaID;
	}
	public void setAreaID(long areaID) {
		AreaID = areaID;
	}

	public long getVoteCount() {
		return VoteCount;
	}
	public void setVoteCount(long voteCount) {
		VoteCount = voteCount;
	}
	public long getVoteID() {
		return VoteID;
	}
	public void setVoteID(long voteID) {
		VoteID = voteID;
	}
	public long getLikeCount() {
		return LikeCount;
	}
	public void setLikeCount(long likeCount) {
		LikeCount = likeCount;
	}
	public int getIsActivity() {
		return IsActivity;
	}
	public void setIsActivity(int isActivity) {
		IsActivity = isActivity;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public int getUserType() {
		return UserType;
	}
	public void setUserType(int userType) {
		UserType = userType;
	}
	public String getAreaName() {
		return AreaName;
	}
	public void setAreaName(String areaName) {
		AreaName = areaName;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public int getHot() {
		return Hot;
	}
	public void setHot(int hot) {
		Hot = hot;
	}
	public long getCommentCount() {
		return CommentCount;
	}
	public void setCommentCount(long commentCount) {
		CommentCount = commentCount;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public List<Comment> getComment() {
		return Comment;
	}
	public void setComment(List<Comment> comment) {
		Comment = comment;
	}
	public String getTempUrl() {
		return TempUrl;
	}
	public void setTempUrl(String tempUrl) {
		TempUrl = tempUrl;
	}
	public long getBelongID() {
		return BelongID;
	}
	public void setBelongID(long belongID) {
		BelongID = belongID;
	}
	public int getIsLike() {
		return IsLike;
	}
	public void setIsLike(int isLike) {
		IsLike = isLike;
	}
	public int getIsVote() {
		return IsVote;
	}
	public void setIsVote(int isVote) {
		IsVote = isVote;
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
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public Region getSRegion() {
		return SRegion;
	}
	public void setSRegion(Region sRegion) {
		SRegion = sRegion;
	}
	public long getCount() {
		return Count;
	}
	public void setCount(long count) {
		Count = count;
	}
	public double getFileSize() {
		return FileSize;
	}
	public void setFileSize(double fileSize) {
		FileSize = fileSize;
	}
	public long getFileTime() {
		return FileTime;
	}
	public void setFileTime(long fileTime) {
		FileTime = fileTime;
	}
	
	
	
	
}
