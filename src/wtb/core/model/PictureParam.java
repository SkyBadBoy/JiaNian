package wtb.core.model;
/**
 * 检测图片是否符合要求的类
 * @author SASR_Studio
 *
 */
public class PictureParam {
	public PictureParam() {
		super();
	}
	private byte[] bytes;
	private String  fileName;
	private String prefix;
	private String SavefileName;
	private String ErrorMessage;
	private String OriginalFilename;
	
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public String getOriginalFilename() {
		return OriginalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		OriginalFilename = originalFilename;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSavefileName() {
		return SavefileName;
	}
	public void setSavefileName(String savefileName) {
		SavefileName = savefileName;
	}
	
	
	
	
	
}
