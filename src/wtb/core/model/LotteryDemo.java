package wtb.core.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LotteryDemo implements Serializable{
	private long DemoId;
	//奖品内容
	private String Award;
	//概率  %
	private BigDecimal Percent;
	//奖品类型
	private int type;
	//奖品数额
	private BigDecimal Discount;
	public long getDemoId() {
		return DemoId;
	}
	public void setDemoId(long demoId) {
		DemoId = demoId;
	}
	public String getAward() {
		return Award;
	}
	public void setAward(String award) {
		Award = award;
	}
	public BigDecimal getPercent() {
		return Percent;
	}
	public void setPercent(BigDecimal percent) {
		Percent = percent;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public BigDecimal getDiscount() {
		return Discount;
	}
	public void setDiscount(BigDecimal discount) {
		Discount = discount;
	}
	
	
}
