package wtb.core.model;

public class StudentsLog 
{
	private long ID;
	private String PKID;
	private String CreateTime;
	private long NewID;
	private String PKNID;
	private long StudentsID;
	private int Status;
	private String URL;
	private String Request;
	private String Type;
	
	private Notices news;
	
	private Students Students;
	
	private BaseInfo BaseInfo;
	
	private WeChatPublic WCID;
	

	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public long getNewID() {
		return NewID;
	}
	public void setNewID(long newID) {
		NewID = newID;
	}
	
	public String getPKNID() {
		return PKNID;
	}
	public void setPKNID(String pKNID) {
		PKNID = pKNID;
	}
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public long getStudentsID() {
		return StudentsID;
	}
	public void setStudentsID(long studentsID) {
		StudentsID = studentsID;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getRequest() {
		return Request;
	}
	public void setRequest(String request) {
		Request = request;
	}

	
	public Notices getNews() {
		return (news==null)?new Notices():news;
	}
	public void setNews(Notices news) {
		this.news = news;
	}
	public Students getStudents() {
		return Students==null?new Students():Students;
	}
	public void setStudents(Students students) {
		Students = students;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo==null?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public WeChatPublic getWCID() {
		return WCID==null?new WeChatPublic():WCID;
	}
	public void setWCID(WeChatPublic wCID) {
		WCID = wCID;
	}

}
