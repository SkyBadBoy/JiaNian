package read.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadLotteryStatuMapper;
import wtb.core.model.LotteryStatu;

 @Service
public class ReadLotteryStatuService {
	
	 @Autowired
	private ReadLotteryStatuMapper mapper;
	
	@Transactional
	public LotteryStatu QueryMessage(Map<String, Object> map) {
		return mapper.QueryMessage(map);
	}
	
	@Transactional
	public int QueryExist(long ID) {
		return mapper.QueryExist(ID);
	}
	
	
}