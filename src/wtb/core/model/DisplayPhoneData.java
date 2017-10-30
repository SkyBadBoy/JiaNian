package wtb.core.model;

import java.util.List;


public class DisplayPhoneData {
	public DisplayPhoneData() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private int Type; //图片0还是活动1
	private long WeChatID;
	private WeChatPublic WeChat;
	private float weight;
	private Activity Activity;
	private List<Product> ProductList;
	private List<Notices> NoticesList;
	private Video Video;
	
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		Video = video;
	}
	public WeChatPublic getWeChat() {
		return WeChat;
	}
	public void setWeChat(WeChatPublic content) {
		WeChat = content;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
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
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public long getWeChatID() {
		return WeChatID;
	}
	public void setWeChatID(long weChatID) {
		WeChatID = weChatID;
	}
	public Activity getActivity() {
		return Activity;
	}
	public void setActivity(Activity activity) {
		Activity = activity;
	}
	public List<Product> getProductList() {
		return ProductList;
	}
	public void setProductList(List<Product> productList) {
		ProductList = productList;
	}
	
	
	
}
