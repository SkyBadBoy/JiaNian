package read.core.service;
import java.util.*;

import read.core.data.ReadApplyListMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.ApplyList;

/**
 * wtb_vitaes
 */
 @Service
public class ReadApplyListService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadApplyListMapper mapper;

	@Transactional(readOnly = true)
	public List<ApplyList> getApplyListList(Map<String,Object> params) {
		return mapper.getApplyListList(params);
	}
	
	@Transactional
	public int getApplyListCount(Map<String,Object> params){
		return mapper.getApplyListCount(params);
	}
	@Transactional
	public ApplyList getApplyListByID(long id){
		return mapper.getApplyListByID(id);
	}
	
}