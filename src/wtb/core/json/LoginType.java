package wtb.core.json;

/**
 * 登录类型
 * @author Mj
 *
 */
public class LoginType {
	private String Code;
	private String Message;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getMessage() {
		switch (this.Code) {
		case "0":
			Message="无需登录";
			break;
		case "1":
			Message="微信登录";
			break;
		case "2":
			Message="需要绑定登录";
			break;
		}
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
}
