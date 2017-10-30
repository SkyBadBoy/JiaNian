package wtb.core.model;

import java.util.Date;

/**
 * 
 */
@SuppressWarnings("unchecked")
public class Vote { // 关键词
    //  属性名称
    public static final String VoteClass = "Vote";
    public static final String attributeVoteID = "ID";
    public static final String attributeVoteTitle = "Title";
    public static final String attributeVoteCreateTime = "CreateTime";
    public static final String attributeVoteModifyTime = "ModifyTime";
    public static final String attributeVoteStartTime = "StartTime";
    public static final String attributeVoteEndTime = "EndTime";
    public static final String attributeVoteStatus = "Status";
    public static final String attributeVoteRewardTimes = "RewardTimes";
    public static final String attributeVoteMemo = "Memo";
    public static final String attributeHeadUrl = "HeadUrl";
    public static final String attributeMiddleUrl = "MiddleUrl";
    public static final String attributeFootUrl = "FootUrl";
    public static final String attributeAreaID = "AreaID";

    private long ID;
    private String Pkid;
    private String Title;
    private String CreateTime;
    private String ModifyTime;
    private String StartTime;
    private String EndTime;
    private Date StartDate;
    private Date EndDate;
    private int RewardTimes;
    private String Memo;
    private String HeadUrl;
    private String MiddleUrl;
    private String FootUrl;
    private long AreaID;

    private int Status;
    private BaseInfo StatusInfo;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getPkid() {
		return Pkid;
	}

	public void setPkid(String pkid) {
		Pkid = pkid;
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

	public String getModifyTime() {
		return ModifyTime;
	}

	public void setModifyTime(String modifyTime) {
		ModifyTime = modifyTime;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public BaseInfo getStatusInfo() {
		return StatusInfo;
	}

	public void setStatusInfo(BaseInfo statusInfo) {
		StatusInfo = statusInfo;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public int getRewardTimes() {
		return RewardTimes;
	}

	public void setRewardTimes(int rewardTimes) {
		RewardTimes = rewardTimes;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}

	public String getHeadUrl() {
		return HeadUrl;
	}

	public void setHeadUrl(String headUrl) {
		HeadUrl = headUrl;
	}

	public String getMiddleUrl() {
		return MiddleUrl;
	}

	public void setMiddleUrl(String middleUrl) {
		MiddleUrl = middleUrl;
	}

	public String getFootUrl() {
		return FootUrl;
	}

	public void setFootUrl(String footUrl) {
		FootUrl = footUrl;
	}

	public long getAreaID() {
		return AreaID;
	}

	public void setAreaID(long areaID) {
		AreaID = areaID;
	}
 


}
