package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadLotteryRecordMapper;
import wtb.core.model.LotteryRecord;

 @Service
public class ReadLotteryRecordService {
	
	 @Autowired
	private ReadLotteryRecordMapper mapper;
	
	@Transactional
	public List<LotteryRecord> QueryRecordAll(Map<String, Object> map){
		return mapper.QueryRecordAll(map);
	}
	
	@Transactional
	public List<LotteryRecord> QueryMyAll(Map<String, Object> map){
		return mapper.QueryMyAll(map);
	}

	
}