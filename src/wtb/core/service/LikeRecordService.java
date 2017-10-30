package wtb.core.service;
import java.util.*;

import wtb.core.data.LikeRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.LikeRecord;

/**
 * wtb_vitaes
 */
 @Service
public class LikeRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private LikeRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<LikeRecord> getLikeRecordList(Map<String,Object> params) {
		return mapper.getLikeRecordList(params);
	}
	
	@Transactional
	public int addLikeRecord(LikeRecord obj) {
		return mapper.addLikeRecord(obj);
	}

	@Transactional
	public int updateLikeRecord(LikeRecord obj) {
		return mapper.updateLikeRecord(obj);
	}

	@Transactional
	public int deleteLikeRecord(long params) {
		return mapper.deleteLikeRecord(params);
	}
	
	@Transactional
	public int enabledLikeRecord(Map<String,Object> params) {
		return mapper.enabledLikeRecord(params);
	}
	@Transactional
	public int getLikeRecordCount(Map<String,Object> params){
		return mapper.getLikeRecordCount(params);
	}

	
}