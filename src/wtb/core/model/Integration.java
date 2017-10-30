package wtb.core.model;

import java.util.Date;

public class Integration 
{
	private long ID;
	private String CreateTime; 
	private int Status;
	private String Reason;
	private int Num;
	private long StudentID;
	private long SrcID;
	private int Type; //调整的积分类型 1表示增加 2表示减少
	private Students Student;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}

	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public int getNum() {
		return Num;
	}
	public void setNum(int num) {
		Num = num;
	}
	public long getStudentID() {
		return StudentID;
	}
	public void setStudentID(long studentID) {
		StudentID = studentID;
	}
	public long getSrcID() {
		return SrcID;
	}
	public void setSrcID(long srcID) {
		SrcID = srcID;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public Students getStudent() {
		return Student;
	}
	public void setStudent(Students student) {
		Student = student;
	}
	
}
