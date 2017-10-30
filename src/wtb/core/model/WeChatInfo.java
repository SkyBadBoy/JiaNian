package wtb.core.model;

public class WeChatInfo
{
	private long ID;
	private String PKID;
	private String Name;
	private String AppID;
	private String Appsecret;
	private String CreateTime;
	private String Status;
	private String AreaID;
	private Long UID;
	private String WID;
	private String AccessToken;
	private String ATTime;
	private String Ticket;
	private String TKTime;
	private long MchID;
	private String ApiKey;
	private int Weight;
	private int IsPay;//1代表默认支付
	private String CertificatePath;
	private Region AreaInfo;
	private BaseInfo BaseInfo;
	
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
	public String getAppID() {
		return AppID;
	}
	public void setAppID(String appID) {
		AppID = appID;
	}
	public String getAppsecret() {
		return Appsecret;
	}
	public void setAppsecret(String appsecret) {
		Appsecret = appsecret;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAreaID() {
		return AreaID;
	}
	public void setAreaID(String areaID) {
		AreaID = areaID;
	}
	public Long getUID() {
		return UID;
	}
	public void setUID(Long uID) {
		UID = uID;
	}
	public Region getAreaInfo() {
		return (AreaInfo==null)?new Region():AreaInfo;
	}
	public void setAreaInfo(Region areaInfo) {
		AreaInfo = areaInfo;
	}
	public BaseInfo getBaseInfo() {
		return (BaseInfo==null)?new BaseInfo():BaseInfo;
	}
	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}
	public String getWID() {
		return WID;
	}
	public void setWID(String wID) {
		WID = wID;
	}
	public String getAccessToken() {
		return AccessToken;
	}
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	public String getATTime() {
		return ATTime;
	}
	public void setATTime(String aTTime) {
		ATTime = aTTime;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getTKTime() {
		return TKTime;
	}
	public void setTKTime(String tKTime) {
		TKTime = tKTime;
	}
	public long getMchID() {
		return MchID;
	}
	public void setMchID(long mchID) {
		MchID = mchID;
	}
	public String getApiKey() {
		return ApiKey;
	}
	public void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
	public int getIsPay() {
		return IsPay;
	}
	public void setIsPay(int isPay) {
		IsPay = isPay;
	}
	public String getCertificatePath() {
		return CertificatePath;
	}
	public void setCertificatePath(String certificatePath) {
		CertificatePath = certificatePath;
	}

	
}
