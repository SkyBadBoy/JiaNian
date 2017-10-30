package wtb.core.model;

public class Comment {
	private long ID;
	private String PKID;
	private String UserID;
	private long NoticesID;
	private String CreateTime;
	private String Content;
	private int Status;
	private int Type;
	private String NewTitle;
	private String UserName;
	private String UserImage;
	private String FromtTime;
	private Notices Notice;
	private Students Student;

	public Notices getNotice() {
		return (Notice==null)?new Notices():Notice;
	}
	public void setNotice(Notices notice) {
		Notice = notice;
	}
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public String getFromtTime() {
		return FromtTime;
	}
	public void setFromtTime(String fromtTime) {
		FromtTime = fromtTime;
	}
	public String getUserImage() {
		return UserImage;
	}
	public void setUserImage(String userImage) {
		UserImage = userImage;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}

	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public long getNoticesID() {
		return NoticesID;
	}
	public void setNoticesID(long noticesID) {
		NoticesID = noticesID;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getNewTitle() {
		return NewTitle;
	}
	public void setNewTitle(String newTitle) {
		NewTitle = newTitle;
	}
	public Students getStudent() {
		return Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	
	
	
	

}
