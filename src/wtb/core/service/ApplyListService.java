package wtb.core.service;
import java.util.*;

import wtb.core.data.ApplyListMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.ApplyList;

/**
 * wtb_vitaes
 */
 @Service
public class ApplyListService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ApplyListMapper mapper;

	
	@Transactional
	public int addApplyList(ApplyList obj) {
		return mapper.addApplyList(obj);
	}

	@Transactional
	public int updateApplyList(ApplyList obj) {
		return mapper.updateApplyList(obj);
	}

	@Transactional
	public int deleteApplyList(Map<String,Object> params) {
		return mapper.deleteApplyList(params);
	}
	
	@Transactional
	public int enabledApplyList(Map<String,Object> params) {
		return mapper.enabledApplyList(params);
	}
	
	
}