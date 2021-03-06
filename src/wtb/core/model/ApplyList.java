package wtb.core.model;

import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

public class ApplyList {
	public ApplyList() {
		super();
	}
	private long ID;
	private String PKID;
	private String Content;
	private String UserName;
	private String UserPhone;
	private String CreateTime;
	private String ModifyTime;
	private int Status;
	private String StudentID;
	private int Type;
	private String Course;
	private long UserID;
	private String Feedback;
	private int ApplyType;
	private String ApplyTypeStr;
	private Users User;
	
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
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserPhone() {
		return UserPhone;
	}
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	public String getCreateTime() {
		if (SmBaseUtil.CheckIsNull(CreateTime)) {
			return CreateTime.substring(0, 19);
		}else {
			return CreateTime;
		}
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getModifyTime() {
		if (SmBaseUtil.CheckIsNull(ModifyTime)) {
			return ModifyTime.substring(0, 19);
		}else {
			return ModifyTime;
		}
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
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getCourse() {
		return Course;
	}
	public void setCourse(String course) {
		Course = course;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public String getFeedback() {
		return Feedback;
	}
	public void setFeedback(String feedback) {
		Feedback = feedback;
	}
	public int getApplyType() {
		return ApplyType;
	}
	public void setApplyType(int applyType) {
		ApplyType = applyType;
	}
	public Users getUser() {
		return User;
	}
	public void setUser(Users user) {
		User = user;
	}
	public String getApplyTypeStr() {
		if (ApplyType==SmBaseGlobal.ApplyWhichType.Mobile.getid()) {
			return "手机端";
		}else if(ApplyType==SmBaseGlobal.ApplyWhichType.Official.getid()){
			return "官网";
		}else {
			return "其他";
		}
	}
	public void setApplyTypeStr(String applyTypeStr) {
		ApplyTypeStr = applyTypeStr;
	}
	
	
	
	
	
}
