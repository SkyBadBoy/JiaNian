package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.ErrorLogMapper;
import wtb.core.model.ErrorLog;

@Service
public class ErrorLogService {
	
	@Autowired
	private ErrorLogMapper mapper;
	
	@Transactional(readOnly = true)
	public List<ErrorLog> getErrorLogIDList(Map<String , Object> map){
		return mapper.getErrorLogIDList(map);
	}
	
	@Transactional
	public List<ErrorLog> getErrorLogList(Map<String , Object> map)
	{
		return mapper.getErrorLogList(map);
	}
	@Transactional
	public List<ErrorLog> getViewData(Map<String , Object> map)
	{
		return mapper.getViewData(map);
	}
	
	@Transactional
	public int getErrorLogCount(Map<String,Object> map){
		return mapper.getErrorLogCount(map);
	}
	@Transactional
	public int deleteErrorLog(Map<String , Object> map){
		return mapper.deleteErrorLog(map);
	}
	@Transactional
	public int addErrorLog(ErrorLog ErrorLog){
		return mapper.addErrorLog(ErrorLog);
	}
	@Transactional
	public int noticeErrorLog(ErrorLog ErrorLog)
	{
		return mapper.noticeErrorLog(ErrorLog);
	}
	
	@Transactional
	public List<ErrorLog> getChartLineList(Map<String , Object> map){
		return mapper.getChartLineList(map);
	}
	@Transactional
	public List<ErrorLog> getChartLineStat(Map<String , Object> map){
		return mapper.getChartLineStat(map);
	}
}
