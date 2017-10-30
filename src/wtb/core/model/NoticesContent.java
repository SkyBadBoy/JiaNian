
/**
 * @Author 作者：马健
 * @Phone  联系qq：1039510196
 * @CreateTime 创建时间：2017年5月15日 下午3:53:06
 * @Details
 */
package wtb.core.model;

public class NoticesContent {

	
	private long ID;
	private String PKID;
	private String Content;
	private String CreateTime;
	private String ModifyTime;
	private int Status;
	private String Memo;
	private int Num;
	private long SrcID;
	
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
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(String modifyTime) {
		ModifyTime = modifyTime;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public int getNum() {
		return Num;
	}
	public void setNum(int num) {
		Num = num;
	}
	public long getSrcID() {
		return SrcID;
	}
	public void setSrcID(long srcID) {
		SrcID = srcID;
	}
	
	
}
