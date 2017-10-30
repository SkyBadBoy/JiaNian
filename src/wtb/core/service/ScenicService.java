package wtb.core.service;
import java.util.*;

import wtb.core.data.ScenicMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Scenic;
 @Service
public class ScenicService implements ScenicMapper{
	/**
	 * 查询全部
	 */
	 @Autowired
	private ScenicMapper mapper;

	@Override
	@Transactional
	public int updateScenic(Scenic Scenic) {
		return mapper.updateScenic(Scenic);
	}

	@Override
	@Transactional
	public int deleteScenic(Map<String, Object> params) {
		return mapper.deleteScenic(params);
	}

	@Override
	@Transactional
	public int addScenic(Scenic Scenic) {
		return mapper.addScenic(Scenic);
	}
	 
}