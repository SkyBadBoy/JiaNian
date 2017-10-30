package wtb.core.json;

import java.util.Date;

public class ReturnRedPacketList {
	private long ID;
	
	private long UserId;

    private Integer Source;

    private long Belongid;

    private String Time;
    
    private int Type;
    
    private int Status;
    
    private String ByName;
    
    private String ByUrl;
    
    private String ByPhone;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	public Integer getSource() {
		return Source;
	}

	public void setSource(Integer source) {
		Source = source;
	}

	public long getBelongid() {
		return Belongid;
	}

	public void setBelongid(long belongid) {
		Belongid = belongid;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getByName() {
		return ByName;
	}

	public void setByName(String byName) {
		ByName = byName;
	}

	public String getByUrl() {
		return ByUrl;
	}

	public void setByUrl(String byUrl) {
		ByUrl = byUrl;
	}

	public String getByPhone() {
		return ByPhone;
	}

	public void setByPhone(String byPhone) {
		ByPhone = byPhone;
	}

}