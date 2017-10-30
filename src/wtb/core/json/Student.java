package wtb.core.json;

import java.util.List;

/**
 * 个人信息
 * @author Administrator
 *
 */
public class Student {
	private String ID;//id
	private String Name;//名字
	private String Level;//等级
	private String Age;//出生日期
	private String sex;
	private String HeadImg;
	private String AreaID;
	private String Phone;
	private String School;
	private String Integration;
	private double WeMoney;//微米，积分
	private String NoticeCount;
	private String Habit;
	private String Grade;
	private String Email;
	private String ParentName;
	private String CreateTime;
	private String UnionID;
	private int IsSign;//今天是否签到
	private long SignCount;
	private long ContinuityCount;//连续签到 
	private String PassWord;
	
	private String PositionIcon;
	private String LevelIcon;
	private List<String> HonourIcon;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
	public String getIntegration() {
		return Integration;
	}
	public void setIntegration(String integration) {
		Integration = integration;
	}
	public String getNoticeCount() {
		return NoticeCount;
	}
	public void setNoticeCount(String noticeCount) {
		NoticeCount = noticeCount;
	}
	public String getHabit() {
		return Habit;
	}
	public void setHabit(String habit) {
		Habit = habit;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getParentName() {
		return ParentName;
	}
	public void setParentName(String parentName) {
		ParentName = parentName;
	}
	public String getAreaID() {
		return AreaID;
	}
	public void setAreaID(String areaID) {
		AreaID = areaID;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public double getWeMoney() {
		return WeMoney;
	}
	public void setWeMoney(double weMoney) {
		WeMoney = weMoney;
	}
	public String getUnionID() {
		return UnionID;
	}
	public void setUnionID(String unionID) {
		UnionID = unionID;
	}
	public int getIsSign() {
		return IsSign;
	}
	public void setIsSign(int isSign) {
		IsSign = isSign;
	}
	public long getSignCount() {
		return SignCount;
	}
	public void setSignCount(long signCount) {
		SignCount = signCount;
	}
	
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getPositionIcon() {
		return PositionIcon;
	}
	public void setPositionIcon(String positionIcon) {
		PositionIcon = positionIcon;
	}
	public String getLevelIcon() {
		return LevelIcon;
	}
	public void setLevelIcon(String levelIcon) {
		LevelIcon = levelIcon;
	}
	public List<String> getHonourIcon() {
		return HonourIcon;
	}
	public void setHonourIcon(List<String> honourIcon) {
		HonourIcon = honourIcon;
	}
	public long getContinuityCount() {
		return ContinuityCount;
	}
	public void setContinuityCount(long continuityCount) {
		ContinuityCount = continuityCount;
	}

	
	
	
	

}
