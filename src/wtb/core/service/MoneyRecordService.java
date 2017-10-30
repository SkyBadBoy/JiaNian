package wtb.core.service;
import java.util.*;

import wtb.core.data.MoneyRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.MoneyRecord;

/**
 * wtb_vitaes
 */
 @Service
public class MoneyRecordService implements MoneyRecordMapper{
	/**
	 * 查询全部
	 */
	 @Autowired
	private MoneyRecordMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<MoneyRecord> getMoneyRecordList(Map<String, Object> params) {
		return mapper.getMoneyRecordList(params);
	}

	@Override
	@Transactional
	public int getMoneyRecordCount(Map<String, Object> params) {
		return mapper.getMoneyRecordCount(params);
	}

	@Override
	@Transactional
	public int addMoneyRecord(MoneyRecord MoneyRecord) {
		return mapper.addMoneyRecord(MoneyRecord);
	}

	@Override
	@Transactional
	public int refundMoneyRecord(MoneyRecord MoneyRecord) {
		return mapper.refundMoneyRecord(MoneyRecord);
	}
	
}