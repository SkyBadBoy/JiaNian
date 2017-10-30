package wtb.core.model;

import java.util.*;

import org.activiti.engine.identity.Picture;
/**
 * WeChatBanner
 */
public class WeChatPublicSimple {
	
	private long ID	;
	private String PKID;
	private String WeChat	;
	private String Company	;
	
	/**
	 * 组织机构全称
	 */
	private String OrgName;

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

	public String getWeChat() {
		return WeChat;
	}

	public void setWeChat(String weChat) {
		WeChat = weChat;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getOrgName() {
		return OrgName;
	}

	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	
	
	
	
	
}
