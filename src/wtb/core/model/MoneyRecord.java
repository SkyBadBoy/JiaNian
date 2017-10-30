package wtb.core.model;

public class MoneyRecord {


	private long ID;
	private String PKID;
	private long StudentID;
	private long ActivityID;
	private String Money;
	private String PayTime;
	private String RefundTime;
	private int Type;
	private long BusinessID;
	private String RefundID;//主要用户退款失败的时候，重新退款所用
	
	private Students Student;
	private BaseInfo BaseInfo; 
	private WeChatInfo WeChatInfo;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getStudentID() {
		return StudentID;
	}
	public void setStudentID(long studentID) {
		StudentID = studentID;
	}
	
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getPayTime() {
		return PayTime;
	}
	public void setPayTime(String payTime) {
		PayTime = payTime;
	}
	public String getRefundTime() {
		return RefundTime;
	}
	public void setRefundTime(String refundTime) {
		RefundTime = refundTime;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public long getBusinessID() {
		return BusinessID;
	}
	public void setBusinessID(long businessID) {
		BusinessID = businessID;
	}
	public Students getStudent() {
		return Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	public long getActivityID() {
		return ActivityID;
	}
	public void setActivityID(long activityID) {
		ActivityID = activityID;
	}
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public WeChatInfo getWeChatInfo() {
		return WeChatInfo;
	}
	public void setWeChatInfo(WeChatInfo weChatInfo) {
		WeChatInfo = weChatInfo;
	}
	public String getRefundID() {
		return RefundID;
	}
	public void setRefundID(String refundID) {
		RefundID = refundID;
	}
	
	
}
