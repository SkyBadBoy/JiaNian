package wtb.core.model;

import java.util.Date;
import java.util.List;


public class DisplayWeNewsPhoneData {
	public DisplayWeNewsPhoneData() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private int Type; //新闻0 还是活动1 视频2
	private int ClickCount;
	private String CreateTime; 
	private float weight;
	private Activity Activity;
	private Product ProductList;
	private Notices NoticesList;
	private Video Video;
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
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public Activity getActivity() {
		return Activity;
	}
	public void setActivity(Activity activity) {
		Activity = activity;
	}
	public Product getProductList() {
		return ProductList;
	}
	public void setProductList(Product productList) {
		ProductList = productList;
	}
	public Notices getNoticesList() {
		return NoticesList;
	}
	public void setNoticesList(Notices noticesList) {
		NoticesList = noticesList;
	}
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		Video = video;
	}
	public int getClickCount() {
		return ClickCount;
	}
	public void setClickCount(int clickCount) {
		ClickCount = clickCount;
	}
	
	
	
	
	
}
