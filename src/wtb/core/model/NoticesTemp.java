package wtb.core.model;

@SuppressWarnings("serial")
public class NoticesTemp implements java.io.Serializable{
	
	private long ID;
	private String title;
	private String content;
	private String imageIDList;
	private String imageUrl;
	private String imgLiId;
	private String imgLiSrc;
	private int status;
	private long NoticeSrcID;
	private String CreateTime;
	private String ModifyTime;


	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getImgLiId() {
		return imgLiId;
	}
	public void setImgLiId(String imgLiId) {
		this.imgLiId = imgLiId;
	}
	public String getImgLiSrc() {
		return imgLiSrc;
	}
	public void setImgLiSrc(String imgLiSrc) {
		this.imgLiSrc = imgLiSrc;
	}
	public String getTitle() {
		return (title==null)?"":title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return (content==null)?"":content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageIDList() {
		return imageIDList;
	}
	public void setImageIDList(String imageIDList) {
		this.imageIDList = imageIDList;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getNoticeSrcID() {
		return NoticeSrcID;
	}
	public void setNoticeSrcID(long noticeSrcID) {
		NoticeSrcID = noticeSrcID;
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

	
	
}
