package wtb.core.model;

import java.util.Date;

public class LotteryNumber {
    private Long ID;

    private String PKID;
    
    private Long lotteryUserid;

    private Long lotteryNumber;

    private Integer lotteryDelete;

    private Integer lotteryStatus;

    private String lotteryRedundancy;

    private Date lotteryCreatetime;

    private Date lotteryModifytime;

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

    public Long getLotteryUserid() {
        return lotteryUserid;
    }

    public void setLotteryUserid(Long lotteryUserid) {
        this.lotteryUserid = lotteryUserid;
    }

    public Long getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(Long lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public Integer getLotteryDelete() {
        return lotteryDelete;
    }

    public void setLotteryDelete(Integer lotteryDelete) {
        this.lotteryDelete = lotteryDelete;
    }

    public Integer getLotteryStatus() {
        return lotteryStatus;
    }

    public void setLotteryStatus(Integer lotteryStatus) {
        this.lotteryStatus = lotteryStatus;
    }

    public String getLotteryRedundancy() {
        return lotteryRedundancy;
    }

    public void setLotteryRedundancy(String lotteryRedundancy) {
        this.lotteryRedundancy = lotteryRedundancy == null ? null : lotteryRedundancy.trim();
    }

    public Date getLotteryCreatetime() {
        return lotteryCreatetime;
    }

    public void setLotteryCreatetime(Date lotteryCreatetime) {
        this.lotteryCreatetime = lotteryCreatetime;
    }

    public Date getLotteryModifytime() {
        return lotteryModifytime;
    }

    public void setLotteryModifytime(Date lotteryModifytime) {
        this.lotteryModifytime = lotteryModifytime;
    }
}