package wtb.core.model;

import java.util.Date;


public class ActivityPart {
	public ActivityPart() {
		super();
	}
	private long ID;
	private String PKID;
	private long StudentID;
	private long ActivityID;
	private long Content;
	private String CreateTime;
	private Date ModifyTime;
	private int Status;
	
	private Activity Activity;
	private Students Student;
	private BaseInfo BaseInfo;
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
	public long getStudentID() {
		return StudentID;
	}
	public void setStudentID(long studentID) {
		StudentID = studentID;
	}
	public long getActivityID() {
		return ActivityID;
	}
	public void setActivityID(long activityID) {
		ActivityID = activityID;
	}
	public long getContent() {
		return Content;
	}
	public void setContent(long content) {
		Content = content;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
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
	public Activity getActivity() {
		return Activity;
	}
	public void setActivity(Activity activity) {
		Activity = activity;
	}
	public Students getStudent() {
		return Student==null?new Students():Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo==null?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	
	
	
	
	
	
}
