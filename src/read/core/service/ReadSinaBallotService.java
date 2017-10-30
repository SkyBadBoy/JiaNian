package read.core.service;
import java.util.*;

import wtb.core.data.SinaBallotMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadSinaBallotMapper;
import wtb.core.model.SinaBallot;

/**
 * wtb_vitaes
 */
 @Service
public class ReadSinaBallotService {
	/**
	 * 查询全部
	 */
	@Autowired
	private ReadSinaBallotMapper mapper;
	
	@Transactional(readOnly = true)
	public List<SinaBallot> getSinaBallotList(Map<String,Object> params) {
		return mapper.getSinaBallotList(params);
	}
	
	@Transactional
	public int getSinaBallotEffCount(Map<String, Object> params){
		return mapper.getSinaBallotEffCount(params);
	}
	
	
	
	
}