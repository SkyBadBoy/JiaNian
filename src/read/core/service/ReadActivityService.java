package read.core.service;
import java.util.*;

import wtb.core.data.ActivityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadActivityMapper;
import wtb.core.model.Activity;

/**
 * wtb_vitaes
 */
 @Service
public class ReadActivityService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadActivityMapper mapper;

	@Transactional(readOnly = true)
	public List<Activity> getActivityList(Map<String,Object> params) {
		return mapper.getActivityList(params);
	}
	

	@Transactional
	public int getActivityCount(Map<String,Object> params){
		return mapper.getActivityCount(params);
	}

	
}