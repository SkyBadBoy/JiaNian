package wtb.core.model;


/**
 * Created by SASR on 17/06/03.
 */
public class QuestionStat { 
    //  属性名称
    public static final String QuestionStatClass = "QuestionStat";
    public static final String attributeQuestionStatID = "ID";
    public static final String attributeQuestionStatCreateTime = "CreateTime";
    public static final String attributeQuestionStatModifyTime = "ModifyTime";
    public static final String attributeQuestionStatVersion = "Version";
    public static final String attributeQuestionStatStatus = "Status";
    public static final String attributeQuestionStatMemo = "Memo";
    public static final String attributeQuestionStatStudentID = "StudentID";
    public static final String attributeQuestionStatRightCount = "RightCount";
    public static final String attributeQuestionStatFailCount = "FailCount";
    public static final String attributeQuestionStatType = "Type";
    public static final String attributeQuestionStatIntegral = "Integral";
    
/*
 	QuestionStat_ID,
	QuestionStat_Status,
	QuestionStat_Memo,
	QuestionStat_ModifyTIme,
	QuestionStat_CreateTime,
	QuestionStat_Version,
	QuestionStat_StudentID,
	QuestionStat_RightCount,
	QuestionStat_FailCount,
	QuestionStat_Type,
	QuestionStat_Integral
 */
    private long ID;//在线编号
    private int Version;//修改次数
    private int Status;//状态信息
    private String Memo;//是否删除
    private String CreateTime;//创建时间
    private String ModifyTime;//修改时间
    private long StudentID;//答案
    private int RightCount;//标题
    private int FailCount;
    private int Type;
    private int Integral;
    private int Level;
    private Students Student;//答案

    public QuestionStat() {
        super();
    }
    public QuestionStat(long newid) {
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
	public int getRightCount() {
		return RightCount;
	}
	public void setRightCount(int rightCount) {
		RightCount = rightCount;
	}
	public int getFailCount() {
		return FailCount;
	}
	public void setFailCount(int failCount) {
		FailCount = failCount;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getIntegral() {
		return Integral;
	}
	public void setIntegral(int integral) {
		Integral = integral;
	}
	public Students getStudent() {
		return Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	
    
}