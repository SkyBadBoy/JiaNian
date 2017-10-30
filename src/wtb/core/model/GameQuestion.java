package wtb.core.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import wtb.smUtil.VerifyCode;

/**
 * Created by SASR on 17/06/03.
 */
public class GameQuestion { // 在线信息
    //  属性名称
    public static final String GameQuestionClass = "GameQuestion";
    public static final String attributeGameQuestionID = "ID";
    public static final String attributeGameQuestionCreateTime = "CreateTime";
    public static final String attributeGameQuestionModifyTime = "ModifyTime";
    public static final String attributeGameQuestionVersion = "Version";
    public static final String attributeGameQuestionStatus = "Status";
    public static final String attributeGameQuestionMemo = "Memo";
    public static final String attributeGameQuestionTitle = "Title";
    public static final String attributeGameQuestionAnswer = "Answer";
    public static final String attributeGameQuestionFailCount = "FailCount";
    public static final String attributeGameQuestionRightCount = "RightCount";
    public static final String attributeGameQuestionContent = "Content";
    public static final String attributeGameQuestionNumber = "Number";
    public static final String attributeGameQuestionImageUrl = "ImageUrl";
    public static final String attributeGameQuestionType = "Type";
    public static final String attributeGameQuestionContinuedTime = "ContinuedTime";
    
    

    private long ID;//在线编号
    private String PKID;
    private int Version;//修改次数
    private int Status;//状态信息
    private String Memo;//是否删除
    private String CreateTime;//创建时间
    private String ModifyTime;//修改时间
    private String Answer;//答案
    private String Title;//标题
    private int FailCount;
    private int RightCount;
    private String Content;
    private int Number;
    private String ImageUrl;
    private int Type;
    private int ContinuedTime;

    public GameQuestion() {
        super();
    }
    public GameQuestion(long newid) {
        super();
        this.ID= newid;
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
	public int getVersion() {
		return Version;
	}
	public void setVersion(int version) {
		Version = version;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
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
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getFailCount() {
		return FailCount;
	}
	public void setFailCount(int failCount) {
		FailCount = failCount;
	}
	public int getRightCount() {
		return RightCount;
	}
	public void setRightCount(int rightCount) {
		RightCount = rightCount;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getContinuedTime() {
		return ContinuedTime;
	}
	public void setContinuedTime(int continuedTime) {
		ContinuedTime = continuedTime;
	}
	
}