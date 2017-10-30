package wtb.core.model;

public class AccessActive {
	
	private String ID;
	private String CreateTime;
	private String ModifyTime;
	private int Status;
	private String Memo;
	private String UserID;
	private String IPAddress;
	private String AccessArea;
	private String AccessUrl;
	private int Year;
	private int Month;
	private int Day;
	private int Hour;
	
	private long Count;
	private int Type;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getModifyTime() {
		return ModifyTime;
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
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getAccessArea() {
		return AccessArea;
	}
	public void setAccessArea(String accessArea) {
		AccessArea = accessArea;
	}
	public String getAccessUrl() {
		return AccessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		AccessUrl = accessUrl;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getDay() {
		return Day;
	}
	public void setDay(int day) {
		Day = day;
	}
	public long getCount() {
		return Count;
	}
	public void setCount(long count) {
		Count = count;
	}
	public int getHour() {
		return Hour;
	}
	public void setHour(int hour) {
		Hour = hour;
	}
	
	

}
