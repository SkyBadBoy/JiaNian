package wtb.core.model;


public class WBUser {
	public WBUser() {
		super();
	}

	private long WBID;
	private String nickname;
	private String head_url;
	private String access_token;
	private long access_token_time;
	public long getWBID() {
		return WBID;
	}
	public void setWBID(long wBID) {
		WBID = wBID;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHead_url() {
		return head_url;
	}
	public void setHead_url(String head_url) {
		this.head_url = head_url;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public long getAccess_token_time() {
		return access_token_time;
	}
	public void setAccess_token_time(long access_token_time) {
		this.access_token_time = access_token_time;
	}


	
	

}
