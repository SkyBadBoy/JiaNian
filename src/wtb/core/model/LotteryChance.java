package wtb.core.model;

import java.util.Date;

public class LotteryChance {
    private Long ID;
    
    private String PKID;

    private Long chanceUserid;

    private int chanceBelongid;

    private Integer chanceType;

    private Integer chanceDelete;

    private String chanceRedundancy;

    private Date chanceCreatetime;

    private Date chanceModifytime;
    
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

	public Long getChanceUserid() {
        return chanceUserid;
    }

    public void setChanceUserid(Long chanceUserid) {
        this.chanceUserid = chanceUserid;
    }

    public int getChanceBelongid() {
		return chanceBelongid;
	}

	public void setChanceBelongid(int chanceBelongid) {
		this.chanceBelongid = chanceBelongid;
	}

	public Integer getChanceType() {
        return chanceType;
    }

    public void setChanceType(Integer chanceType) {
        this.chanceType = chanceType;
    }

    public Integer getChanceDelete() {
        return chanceDelete;
    }

    public void setChanceDelete(Integer chanceDelete) {
        this.chanceDelete = chanceDelete;
    }

    public String getChanceRedundancy() {
        return chanceRedundancy;
    }

    public void setChanceRedundancy(String chanceRedundancy) {
        this.chanceRedundancy = chanceRedundancy == null ? null : chanceRedundancy.trim();
    }

    public Date getChanceCreatetime() {
        return chanceCreatetime;
    }

    public void setChanceCreatetime(Date chanceCreatetime) {
        this.chanceCreatetime = chanceCreatetime;
    }

    public Date getChanceModifytime() {
        return chanceModifytime;
    }

    public void setChanceModifytime(Date chanceModifytime) {
        this.chanceModifytime = chanceModifytime;
    }
}