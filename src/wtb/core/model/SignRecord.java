package wtb.core.model;

import java.util.Date;


public class SignRecord {
	public SignRecord() {
		super();
	}
	private long ID;
	private String UserID;
	private String PKID;
	private String Memo;
	private int Day;
	private int Month;
	private int Year;
	private Date CreateTime;
	private int Status;
	private int Deleted;
	private int Week;
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
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
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
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getDeleted() {
		return Deleted;
	}
	public void setDeleted(int deleted) {
		Deleted = deleted;
	}
	public int getWeek() {
		return Week;
	}
	public void setWeek(int week) {
		Week = week;
	}
	

	
	
}
