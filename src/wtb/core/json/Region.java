package wtb.core.json;

import java.util.List;

public class Region {
	public String iD;
	public String name;
	public String parentID;
	public int level;
	
	public List<Region> regin; 
	public String getiD() {
		return iD;
	}
	public void setiD(String iD) {
		this.iD = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public List<Region> getRegin() {
		return regin;
	}
	public void setRegin(List<Region> regin) {
		this.regin = regin;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	

	
	

	
	
}
