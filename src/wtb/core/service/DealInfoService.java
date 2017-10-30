package wtb.core.service;
import java.util.*;

import wtb.core.data.DealInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.DealInfo;

/**
 * wtb_vitaes
 */
 @Service
public class DealInfoService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private DealInfoMapper mapper;

	@Transactional(readOnly = true)
	public List<DealInfo> getDealInfoList(Map<String,Object> params) {
		return mapper.getDealInfoList(params);
	}
	
	@Transactional
	public int addDealInfo(DealInfo obj) {
		return mapper.addDealInfo(obj);
	}

	@Transactional
	public int updateDealInfo(DealInfo obj) {
		return mapper.updateDealInfo(obj);
	}

	@Transactional
	public int deleteDealInfo(Map<String,Object> params) {
		return mapper.deleteDealInfo(params);
	}
	
	@Transactional
	public int enabledDealInfo(Map<String,Object> params) {
		return mapper.enabledDealInfo(params);
	}
	@Transactional
	public int getDealInfoCount(Map<String,Object> params){
		return mapper.getDealInfoCount(params);
	}
	@Transactional
	public DealInfo getDealInfoByID(long id){
		return mapper.getDealInfoByID(id);
	}
	
}