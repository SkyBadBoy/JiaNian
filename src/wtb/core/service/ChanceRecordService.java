package wtb.core.service;
import wtb.core.data.ChanceRecordMapper;
import wtb.core.model.ChanceRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * wtb_vitaes
 */
 @Service
public class ChanceRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	 private ChanceRecordMapper mapper;
	 
	 @Transactional
	 public int AddNewMessage(ChanceRecord record)  {
		 return mapper.AddNewMessage(record);
	 }

	
}