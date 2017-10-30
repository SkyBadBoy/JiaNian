package wtb.core.json;

import java.util.List;


/**
 * 活动列表
 * @author Administrator
 *
 */
public class Activity {
	private String StudentID;
	private String ActivityID;
	private String Title;
	private String EndTime;
	private List<String> Img;
	private String Content;
	private int ApplyCount;
	private int Hot;//是否是热门活动
	private String Sponsor;//主办方
	private String SBrief;//主办方简介
	private String SHeadImage;//主办方图片
	private String CreateTime;
	private int IsApply;//是否参加活动
	
	private int PayType;//支付类型
	
	private int ApplyLimit;
	private double ApplyMoney;
	
	public String getActivityID() {
		return ActivityID;
	}
	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public List<String> getImg() {
		return Img;
	}
	public void setImg(List<String> img) {
		Img = img;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getApplyCount() {
		return ApplyCount;
	}
	public void setApplyCount(int applyCount) {
		ApplyCount = applyCount;
	}
	public int getHot() {
		return Hot;
	}
	public void setHot(int hot) {
		Hot = hot;
	}
	public String getSponsor() {
		return Sponsor;
	}
	public void setSponsor(String sponsor) {
		Sponsor = sponsor;
	}
	public String getSBrief() {
		return SBrief;
	}
	public void setSBrief(String sBrief) {
		SBrief = sBrief;
	}
	public String getSHeadImage() {
		return SHeadImage;
	}
	public void setSHeadImage(String sHeadImage) {
		SHeadImage = sHeadImage;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public int getIsApply() {
		return IsApply;
	}
	public void setIsApply(int isApply) {
		IsApply = isApply;
	}
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public int getPayType() {
		return PayType;
	}
	public void setPayType(int payType) {
		PayType = payType;
	}
	public int getApplyLimit() {
		return ApplyLimit;
	}
	public void setApplyLimit(int applyLimit) {
		ApplyLimit = applyLimit;
	}
	public double getApplyMoney() {
		return ApplyMoney;
	}
	public void setApplyMoney(double applyMoney) {
		ApplyMoney = applyMoney;
	}
	
	
	
}
