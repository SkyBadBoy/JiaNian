package wtb.core.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import wtb.smUtil.VerifyCode;

/**
 * Created by MeetLucky on 16/5/21.
 */
public class OnLine { // 在线信息
    //  属性名称
    public static final String onLineClass = "onLine";
    public static final String attributeOnLineID = "iD";
    public static final String attributeOnLineStudentID = "studentID";
    public static final String attributeOnLineSession = "session";
    public static final String attributeOnLineCreateTime = "createTime";
    public static final String attributeOnLineModifyTime = "modifyTime";
    public static final String attributeOnLineVersion = "version";
    public static final String attributeOnLineStatus = "status";
    public static final String attributeOnLineDeleted = "deleted";

    private long iD;//在线编号
    private long studentID;//学生编号
    private String session;//会话编号
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间

    @JSONField(serialize=false)
    private int version;//修改次数
    @JSONField(serialize=false)
    private int status;//状态信息
    @JSONField(serialize=false)
    private int deleted;//是否删除


    public OnLine() {
        super();
        this.iD= VerifyCode.CreateNewID();
    }

    public OnLine(long UserID) {
        super();
        this.studentID = UserID;
    }

    public long getID() {
        return iD;
    }

    public void setID(long iD) {
        this.iD = iD;
    }

 

    public long getiD() {
		return iD;
	}

	public void setiD(long iD) {
		this.iD = iD;
	}

	public long getStudentID() {
		return studentID;
	}

	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}

	public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}