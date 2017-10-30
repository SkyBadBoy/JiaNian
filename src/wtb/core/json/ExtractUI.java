package wtb.core.json;

import java.util.List;

public class ExtractUI {
	private List<ExtractUIForPrizes> Prizes;
    
	private Integer Residuecount;//剩余抽奖次数
	
	private Integer Obtainedcount;//次数限制
	
	private List<ExtractUIForUsers> users;

	public List<ExtractUIForPrizes> getPrizes() {
		return Prizes;
	}

	public void setPrizes(List<ExtractUIForPrizes> prizes) {
		Prizes = prizes;
	}

	public Integer getResiduecount() {
		return Residuecount;
	}

	public void setResiduecount(Integer residuecount) {
		Residuecount = residuecount;
	}

	public Integer getObtainedcount() {
		return Obtainedcount;
	}

	public void setObtainedcount(Integer obtainedcount) {
		Obtainedcount = obtainedcount;
	}

	public List<ExtractUIForUsers> getUsers() {
		return users;
	}

	public void setUsers(List<ExtractUIForUsers> users) {
		this.users = users;
	}
	
	
	
}
