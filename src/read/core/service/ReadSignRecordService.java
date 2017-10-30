package read.core.service;
import java.util.*;

import wtb.core.data.SignRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadSignRecordMapper;
import wtb.core.model.SignRecord;

/**
 * wtb_vitaes
 */
 @Service
public class ReadSignRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadSignRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<SignRecord> getSignRecordList(Map<String,Object> params) {
		return mapper.getSignRecordList(params);
	}
	
	
	@Transactional
	public int getSignRecordCount(Map<String,Object> params){
		return mapper.getSignRecordCount(params);
	}
	
	
}