package read.core.service;
import java.util.*;

import wtb.core.data.PayRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadPayRecordMapper;
import wtb.core.model.PayRecord;

/**
 * wtb_vitaes
 */
 @Service
public class ReadPayRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadPayRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<PayRecord> getPayRecordList(Map<String,Object> params) {
		return mapper.getPayRecordList(params);
	}
	

	@Transactional
	public int getPayRecordCount(Map<String,Object> params){
		return mapper.getPayRecordCount(params);
	}

	
}