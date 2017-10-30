package wtb.core.model;

import wtb.smUtil.SmBaseUtil;

@SuppressWarnings("serial")
public class WeMoneyRecord implements java.io.Serializable{
	public WeMoneyRecord() {
		super();
	}
	private long ID;
	private String PKID;
	private Double WeMoney;
	private int Deleted;
	private long UserID;
	private int Status;
	private String CreateTime;
	private String FormtTime;
	private int RewardLevel;
	private int Classify;
	private String Count;
	private Notices Notices;
	public Notices getNotices() {
		return Notices;
	}
	public void setNotices(Notices notices) {
		Notices = notices;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getFormtTime() {
		return FormtTime;
	}
	public void setFormtTime(String formtTime) {
		FormtTime = formtTime;
	}
	private String ModifyTime;
	private int Type;//微米增减类型 1为增加微米 2为赞赏 3为消费 4为其他   微米增减类型 1为充值 2为被他人赞赏 3为赞赏他人 4为其他
	private Long Belong;
	private String Reson;
	private Long FromUserID;
	private Students FromUser;

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
	public Double getWeMoney() {
		return WeMoney;
	}
	public void setWeMoney(Double weMoney) {
		WeMoney = weMoney;
	}
	public int getDeleted() {
		return Deleted;
	}
	public void setDeleted(int deleted) {
		Deleted = deleted;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
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
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public Long getBelong() {
		return Belong;
	}
	public void setBelong(Long belong) {
		Belong = belong;
	}
	public String getReson() {
		return Reson;
	}
	public void setReson(String reson) {
		Reson = reson;
	}
	public Long getFromUserID() {
		return FromUserID;
	}
	public void setFromUserID(Long fromUserID) {
		FromUserID = fromUserID;
	}
	public Students getFromUser() {
		return FromUser;
	}
	public void setFromUser(Students fromUser) {
		FromUser = fromUser;
	}
	public int getRewardLevel() {
		return RewardLevel;
	}
	public void setRewardLevel(int rewardLevel) {
		RewardLevel = rewardLevel;
	}
	public int getClassify() {
		return Classify;
	}
	public void setClassify(int classify) {
		Classify = classify;
	}
	
	
	
	
}
