package wtb.core.json;

public class Like {
	private String ID;//新闻或视频ID
	private String StudentID;
	private String HeadImg;
	private String Other;//代表其他方式的点赞
	private int Type;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public String getOther() {
		return Other;
	}
	public void setOther(String other) {
		Other = other;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	

	
	

}
