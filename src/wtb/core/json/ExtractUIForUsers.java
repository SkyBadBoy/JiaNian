package wtb.core.json;

public class ExtractUIForUsers {
	private Long ID;
    
	private String Name;
	
	private String ImageUrl;

	private String Phone;

	private Integer prizeResiduecount;//发放的剩余个数

	private String prizeRolltitle;//卡卷标题
	
	private String prizeCreatetime;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public Integer getPrizeResiduecount() {
		return prizeResiduecount;
	}

	public void setPrizeResiduecount(Integer prizeResiduecount) {
		this.prizeResiduecount = prizeResiduecount;
	}

	public String getPrizeRolltitle() {
		return prizeRolltitle;
	}

	public void setPrizeRolltitle(String prizeRolltitle) {
		this.prizeRolltitle = prizeRolltitle;
	}

	public String getPrizeCreatetime() {
		return prizeCreatetime;
	}

	public void setPrizeCreatetime(String prizeCreatetime) {
		this.prizeCreatetime = prizeCreatetime;
	}

}
