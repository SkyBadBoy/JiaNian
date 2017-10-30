package wtb.core.json;

import java.util.List;

public class News {
	private String NoticeID;
	private String StudentID;
	private String Name;
	private String HeadImg;
	private String Content;
	private List<String> Imgs;
	private String Title;
	private String ClickCount;
	private int IsActivity;
	private long VoteCount;
	private int NoticeType;
	private String CreateTime;
	private String ModifyTime;
	private int IsLike;
	private int IsVote;//检测是否自己投过票
	private String Type;
	private long LikeCount;
	private String ImageIDList;
	private long  RewardCount;//打赏的总数
	private long AreaID;
	private long CommentCount;
	private String PositionIcon;
	private String LevelIcon;
	private List<String> HonourIcon;
	private String ImageUrl;
	private String ContentID;//内容组ID
	private int ContentType;  //0:老版本  1html
	private String HtmlContent;
	private int DeviceType;
	private String School;

	public String getNoticeID() {
		return NoticeID;
	}
	public void setNoticeID(String noticeID) {
		NoticeID = noticeID;
	}
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studetID) {
		StudentID = studetID;
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
	public List<String> getImgs() {
		return Imgs;
	}
	public void setImgs(List<String> imgs) {
		Imgs = imgs;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getClickCount() {
		return ClickCount;
	}
	public void setClickCount(String clickCount) {
		ClickCount = clickCount;
	}
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public int getIsActivity() {
		return IsActivity;
	}
	public void setIsActivity(int isActivity) {
		IsActivity = isActivity;
	}

	public long getVoteCount() {
		return VoteCount;
	}
	public void setVoteCount(long voteCount) {
		VoteCount = voteCount;
	}
	public int getNoticeType() {
		return NoticeType;
	}
	public void setNoticeType(int noticeType) {
		NoticeType = noticeType;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
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
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public long getLikeCount() {
		return LikeCount;
	}
	public void setLikeCount(long likeCount) {
		LikeCount = likeCount;
	}
	public String getImageIDList() {
		return ImageIDList;
	}
	public void setImageIDList(String imageIDList) {
		ImageIDList = imageIDList;
	}
	public long getRewardCount() {
		return RewardCount;
	}
	public void setRewardCount(long rewardCount) {
		RewardCount = rewardCount;
	}
	public long getAreaID() {
		return AreaID;
	}
	public void setAreaID(long areaID) {
		AreaID = areaID;
	}
	public long getCommentCount() {
		return CommentCount;
	}
	public void setCommentCount(long commentCount) {
		CommentCount = commentCount;
	}
	public String getPositionIcon() {
		return PositionIcon;
	}
	public void setPositionIcon(String positionIcon) {
		PositionIcon = positionIcon;
	}
	public String getLevelIcon() {
		return LevelIcon;
	}
	public void setLevelIcon(String levelIcon) {
		LevelIcon = levelIcon;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public List<String> getHonourIcon() {
		return HonourIcon;
	}
	public void setHonourIcon(List<String> honourIcon) {
		HonourIcon = honourIcon;
	}
	public String getContentID() {
		return ContentID;
	}
	public void setContentID(String contentID) {
		ContentID = contentID;
	}
	public int getContentType() {
		return ContentType;
	}
	public void setContentType(int contentType) {
		ContentType = contentType;
	}
	public String getHtmlContent() {
		return HtmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		HtmlContent = htmlContent;
	}
	public int getDeviceType() {
		return DeviceType;
	}
	public void setDeviceType(int deviceType) {
		DeviceType = deviceType;
	}
	public String getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(String modifyTime) {
		ModifyTime = modifyTime;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
}
