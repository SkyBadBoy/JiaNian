package wtb.core.service;

import wtb.core.data.LotteryRecordMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.LotteryRecord;

@Service
public class LotteryRecordService {
	
	 
	 @Autowired
	 private LotteryRecordMapper mapper;
	 
	 @Transactional
	 public int AddNewMessage(LotteryRecord record) {
		 return mapper.AddNewMessage(record);
	 }
	
	
}