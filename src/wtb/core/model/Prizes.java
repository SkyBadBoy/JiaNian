package wtb.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class Prizes {
	private long ID;

//    private String PKID;
    
    private BigDecimal totalprobability;

    private BigDecimal prizeProbability;

    private Integer prizeTotal;

    private Integer prizeUsecount;//奖品投入个数

    private Integer prizeResiduecount;//发放的剩余个数

    private Integer prizeNotusecount;

    private long prizeMerchantid;

    private Date prizeVaildstarttime;//有效开始时间

    private Date prizeVaildendtime;//有效结束时间

    private String prizeRolltitle;//卡卷标题

    private String prizeRolldetails;//卡卷内容

    private String prizeRollremark;//卡卷备注

    private BigDecimal prizeDiscount;//折扣

    private Integer prizeType;//奖品类型
    
    private Integer prizeDelete;

    private String prizeRedundancy;

    private Date prizeCreatetime;

    private Date prizeModifytime;

    public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

//	public String getPKID() {
//		return PKID;
//	}
//
//	public void setPKID(String pKID) {
//		PKID = pKID;
//	}
	
	public BigDecimal getTotalprobability() {
		return totalprobability;
	}

	public void setTotalprobability(BigDecimal totalprobability) {
		this.totalprobability = totalprobability;
	}

	public BigDecimal getPrizeProbability() {
        return prizeProbability;
    }

    public void setPrizeProbability(BigDecimal prizeProbability) {
        this.prizeProbability = prizeProbability;
    }

    public Integer getPrizeTotal() {
        return prizeTotal;
    }

    public void setPrizeTotal(Integer prizeTotal) {
        this.prizeTotal = prizeTotal;
    }

    public Integer getPrizeUsecount() {
        return prizeUsecount;
    }

    public void setPrizeUsecount(Integer prizeUsecount) {
        this.prizeUsecount = prizeUsecount;
    }

    public Integer getPrizeResiduecount() {
        return prizeResiduecount;
    }

    public void setPrizeResiduecount(Integer prizeResiduecount) {
        this.prizeResiduecount = prizeResiduecount;
    }

    public Integer getPrizeNotusecount() {
        return prizeNotusecount;
    }

    public void setPrizeNotusecount(Integer prizeNotusecount) {
        this.prizeNotusecount = prizeNotusecount;
    }

    public long getPrizeMerchantid() {
        return prizeMerchantid;
    }

    public void setPrizeMerchantid(long prizeMerchantid) {
        this.prizeMerchantid = prizeMerchantid;
    }

    public Date getPrizeVaildstarttime() {
        return prizeVaildstarttime;
    }

    public void setPrizeVaildstarttime(Date prizeVaildstarttime) {
        this.prizeVaildstarttime = prizeVaildstarttime;
    }

    public Date getPrizeVaildendtime() {
        return prizeVaildendtime;
    }

    public void setPrizeVaildendtime(Date prizeVaildendtime) {
        this.prizeVaildendtime = prizeVaildendtime;
    }

    public String getPrizeRolltitle() {
        return prizeRolltitle;
    }

    public void setPrizeRolltitle(String prizeRolltitle) {
        this.prizeRolltitle = prizeRolltitle == null ? null : prizeRolltitle.trim();
    }

    public String getPrizeRolldetails() {
        return prizeRolldetails;
    }

    public void setPrizeRolldetails(String prizeRolldetails) {
        this.prizeRolldetails = prizeRolldetails == null ? null : prizeRolldetails.trim();
    }

    public String getPrizeRollremark() {
        return prizeRollremark;
    }

    public void setPrizeRollremark(String prizeRollremark) {
        this.prizeRollremark = prizeRollremark == null ? null : prizeRollremark.trim();
    }

    public BigDecimal getPrizeDiscount() {
        return prizeDiscount;
    }

    public void setPrizeDiscount(BigDecimal prizeDiscount) {
        this.prizeDiscount = prizeDiscount;
    }

    public Integer getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    public Integer getPrizeDelete() {
		return prizeDelete;
	}

	public void setPrizeDelete(Integer prizeDelete) {
		this.prizeDelete = prizeDelete;
	}

	public String getPrizeRedundancy() {
        return prizeRedundancy;
    }

    public void setPrizeRedundancy(String prizeRedundancy) {
        this.prizeRedundancy = prizeRedundancy == null ? null : prizeRedundancy.trim();
    }

    public Date getPrizeCreatetime() {
        return prizeCreatetime;
    }

    public void setPrizeCreatetime(Date prizeCreatetime) {
        this.prizeCreatetime = prizeCreatetime;
    }

    public Date getPrizeModifytime() {
        return prizeModifytime;
    }

    public void setPrizeModifytime(Date prizeModifytime) {
        this.prizeModifytime = prizeModifytime;
    }
}