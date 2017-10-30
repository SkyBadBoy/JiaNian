package wtb.core.service;
import java.util.*;

import wtb.core.data.PayRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.PayRecord;

/**
 * wtb_vitaes
 */
 @Service
public class PayRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private PayRecordMapper mapper;


	@Transactional
	public int addPayRecord(PayRecord obj) {
		return mapper.addPayRecord(obj);
	}

	@Transactional
	public int updatePayRecord(PayRecord obj) {
		return mapper.updatePayRecord(obj);
	}

	@Transactional
	public int deletePayRecord(Map<String,Object> params) {
		return mapper.deletePayRecord(params);
	}
	
	@Transactional
	public int enabledPayRecord(Map<String,Object> params) {
		return mapper.enabledPayRecord(params);
	}


}