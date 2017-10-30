package wtb.core.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 
 */
@SuppressWarnings("unchecked")
public class VerifyRecord { // 登录验证

    public static final String verifyRecordClass = "verifyRecord";
    public static final String attributeVerifyRecordID = "iD";
    public static final String attributeVerifyRecordStudentID = "studentID";
    public static final String attributeVerifyRecordPhone = "phone";
    public static final String attributeVerifyRecordCheckNumber = "checkNumber";
    public static final String attributeVerifyRecordCreateTime = "createTime";
    public static final String attributeVerifyRecordModifyTime = "modifyTime";
    public static final String attributeVerifyRecordDisableTime = "disableTime";
    public static final String attributeVerifyRecordEnableTime = "enableTime";
    public static final String attributeVerifyRecordPreviousID = "previousID";
    public static final String attributeVerifyRecordVersion = "version";
    public static final String attributeVerifyRecordStatus = "status";
    public static final String attributeVerifyRecordDeleted = "deleted";

    //	用户信息
    private long iD;//记录编号
    private long studentID;//用户编号

    //	验证信息
    private String phone;//登录手机
    private String checkNumber;//验 证 码
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间
    private Date disableTime;//禁用时间
    private Date enableTime;//禁用时间
    private long previousID;//关联上次

    //	结构信息
    private int version;//修改次数
    private int status;//状态信息
    private int deleted;//是否删除





    public long getID() {
        return iD;
    }

    public void setID(long iD) {
        this.iD = iD;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

	public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
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

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public long getPreviousID() {
        return previousID;
    }

    public void setPreviousID(long previousID) {
        this.previousID = previousID;
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
