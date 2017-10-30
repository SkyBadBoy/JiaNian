package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.ErrorLog;

public interface ErrorLogMapper {
	public List<ErrorLog> getErrorLogIDList(Map<String , Object> map);
	public List<ErrorLog> getErrorLogList(Map<String , Object> map);
	public List<ErrorLog> getViewData(Map<String  , Object> map);
	public int getErrorLogCount(Map<String,Object> map);
	public int deleteErrorLog(Map<String , Object> map);
	public int addErrorLog(ErrorLog ErrorLog);
	public int noticeErrorLog(ErrorLog ErrorLog);
	public List<ErrorLog> getChartLineList(Map<String , Object> map);
	public List<ErrorLog> getChartLineStat(Map<String , Object> map);
	
}
