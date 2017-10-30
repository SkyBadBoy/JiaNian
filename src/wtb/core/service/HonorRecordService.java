package wtb.core.service;
import java.util.*;

import wtb.core.data.ActivityMapper;
import wtb.core.data.HonorRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Activity;
import wtb.core.model.HonorRecord;

/**
 * wtb_vitaes
 */
 @Service
public class HonorRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private HonorRecordMapper mapper;


	@Transactional
	public int addHonorRecord(HonorRecord obj) {
		return mapper.addHonorRecord(obj);
	}

	@Transactional
	public int updateHonorRecord(HonorRecord obj) {
		return mapper.updateHonorRecord(obj);
	}

	@Transactional
	public int deleteHonorRecord(Map<String,Object> params) {
		return mapper.deleteHonorRecord(params);
	}
	
}