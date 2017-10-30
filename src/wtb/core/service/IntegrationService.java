package wtb.core.service;
import java.util.*;

import wtb.core.data.IntegrationMapper;
import wtb.core.model.Integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




 @Service
public class IntegrationService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private IntegrationMapper mapper;
	 
	@Transactional(readOnly = true)
	public List<Integration> getIntegrationList(Map<String,Object> params) {
		return mapper.getIntegrationList(params);
	}
	
	@Transactional
	public int addIntegration(Integration obj) {
		return mapper.addIntegration(obj);
	}

	@Transactional
	public int updateIntegration(Integration obj) {
		return mapper.updateIntegration(obj);
	}

	@Transactional
	public int deleteIntegration(Map<String,Object> params) {
		return mapper.deleteIntegration(params);
	}

	@Transactional
	public int getIntegrationCount(Map<String,Object> params){
		return mapper.getIntegrationCount(params);
	}



}