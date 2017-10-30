package wtb.core.model;

@SuppressWarnings("serial")
public class BaseInfo implements java.io.Serializable{
	public BaseInfo() {
		super();
	}
	private long ID;
	private String PKID;
	private String Name;
	private String Memo;
	private long ParentID;
	private int Status;
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public long getParentID() {
		return ParentID;
	}
	public void setParentID(long parentID) {
		ParentID = parentID;
	}
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	
	
}
