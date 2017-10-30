package wtb.core.json;

public class Picture {
	private String ImageUrl;
	private String ClickUrl;
	private int Type;
	private long ResourceID;
	private String Title;
	private String CreateTime;
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getClickUrl() {
		return ClickUrl;
	}
	public void setClickUrl(String clickUrl) {
		ClickUrl = clickUrl;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public long getResourceID() {
		return ResourceID;
	}
	public void setResourceID(long resourceID) {
		ResourceID = resourceID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	

}
