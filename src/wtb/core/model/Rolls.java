package wtb.core.model;

import java.util.Date;

public class Rolls {
	private Long ID;

    private String PKID;

    private String rollMarkid;

    private Long rollUserid;

    private Long rollBelongid;

    private Integer rollType;

    private Integer rollStatus;

    private Integer rollDelete;

    private Long rollPrizeid;

    private String rollRedundancy;

    private Date rollCreatetime;

    private Date rollModifytime;
    
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

	public String getRollMarkid() {
        return rollMarkid;
    }

    public void setRollMarkid(String rollMarkid) {
        this.rollMarkid = rollMarkid == null ? null : rollMarkid.trim();
    }

    public Long getRollUserid() {
        return rollUserid;
    }

    public void setRollUserid(Long rollUserid) {
        this.rollUserid = rollUserid;
    }

    public Long getRollBelongid() {
        return rollBelongid;
    }

    public void setRollBelongid(Long rollBelongid) {
        this.rollBelongid = rollBelongid;
    }

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
    }

    public Integer getRollStatus() {
        return rollStatus;
    }

    public void setRollStatus(Integer rollStatus) {
        this.rollStatus = rollStatus;
    }

    public Integer getRollDelete() {
        return rollDelete;
    }

    public void setRollDelete(Integer rollDelete) {
        this.rollDelete = rollDelete;
    }

    public Long getRollPrizeid() {
        return rollPrizeid;
    }

    public void setRollPrizeid(Long rollPrizeid) {
        this.rollPrizeid = rollPrizeid;
    }

    public String getRollRedundancy() {
        return rollRedundancy;
    }

    public void setRollRedundancy(String rollRedundancy) {
        this.rollRedundancy = rollRedundancy == null ? null : rollRedundancy.trim();
    }

    public Date getRollCreatetime() {
        return rollCreatetime;
    }

    public void setRollCreatetime(Date rollCreatetime) {
        this.rollCreatetime = rollCreatetime;
    }

    public Date getRollModifytime() {
        return rollModifytime;
    }

    public void setRollModifytime(Date rollModifytime) {
        this.rollModifytime = rollModifytime;
    }
}