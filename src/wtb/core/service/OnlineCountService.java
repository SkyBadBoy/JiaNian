package wtb.core.service;
import java.util.*;

import wtb.core.data.OnlineCountMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.OnlineCount;

/**
 * wtb_vitaes
 */
 @Service
public class OnlineCountService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private OnlineCountMapper mapper;


	
	@Transactional
	public int addOnlineCount(OnlineCount obj) {
		return mapper.addOnlineCount(obj);
	}

	@Transactional
	public int updateOnlineCount(OnlineCount obj) {
		return mapper.updateOnlineCount(obj);
	}

	@Transactional
	public int deleteOnlineCount(Map<String,Object> params) {
		return mapper.deleteOnlineCount(params);
	}
	
	@Transactional
	public int updateOnlineCountByToDay(OnlineCount obj) {
		return mapper.updateOnlineCountByToDay(obj);
	}
}