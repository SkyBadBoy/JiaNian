package wtb.core.service;
import java.util.*;

import wtb.core.data.WeMoneyRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeMoneyRecord;

/**
 * wtb_vitaes
 */
 @Service
public class WeMoneyRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeMoneyRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<WeMoneyRecord> getWeMoneyRecordList(Map<String,Object> params) {
		return mapper.getWeMoneyRecordList(params);
	}
	
	@Transactional
	public int addWeMoneyRecord(WeMoneyRecord obj) {
		return mapper.addWeMoneyRecord(obj);
	}

	@Transactional
	public int updateWeMoneyRecord(WeMoneyRecord obj) {
		return mapper.updateWeMoneyRecord(obj);
	}

	@Transactional
	public int deleteWeMoneyRecord(Map<String,Object> params) {
		return mapper.deleteWeMoneyRecord(params);
	}
	
	@Transactional
	public int getWeMoneyRecordCount(Map<String,Object> params){
		return mapper.getWeMoneyRecordCount(params);
	}
	
	@Transactional
	public long getWeMoneyRecordSum(Map<String,Object> params){
		return mapper.getWeMoneyRecordSum(params);
	}
	@Transactional
	public List<WeMoneyRecord> getWeMoneyRecordRankingList(Map<String, Object> params){
		return mapper.getWeMoneyRecordRankingList(params);
	}
	@Transactional
	public int getWeMoneyRecordRankingCount(Map<String,Object> params){
		return mapper.getWeMoneyRecordRankingCount(params);
	}
	
}