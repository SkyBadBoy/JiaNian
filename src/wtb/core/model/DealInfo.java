package wtb.core.model;

public class DealInfo {
	public DealInfo() {
		super();
	}
	private long ID;
	private String PKID;
	private int Type;
	private long ParentID;
	private long ResultStatus;
	private String Result;
	private long CheckerID;
	private String CreateTime;
	private BaseInfo BaseInfo;
	
	private Users Checker;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getPKID() {
		return PKID;
	}

	public void setPKID(String pKID) {
		PKID = pKID;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public long getParentID() {
		return ParentID;
	}

	public void setParentID(long parentID) {
		ParentID = parentID;
	}

	public long getResultStatus() {
		return ResultStatus;
	}

	public void setResultStatus(long resultStatus) {
		ResultStatus = resultStatus;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public long getCheckerID() {
		return CheckerID;
	}

	public void setCheckerID(long checkerID) {
		CheckerID = checkerID;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public BaseInfo getBaseInfo() {
		return BaseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		BaseInfo = baseInfo;
	}

	public Users getChecker() {
		return Checker;
	}

	public void setChecker(Users checker) {
		Checker = checker;
	}
	
	

}
