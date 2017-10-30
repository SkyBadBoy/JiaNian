package wtb.core.model;

public class Messages {
	public Messages() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private String Content;
	private long From;
	private long To;
	private String CreateTime;
	private String ModifyTime;
	private int Status;
	private int IsRead;
	private int Type;
	private String SrcID;
	private Users FromUser;
	private Users ToUser;
	private BaseInfo BaseInfo;
	
	private Notices Notice;
	
	
	public Notices getNotice() {
		return Notice;
	}
	public void setNotice(Notices notice) {
		Notice = notice;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getSrcID() {
		return SrcID;
	}
	public void setSrcID(String srcID) {
		SrcID = srcID;
	}
	public int getIsRead() {
		return IsRead;
	}
	public void setIsRead(int isRead) {
		IsRead = isRead;
	}
	public BaseInfo getBaseInfo() {
		return BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
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
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
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
	public long getFrom() {
		return From;
	}
	public void setFrom(long from) {
		From = from;
	}
	public long getTo() {
		return To;
	}
	public void setTo(long to) {
		To = to;
	}
	public Users getFromUser() {
		return FromUser;
	}
	public void setFromUser(Users fromUser) {
		FromUser = fromUser;
	}
	public Users getToUser() {
		return ToUser;
	}
	public void setToUser(Users toUser) {
		ToUser = toUser;
	}
	

}
