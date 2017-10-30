package read.core.service;
import java.util.*;

import wtb.core.data.WeMoneyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadWeMoneyMapper;
import wtb.core.model.WeMoney;

/**
 * wtb_vitaes
 */
 @Service
public class ReadWeMoneyService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadWeMoneyMapper mapper;

	@Transactional(readOnly = true)
	public List<WeMoney> getWeMoneyList(Map<String,Object> params) {
		return mapper.getWeMoneyList(params);
	}

	@Transactional
	public int getWeMoneyCount(Map<String,Object> params){
		return mapper.getWeMoneyCount(params);
	}
	@Transactional(readOnly = true)
	public List<WeMoney> getWeMoneyByIDList(Map<String,Object> params) {
		return mapper.getWeMoneyByIDList(params);
	}
	
	
	
}