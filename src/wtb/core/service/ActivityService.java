package wtb.core.service;
import java.util.*;

import wtb.core.data.ActivityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Activity;

/**
 * wtb_vitaes
 */
 @Service
public class ActivityService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ActivityMapper mapper;


	@Transactional
	public int addActivity(Activity obj) {
		return mapper.addActivity(obj);
	}

	@Transactional
	public int updateActivity(Activity obj) {
		return mapper.updateActivity(obj);
	}

	@Transactional
	public int deleteActivity(Map<String,Object> params) {
		return mapper.deleteActivity(params);
	}
	
	@Transactional
	public int enabledActivity(Map<String,Object> params) {
		return mapper.enabledActivity(params);
	}

	/**
	 * 活动指定
	 * @param params
	 * @return
	 */
	@Transactional
	public int TopLevevActivity(Map<String,Object> params){
		return mapper.TopLevevActivity(params);
	}
	/**
	 * 取消置顶
	 * @param params
	 * @return
	 */
	@Transactional
	public int CanCelTopLevevActivity(){
		return mapper.CanCelTopLevevActivity();
	}
	@Transactional
	 public int CanCelApplyActivity(long Aid){
		return mapper.CanCelApplyActivity(Aid);
	 }
	
	@Transactional
	 public int ApplyActivity(long Aid){
		return mapper.ApplyActivity(Aid);
	}
}