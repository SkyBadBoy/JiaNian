package wtb.core.model;


/**
 * Created by SASR on 17/06/03.
 */
public class QuestionRecord { 
    //  属性名称
    public static final String QuestionRecordClass = "QuestionRecord";
    public static final String attributeQuestionRecordID = "ID";
    public static final String attributeQuestionRecordCreateTime = "CreateTime";
    public static final String attributeQuestionRecordModifyTime = "ModifyTime";
    public static final String attributeQuestionRecordVersion = "Version";
    public static final String attributeQuestionRecordStatus = "Status";
    public static final String attributeQuestionRecordMemo = "Memo";
    public static final String attributeQuestionRecordStudentID = "StudentID";
    public static final String attributeQuestionRecordQuestionID = "QuestionID";
    public static final String attributeQuestionRecordAnswerResult = "AnswerResult";
    public static final String attributeQuestionRecordType = "Type";
    public static final String attributeQuestionRecordContinuedTime = "ContinuedTime";
    

    private long ID;//在线编号
    private int Version;//修改次数
    private int Status;//状态信息
    private String Memo;//是否删除
    private String CreateTime;//创建时间
    private String ModifyTime;//修改时间
    private long StudentID;//答案
    private long QuestionID;//标题
    private int AnswerResult;
    private int Type;
    private int ContinuedTime;

    public QuestionRecord() {
        super();
    }
    public QuestionRecord(long newid) {
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
	public long getQuestionID() {
		return QuestionID;
	}
	public void setQuestionID(long questionID) {
		QuestionID = questionID;
	}
	public int getAnswerResult() {
		return AnswerResult;
	}
	public void setAnswerResult(int answerResult) {
		AnswerResult = answerResult;
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