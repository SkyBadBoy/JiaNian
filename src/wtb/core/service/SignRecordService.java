package wtb.core.service;
import java.util.*;

import wtb.core.data.SignRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.SignRecord;

/**
 * wtb_vitaes
 */
 @Service
public class SignRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private SignRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<SignRecord> getSignRecordList(Map<String,Object> params) {
		return mapper.getSignRecordList(params);
	}
	
	@Transactional
	public int addSignRecord(SignRecord obj) {
		return mapper.addSignRecord(obj);
	}

	@Transactional
	public int updateSignRecord(SignRecord obj) {
		return mapper.updateSignRecord(obj);
	}

	@Transactional
	public int deleteSignRecord(Map<String,Object> params) {
		return mapper.deleteSignRecord(params);
	}
	
	@Transactional
	public int enabledSignRecord(Map<String,Object> params) {
		return mapper.enabledSignRecord(params);
	}
	@Transactional
	public int getSignRecordCount(Map<String,Object> params){
		return mapper.getSignRecordCount(params);
	}
	
	
}