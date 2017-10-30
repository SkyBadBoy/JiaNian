package wtb.core.model;

import java.util.Date;

/**
 * Created by SASR on 17/06/03.
 */
public class QuestionLimit { 
    //  属性名称
    public static final String QuestionLimitClass = "QuestionLimit";
    public static final String attributeQuestionLimitID = "ID";
    public static final String attributeQuestionLimitCreateTime = "CreateTime";
    public static final String attributeQuestionLimitModifyTime = "ModifyTime";
    public static final String attributeQuestionLimitVersion = "Version";
    public static final String attributeQuestionLimitStatus = "Status";
    public static final String attributeQuestionLimitMemo = "Memo";
    public static final String attributeQuestionLimitStudentID = "StudentID";
    public static final String attributeQuestionLimitLimitNumber = "LimitNumber";
    public static final String attributeQuestionLimitLimitTime = "LimitTime";
    public static final String attributeQuestionLimitType = "Type";
    public static final String attributeQuestionLimitLimitNo = "LimitNo";
    

    private long ID;//在线编号
    private int Version;//修改次数
    private int Status;//状态信息
    private String Memo;//是否删除
    private String CreateTime;//创建时间
    private String ModifyTime;//修改时间
    private long StudentID;//答案
    private int LimitNumber;//允许的免费答题最大值
    private Date LimitTime;//免费使用的最终时间，和上面那个二选一，有时间的以时间的为准
    private int Type;
    private String LimitNo;

    public QuestionLimit() {
        super();
    }
    public QuestionLimit(long newid) {
        super();
        this.ID= newid;
    }
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
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
	public long getStudentID() {
		return StudentID;
	}
	public void setStudentID(long studentID) {
		StudentID = studentID;
	}
	public int getLimitNumber() {
		return LimitNumber;
	}
	public void setLimitNumber(int limitNumber) {
		LimitNumber = limitNumber;
	}
	public Date getLimitTime() {
		return LimitTime;
	}
	public void setLimitTime(Date limitTime) {
		LimitTime = limitTime;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getLimitNo() {
		return LimitNo;
	}
	public void setLimitNo(String limitNo) {
		LimitNo = limitNo;
	}
	
	
    
}