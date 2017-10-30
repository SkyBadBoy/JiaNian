package wtb.core.service;
import java.util.*;

import wtb.core.data.VoteRecordsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.VoteRecords;

/**
 * wtb_vitaes
 */
 @Service
public class VoteRecordsService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private VoteRecordsMapper mapper;

	@Transactional(readOnly = true)
	public List<VoteRecords> getVoteRecordsPartList(Map<String,Object> params) {
		return mapper.getVoteRecordsList(params);
	}
	
	@Transactional
	public int addVoteRecords(VoteRecords obj) {
		return mapper.addVoteRecords(obj);
	}

	@Transactional
	public int updateVoteRecords(VoteRecords obj) {
		return mapper.updateVoteRecords(obj);
	}

	@Transactional
	public int deleteVoteRecords(Map<String,Object> params) {
		return mapper.deleteVoteRecords(params);
	}
	
	@Transactional
	public int enabledVoteRecords(Map<String,Object> params) {
		return mapper.enabledVoteRecords(params);
	}
	@Transactional
	public int getVoteRecordsCount(Map<String,Object> params){
		return mapper.getVoteRecordsCount(params);
	}

	
}