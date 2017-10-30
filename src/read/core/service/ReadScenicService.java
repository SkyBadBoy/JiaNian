package read.core.service;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadScenicMapper;
import wtb.core.model.Scenic;

/**
 * wtb_vitaes
 */
 @Service
public class ReadScenicService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadScenicMapper mapper;

	@Transactional(readOnly = true)
	public List<Scenic> getScenicList(Map<String,Object> params) {
		return mapper.getScenicList(params);
	}
	

	@Transactional
	public int getScenicCount(Map<String,Object> params){
		return mapper.getScenicCount(params);
	}

	
}