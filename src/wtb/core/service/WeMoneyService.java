package wtb.core.service;
import java.util.*;

import wtb.core.data.WeMoneyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeMoney;

/**
 * wtb_vitaes
 */
 @Service
public class WeMoneyService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeMoneyMapper mapper;

	@Transactional
	public int addWeMoney(WeMoney obj) {
		return mapper.addWeMoney(obj);
	}

	@Transactional
	public int updateWeMoney(WeMoney obj) {
		return mapper.updateWeMoney(obj);
	}

	@Transactional
	public int deleteWeMoney(Map<String,Object> params) {
		return mapper.deleteWeMoney(params);
	}
	
	
}