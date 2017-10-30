package read.core.service;
import java.util.*;

import wtb.core.data.IntegrationMapper;
import wtb.core.model.Integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadIntegrationMapper;




 @Service
public class ReadIntegrationService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadIntegrationMapper mapper;
	 
	@Transactional(readOnly = true)
	public List<Integration> getIntegrationList(Map<String,Object> params) {
		return mapper.getIntegrationList(params);
	}
	
	

	@Transactional
	public int getIntegrationCount(Map<String,Object> params){
		return mapper.getIntegrationCount(params);
	}
	
	@Transactional(readOnly = true)
	public List<Integration> getIntegrationRecordRankingList(Map<String,Object> params) {
		return mapper.getIntegrationRecordRankingList(params);
	}

	@Transactional(readOnly = true)
	public List<Integration> getNoticesIntegrationRankingList(Map<String,Object> params) {
		return mapper.getNoticesIntegrationRankingList(params);
	}
	
	@Transactional
	public int getIntegrationRecordRankingCount(Map<String,Object> params){
		return mapper.getIntegrationRecordRankingCount(params);
	}

}