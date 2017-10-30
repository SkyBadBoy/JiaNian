package read.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadLotteryChanceMapper;

@Service
public class ReadLotteryChanceService {
	
	 @Autowired
	private ReadLotteryChanceMapper mapper;
	
	@Transactional
	public int QueryCount(Map<String, Object> map){
		return mapper.QueryCount(map);
	}
	
	@Transactional
	public int QueryBelong(Map<String, Object> map) {
		return mapper.QueryBelong(map);
	}


	
}