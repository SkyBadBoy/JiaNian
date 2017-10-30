package wtb.core.service;
import java.util.*;

import wtb.core.data.AccessActiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.AccessActive;

/**
 * wtb_vitaes
 */
 @Service
public class AccessActiveService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private AccessActiveMapper mapper;

	@Transactional
	public int addAccessActive(AccessActive obj) {
		return mapper.addAccessActive(obj);
	}

	@Transactional
	public int updateAccessActive(AccessActive obj) {
		return mapper.updateAccessActive(obj);
	}

	@Transactional
	public int deleteAccessActive(Map<String,Object> params) {
		return mapper.deleteAccessActive(params);
	}
}