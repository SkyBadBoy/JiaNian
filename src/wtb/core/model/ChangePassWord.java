package wtb.core.model;

public class ChangePassWord {
	public ChangePassWord() {
		super();
	}
	private String UserName ;
	private String OldPassWord;
	private String NewPassWord;
	private String Confirm_NewPassWord;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getOldPassWord() {
		return OldPassWord;
	}
	public void setOldPassWord(String oldPassWord) {
		OldPassWord = oldPassWord;
	}
	public String getNewPassWord() {
		return NewPassWord;
	}
	public void setNewPassWord(String newPassWord) {
		NewPassWord = newPassWord;
	}
	public String getConfirm_NewPassWord() {
		return Confirm_NewPassWord;
	}
	public void setConfirm_NewPassWord(String confirm_NewPassWord) {
		Confirm_NewPassWord = confirm_NewPassWord;
	}
	
	
	
}
