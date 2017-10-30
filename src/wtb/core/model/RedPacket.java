package wtb.core.model;

import java.util.Date;

public class RedPacket {
	private Long ID;

    private String PKID;

    private Long redpacketUserid;

    private Integer redpacketSource;

    private Long redpacketReceiveid;

    private Integer redpacketLotterynumber;

    private Long redpacketBelongid;

    private Integer redpacketType;

    private Integer redpacketStatus;

    private Integer redpacketDelete;

    private String redpacketRedundancy;

    private Date redpacketCreatetime;

    private Date redpacketModifytime;

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

	public Long getRedpacketUserid() {
        return redpacketUserid;
    }

    public void setRedpacketUserid(Long redpacketUserid) {
        this.redpacketUserid = redpacketUserid;
    }

    public Integer getRedpacketSource() {
        return redpacketSource;
    }

    public void setRedpacketSource(Integer redpacketSource) {
        this.redpacketSource = redpacketSource;
    }

    public Long getRedpacketReceiveid() {
        return redpacketReceiveid;
    }

    public void setRedpacketReceiveid(Long redpacketReceiveid) {
        this.redpacketReceiveid = redpacketReceiveid;
    }

    public Integer getRedpacketLotterynumber() {
        return redpacketLotterynumber;
    }

    public void setRedpacketLotterynumber(Integer redpacketLotterynumber) {
        this.redpacketLotterynumber = redpacketLotterynumber;
    }

    public Long getRedpacketBelongid() {
        return redpacketBelongid;
    }

    public void setRedpacketBelongid(Long redpacketBelongid) {
        this.redpacketBelongid = redpacketBelongid;
    }

    public Integer getRedpacketType() {
        return redpacketType;
    }

    public void setRedpacketType(Integer redpacketType) {
        this.redpacketType = redpacketType;
    }

    public Integer getRedpacketStatus() {
        return redpacketStatus;
    }

    public void setRedpacketStatus(Integer redpacketStatus) {
        this.redpacketStatus = redpacketStatus;
    }

    public Integer getRedpacketDelete() {
        return redpacketDelete;
    }

    public void setRedpacketDelete(Integer redpacketDelete) {
        this.redpacketDelete = redpacketDelete;
    }

    public String getRedpacketRedundancy() {
        return redpacketRedundancy;
    }

    public void setRedpacketRedundancy(String redpacketRedundancy) {
        this.redpacketRedundancy = redpacketRedundancy == null ? null : redpacketRedundancy.trim();
    }

    public Date getRedpacketCreatetime() {
        return redpacketCreatetime;
    }

    public void setRedpacketCreatetime(Date redpacketCreatetime) {
        this.redpacketCreatetime = redpacketCreatetime;
    }

    public Date getRedpacketModifytime() {
        return redpacketModifytime;
    }

    public void setRedpacketModifytime(Date redpacketModifytime) {
        this.redpacketModifytime = redpacketModifytime;
    }
}