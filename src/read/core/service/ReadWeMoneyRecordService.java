package read.core.service;
import java.util.*;

import wtb.core.data.WeMoneyRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadWeMoneyRecordMapper;
import wtb.core.model.WeMoneyRecord;

/**
 * wtb_vitaes
 */
 @Service
public class ReadWeMoneyRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadWeMoneyRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<WeMoneyRecord> getWeMoneyRecordList(Map<String,Object> params) {
		return mapper.getWeMoneyRecordList(params);
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
	
	@Transactional
	public int getWeMoneyRecordUserCount(long params){
		return mapper.getWeMoneyRecordUserCount(params);
	}
	
	@Transactional
	public int getWeMoneyRecordUserSum(long params){
		return mapper.getWeMoneyRecordUserSum(params);
	}
	
	@Transactional(readOnly = true)
	public List<WeMoneyRecord> getWeMoneyRecordSumRankingList(Map<String,Object> params) {
		return mapper.getWeMoneyRecordSumRankingList(params);
	}
	
	@Transactional(readOnly = true)
	public List<WeMoneyRecord> getWeMoneyRewardRankingList(Map<String,Object> params) {
		return mapper.getWeMoneyRewardRankingList(params);
	}
	
}