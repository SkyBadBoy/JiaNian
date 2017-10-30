package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.ErrorSend;

public interface ErrorSendMapper {
	public List<ErrorSend> getErrorSendList(Map<String, Object> map);
	public int getErrorSendCount(Map<String, Object> map);
	public int addErrorSend(ErrorSend ErrorSend);
	public int deleteErrorSend(Map<String , Object> map);
	public int updateErrorSend(ErrorSend ErrorSend);
}
