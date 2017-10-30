package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.ErrorStatMapper;
import wtb.core.model.ErrorStat;

@Service
public class ErrorStatService {
	@Autowired
	private ErrorStatMapper mapper;

	@Transactional
	public int addErrorStat(ErrorStat ErrorStat){
		return mapper.addErrorStat(ErrorStat);
	}
	@Transactional
	public int deleteErrorStat(Map<String , Object> map){
		return mapper.deleteErrorStat(map);
	}
	@Transactional
	public int updateErrorStat(ErrorStat ErrorStat){
		
		return mapper.updateErrorStat(ErrorStat);
	}
	
	@Transactional
	public int updateErrorStatByClassName(long ErrorStat){
		
		return mapper.updateErrorStatByClassName(ErrorStat);
	}
}
