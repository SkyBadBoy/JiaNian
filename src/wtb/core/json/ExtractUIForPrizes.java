package wtb.core.json;

import java.math.BigDecimal;
import java.util.Date;

public class ExtractUIForPrizes {
	private long ID;//卡卷id
	
	private Integer Usecount;//奖品投入个数
	
	private Integer Residuecount;//发放的剩余个数
	
	private BigDecimal totalprobability;//总概率按照卡卷种类区分的
	
	private String Vaildstarttime;//有效开始时间

    private String Vaildendtime;//有效结束时间
    
    private String Rolltitle;//卡卷标题

    private String Rolldetails;//卡卷内容

    private String Rollremark;//卡卷备注

    private BigDecimal Discount;//折扣

    private Integer Type;//奖品类型

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Integer getUsecount() {
		return Usecount;
	}

	public void setUsecount(Integer usecount) {
		Usecount = usecount;
	}

	public Integer getResiduecount() {
		return Residuecount;
	}

	public void setResiduecount(Integer residuecount) {
		Residuecount = residuecount;
	}

	public BigDecimal getTotalprobability() {
		return totalprobability;
	}

	public void setTotalprobability(BigDecimal totalprobability) {
		this.totalprobability = totalprobability;
	}

	public String getVaildstarttime() {
		return Vaildstarttime;
	}

	public void setVaildstarttime(String vaildstarttime) {
		Vaildstarttime = vaildstarttime;
	}

	public String getVaildendtime() {
		return Vaildendtime;
	}

	public void setVaildendtime(String vaildendtime) {
		Vaildendtime = vaildendtime;
	}

	public String getRolltitle() {
		return Rolltitle;
	}

	public void setRolltitle(String rolltitle) {
		Rolltitle = rolltitle;
	}

	public String getRolldetails() {
		return Rolldetails;
	}

	public void setRolldetails(String rolldetails) {
		Rolldetails = rolldetails;
	}

	public String getRollremark() {
		return Rollremark;
	}

	public void setRollremark(String rollremark) {
		Rollremark = rollremark;
	}

	public BigDecimal getDiscount() {
		return Discount;
	}

	public void setDiscount(BigDecimal discount) {
		Discount = discount;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

}
