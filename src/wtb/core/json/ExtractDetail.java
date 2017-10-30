package wtb.core.json;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ExtractDetail {
	
	private Long ID;//卡卷id
	
	private Integer prizeUsecount;//奖品投入个数
	
	private Integer prizeResiduecount;//发放的剩余个数
	
	private BigDecimal totalprobability;//总概率按照卡卷种类区分的
	
	private Date prizeVaildstarttime;//有效开始时间

    private Date prizeVaildendtime;//有效结束时间
    
    private String prizeRolltitle;//卡卷标题

    private String prizeRolldetails;//卡卷内容

    private String prizeRollremark;//卡卷备注

    private BigDecimal prizeDiscount;//折扣

    private Integer prizeType;//奖品类型
    
	
	private Integer studentstatResiduecount;//剩余抽奖次数
	
	private Integer studentstatObtainedcount;//次数限制
	
	private List<ExtractUIForUsers> users;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
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

	public BigDecimal getTotalprobability() {
		return totalprobability;
	}

	public void setTotalprobability(BigDecimal totalprobability) {
		this.totalprobability = totalprobability;
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
		this.prizeRolltitle = prizeRolltitle;
	}

	public String getPrizeRolldetails() {
		return prizeRolldetails;
	}

	public void setPrizeRolldetails(String prizeRolldetails) {
		this.prizeRolldetails = prizeRolldetails;
	}

	public String getPrizeRollremark() {
		return prizeRollremark;
	}

	public void setPrizeRollremark(String prizeRollremark) {
		this.prizeRollremark = prizeRollremark;
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

	public Integer getStudentstatResiduecount() {
		return studentstatResiduecount;
	}

	public void setStudentstatResiduecount(Integer studentstatResiduecount) {
		this.studentstatResiduecount = studentstatResiduecount;
	}

	public Integer getStudentstatObtainedcount() {
		return studentstatObtainedcount;
	}

	public void setStudentstatObtainedcount(Integer studentstatObtainedcount) {
		this.studentstatObtainedcount = studentstatObtainedcount;
	}

	public List<ExtractUIForUsers> getUsers() {
		return users;
	}

	public void setUsers(List<ExtractUIForUsers> users) {
		this.users = users;
	}
	
	
	
}
