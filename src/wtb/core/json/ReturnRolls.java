package wtb.core.json;

import java.math.BigDecimal;
import java.util.Date;

public class ReturnRolls {
	private Long ID;
	
	private String title;
	
	private String detail;
	
	private BigDecimal discount;
	
//	private String dcstring;
	
	private String remark;

	private String markId;
	
//	private int efficientCount;
	
	private int type;

    private Integer Status;

    private String startTime;
    
    private String endTime;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

//	public String getDcstring() {
//		return dcstring;
//	}
//
//	public void setDcstring(String dcstring) {
//		this.dcstring = dcstring;
//	}

	public String getMarkId() {
		return markId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

//	public int getEfficientCount() {
//		return efficientCount;
//	}
//
//	public void setEfficientCount(int efficientCount) {
//		this.efficientCount = efficientCount;
//	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}