package wtb.core.json;

import java.util.List;

public class Comment {
	private String HeadImg;
	private String CreateTime;
	private String FromtTime;
	private String Content;
	private String Name;
	private String StudentID;
	private String PositionIcon;
	private String LevelIcon;
	private List<String> HonourIcon;
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getFromtTime() {
		return FromtTime;
	}
	public void setFromtTime(String fromtTime) {
		FromtTime = fromtTime;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
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
	
	
	

}
