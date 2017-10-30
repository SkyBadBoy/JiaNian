package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.ErrorSendMapper;
import wtb.core.model.ErrorSend;

@Service
public class ErrorSendService {
	@Autowired
	private ErrorSendMapper mapper;
	@Transactional(readOnly = true)
	public List<ErrorSend> getErrorSendList(Map<String, Object> map)
	{
		return mapper.getErrorSendList(map);
	}
	@Transactional
	public int getErrorSendCount(Map<String, Object> map){
		return mapper.getErrorSendCount(map);
	}
	@Transactional
	public int addErrorSend(ErrorSend ErrorSend){
		return mapper.addErrorSend(ErrorSend);
	}
	@Transactional
	public int deleteErrorSend(Map<String , Object> map){
		return mapper.deleteErrorSend(map);
	}
	@Transactional
	public int updateErrorSend(ErrorSend ErrorSend){
		
		return mapper.updateErrorSend(ErrorSend);
	}
}
