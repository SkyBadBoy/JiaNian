package wtb.core.model;

public class ErrorLog {
	private long ID;
	private String PKID;
	private String UserID;
	private String Name;//发生在哪个类
	private String ClassName;
	private String Message;
	private String CreateTime;
	private int Status;
	private String IsStatus;
	private int Count;
	private String DateDay;
	private String DateDayHour;
	private Users UserInfo;
	
	private String Year;
	private String Month;
	private String Day;
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

	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
	public String getIsStatus() {
		return IsStatus;
	}
	public void setIsStatus(String isStatus) {
		IsStatus = isStatus;
	}
	public Users getUserInfo() {
		return UserInfo;
	}
	public void setUserInfo(Users userInfo) {
		UserInfo = userInfo;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public String getDateDay() {
		return DateDay;
	}
	public void setDateDay(String dateDay) {
		DateDay = dateDay;
	}
	public String getDateDayHour() {
		return DateDayHour;
	}
	public void setDateDayHour(String dateDayHour) {
		DateDayHour = dateDayHour;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}

}
