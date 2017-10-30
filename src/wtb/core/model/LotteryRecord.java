package wtb.core.model;

import java.util.Date;

public class LotteryRecord {
	private Long ID;

    private String PKID;

    private Long recordUserid;

    private Long recordBelongid;
    
    private Integer recordType;

    private Integer recordDelete;

    private Integer recordStatus;

    private String recordRedundancy;

    private String recordCreatetime;

    private String recordModifytime;
    
    private Students student;
    

    public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getPKID() {
		return PKID;
	}

	public void setPKID(String pKID) {
		PKID = pKID;
	}

	public Long getRecordUserid() {
        return recordUserid;
    }

    public void setRecordUserid(Long recordUserid) {
        this.recordUserid = recordUserid;
    }

    public Long getRecordBelongid() {
        return recordBelongid;
    }

    public void setRecordBelongid(Long recordBelongid) {
        this.recordBelongid = recordBelongid;
    }

    public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Integer getRecordDelete() {
        return recordDelete;
    }

    public void setRecordDelete(Integer recordDelete) {
        this.recordDelete = recordDelete;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordRedundancy() {
        return recordRedundancy;
    }

    public void setRecordRedundancy(String recordRedundancy) {
        this.recordRedundancy = recordRedundancy == null ? null : recordRedundancy.trim();
    }

    public String getRecordCreatetime() {
        return recordCreatetime;
    }

    public void setRecordCreatetime(String recordCreatetime) {
        this.recordCreatetime = recordCreatetime;
    }

    public String getRecordModifytime() {
        return recordModifytime;
    }

    public void setRecordModifytime(String recordModifytime) {
        this.recordModifytime = recordModifytime;
    }

	/**
	 * @return the student
	 */
	public Students getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Students student) {
		this.student = student;
	}
    
    
}