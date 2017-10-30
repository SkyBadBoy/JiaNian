package wtb.core.json;

import java.util.List;

public class Video {
	
	private String VideoID;
	private String StudentID;
	private String Name;
	private String HeadImg;
	private String Content;
	private String ImageUrl;
	private String Title;
	private long ClickCount;
	private int IsActivity;
	private long VoteCount;
	private int VideoType;//视频类型 是不是用户还是学校还是管理员
	private String CreateTime;
	private int IsLike;
	private long LikeCount;
	private String Url;//播放地址
	private int IsVote;
	private String ImageID;
	private long AreaID;
	private long CommentCount;
	
	private double FileSize;
	private long FileTime;
	private int Type;//0后台发布   1用户发布
	
	private boolean IsAnalysis;//是否解析
	
	private String PositionIcon;
	private String LevelIcon;
	private List<String> HonourIcon;
	private String School;
	public String getVideoID() {
		return VideoID;
	}
	public void setVideoID(String videoID) {
		VideoID = videoID;
	}

	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

	public long getClickCount() {
		return ClickCount;
	}
	public void setClickCount(long clickCount) {
		ClickCount = clickCount;
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
	public int getVideoType() {
		return VideoType;
	}
	public void setVideoType(int videoType) {
		VideoType = videoType;
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
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public int getIsVote() {
		return IsVote;
	}
	public void setIsVote(int isVote) {
		IsVote = isVote;
	}
	public String getImageID() {
		return ImageID;
	}
	public void setImageID(String imageID) {
		ImageID = imageID;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public long getLikeCount() {
		return LikeCount;
	}
	public void setLikeCount(long likeCount) {
		LikeCount = likeCount;
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
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public boolean isIsAnalysis() {
		return IsAnalysis;
	}
	public void setIsAnalysis(boolean isAnalysis) {
		IsAnalysis = isAnalysis;
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
	public List<String> getHonourIcon() {
		return HonourIcon;
	}
	public void setHonourIcon(List<String> honourIcon) {
		HonourIcon = honourIcon;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}

	
	

}
