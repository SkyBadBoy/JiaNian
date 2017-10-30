package wtb.core.model;

import java.util.Date;

public class ChanceRecord {
    private Long recordId;

    private Long recordUserid;

    private Long recordBelongid;

    private String recordReason;

    private Integer recordType;

    private Integer recordDelete;

    private String recordRedundancy;

    private Date recordCreatetime;

    private Date recordModifytime;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public String getRecordReason() {
        return recordReason;
    }

    public void setRecordReason(String recordReason) {
        this.recordReason = recordReason == null ? null : recordReason.trim();
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

    public String getRecordRedundancy() {
        return recordRedundancy;
    }

    public void setRecordRedundancy(String recordRedundancy) {
        this.recordRedundancy = recordRedundancy == null ? null : recordRedundancy.trim();
    }

    public Date getRecordCreatetime() {
        return recordCreatetime;
    }

    public void setRecordCreatetime(Date recordCreatetime) {
        this.recordCreatetime = recordCreatetime;
    }

    public Date getRecordModifytime() {
        return recordModifytime;
    }

    public void setRecordModifytime(Date recordModifytime) {
        this.recordModifytime = recordModifytime;
    }
}