package wtb.core.model;

@SuppressWarnings("serial")
public class WeMoney implements java.io.Serializable{
	public WeMoney() {
		super();
	}
	private long ID;
	private String PKID;
	private double WeMoney;
	private int Deleted;
	private long UserID;
	private int Status;
	private String CreateTime;
	private String ModifyTime;
	private long ResidualIntegral;//剩余积分
	
	private String Count;
	private String Name;
	private Students Student;
	
	public Students getStudent() {
		return Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
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
	public double getWeMoney() {
		return WeMoney;
	}
	public void setWeMoney(double weMoney) {
		WeMoney = weMoney;
	}
	public int getDeleted() {
		return Deleted;
	}
	public void setDeleted(int deleted) {
		Deleted = deleted;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
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
	public long getResidualIntegral() {
		return ResidualIntegral;
	}
	public void setResidualIntegral(long residualIntegral) {
		ResidualIntegral = residualIntegral;
	}
	
	
	
}
