package read.core.service;
import java.util.*;

import wtb.core.data.ActivityPartMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadActivityPartMapper;
import read.core.data.ReadHonorRecordMapper;
import wtb.core.model.ActivityPart;
import wtb.core.model.HonorRecord;

/**
 * wtb_vitaes
 */
 @Service
public class ReadHonorRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadHonorRecordMapper mapper;

	@Transactional(readOnly = true)
	public List<HonorRecord> getHonorRecordList(Map<String,Object> params) {
		return mapper.getHonorRecordList(params);
	}

	@Transactional(readOnly = true)
	public HonorRecord getHonorRecordListByID(long params) {
		return mapper.getHonorRecordListByID(params);
	}
	
	@Transactional
	public int getHonorRecordCount(Map<String,Object> params){
		return mapper.getHonorRecordCount(params);
	}

	
}