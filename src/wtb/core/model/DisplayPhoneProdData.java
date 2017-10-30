package wtb.core.model;

import java.util.List;


public class DisplayPhoneProdData {
	public DisplayPhoneProdData() {
		super();
	}
	private long ID;
	private String PKID;
	private String Title;
	private WeChatPublic WeChat;
	private List<Activity> Activitys;
	private List<Product> ProductList;
	
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
	public WeChatPublic getWeChat() {
		return WeChat;
	}
	public void setWeChat(WeChatPublic weChat) {
		WeChat = weChat;
	}
	
	public List<Activity> getActivitys() {
		return Activitys;
	}
	public void setActivitys(List<Activity> activitys) {
		Activitys = activitys;
	}
	public List<Product> getProductList() {
		return ProductList;
	}
	public void setProductList(List<Product> productList) {
		ProductList = productList;
	}
	
	
	
	
}
