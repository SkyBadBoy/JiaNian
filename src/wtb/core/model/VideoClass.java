package wtb.core.model;



public class VideoClass {
	public VideoClass() {
		super();
	}
	public static final String VideoClassClass = "VideoClass";
    public static final String attributeVideoClassID = "ID";
    public static final String attributeVideoClassTitle = "Title";
    public static final String attributeVideoClassCreateTime = "CreateTime";
    public static final String attributeVideoClassModifyTime = "ModifyTime";
    public static final String attributeVideoClassParentID = "ParentID";
    public static final String attributeVideoClassImageUrl = "ImageUrl";
    public static final String attributeVideoClassStatus = "Status";
    public static final String attributeVideoClassLevel = "Level";
    public static final String attributeVideoClassMemo = "Memo";
    public static final String attributeVideoClassIsEnd = "IsEnd";
    public static final String attributeVideoClassType = "Type";
	
    
    
	private long ID;
	private String PKID;
	private String Title;
	private String ImageUrl;
	private long ParentID;
	private String CreateTime;
	private String ModifyTime;
	private int Status;
	private long Level;
	private int IsEnd;
	private String Memo;
	private int Type;
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
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public long getParentID() {
		return ParentID;
	}
	public void setParentID(long parentID) {
		ParentID = parentID;
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
	public long getLevel() {
		return Level;
	}
	public void setLevel(long level) {
		Level = level;
	}
	public int getIsEnd() {
		return IsEnd;
	}
	public void setIsEnd(int isEnd) {
		IsEnd = isEnd;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}

	
	
	
	
}
