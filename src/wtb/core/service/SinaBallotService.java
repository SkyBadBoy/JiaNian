package wtb.core.service;
import java.util.*;

import wtb.core.data.SinaBallotMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.SinaBallot;

/**
 * wtb_vitaes
 */
 @Service
public class SinaBallotService {
	/**
	 * 查询全部
	 */
	@Autowired
	private SinaBallotMapper mapper;
	
	@Transactional(readOnly = true)
	public List<SinaBallot> getSinaBallotList(Map<String,Object> params) {
		return mapper.getSinaBallotList(params);
	}
	
	@Transactional
	public int addSinaBallot(SinaBallot obj) {
		return mapper.addSinaBallot(obj);
	}

	@Transactional
	public int updateSinaBallot(SinaBallot obj) {
		return mapper.updateSinaBallot(obj);
	}

	@Transactional
	public int deleteSinaBallot(Map<String,Object> params) {
		return mapper.deleteSinaBallot(params);
	}
	
	@Transactional
	public int enabledSinaBallot(Map<String,Object> params) {
		return mapper.enabledSinaBallot(params);
	}
	
	@Transactional
	public int getSinaBallotEffCount(Map<String, Object> params){
		return mapper.getSinaBallotEffCount(params);
	}
	
	
	
	
}