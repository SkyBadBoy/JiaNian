package read.core.service;
import java.util.*;

import wtb.core.data.ActivityPartMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadActivityPartMapper;
import wtb.core.model.ActivityPart;

/**
 * wtb_vitaes
 */
 @Service
public class ReadActivityPartService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadActivityPartMapper mapper;

	@Transactional(readOnly = true)
	public List<ActivityPart> getActivityPartPartList(Map<String,Object> params) {
		return mapper.getActivityPartList(params);
	}
	
	@Transactional
	public int addActivityPart(ActivityPart obj) {
		return mapper.addActivityPart(obj);
	}

	@Transactional
	public int updateActivityPart(ActivityPart obj) {
		return mapper.updateActivityPart(obj);
	}

	@Transactional
	public int deleteActivityPart(Map<String,Object> params) {
		return mapper.deleteActivityPart(params);
	}
	
	@Transactional
	public int enabledActivityPart(Map<String,Object> params) {
		return mapper.enabledActivityPart(params);
	}
	@Transactional
	public int getActivityPartCount(Map<String,Object> params){
		return mapper.getActivityPartCount(params);
	}
	/**
	 * 确认参赛
	 * @param params
	 * @return
	 */
	@Transactional
	public int confirmApplyActivityPart(Map<String,Object> params){
		return mapper.confirmApplyActivityPart(params);
	}
	
	
}