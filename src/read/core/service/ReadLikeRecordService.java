package read.core.service;
import java.util.*;

import wtb.core.data.LikeRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadLikeRecordMapper;
import wtb.core.model.LikeRecord;

/**
 * wtb_vitaes
 */
 @Service
public class ReadLikeRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadLikeRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<LikeRecord> getLikeRecordList(Map<String,Object> params) {
		return mapper.getLikeRecordList(params);
	}
	
	@Transactional
	public int getLikeRecordCount(Map<String,Object> params){
		return mapper.getLikeRecordCount(params);
	}

	
}