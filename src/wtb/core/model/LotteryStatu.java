package wtb.core.model;

import java.util.Date;

public class LotteryStatu {
    private Long lotteryId;

    private Long lotteryUserid;

    private Long lotteryBelongid;

    private Integer lotteryResiduecount;

    private Integer lotteryObtainedcount;

    private Integer lotteryTotalcount;

    private String lotteryRedundancy;

    private Date lotteryCreatetime;

    private Date lotteryModifytime;

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Long getLotteryUserid() {
        return lotteryUserid;
    }

    public void setLotteryUserid(Long lotteryUserid) {
        this.lotteryUserid = lotteryUserid;
    }

    public Long getLotteryBelongid() {
        return lotteryBelongid;
    }

    public void setLotteryBelongid(Long lotteryBelongid) {
        this.lotteryBelongid = lotteryBelongid;
    }

    public Integer getLotteryResiduecount() {
		return lotteryResiduecount;
	}

	public void setLotteryResiduecount(Integer lotteryResiduecount) {
		this.lotteryResiduecount = lotteryResiduecount;
	}

	public Integer getLotteryObtainedcount() {
        return lotteryObtainedcount;
    }

    public void setLotteryObtainedcount(Integer lotteryObtainedcount) {
        this.lotteryObtainedcount = lotteryObtainedcount;
    }

    public Integer getLotteryTotalcount() {
        return lotteryTotalcount;
    }

    public void setLotteryTotalcount(Integer lotteryTotalcount) {
        this.lotteryTotalcount = lotteryTotalcount;
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