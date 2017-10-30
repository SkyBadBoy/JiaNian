package read.core.service;
import java.util.*;

import wtb.core.data.VideoClassMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadVideoClassMapper;
import wtb.core.model.VideoClass;

/**
 * wtb_vitaes
 */
 @Service
public class ReadVideoClassService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadVideoClassMapper mapper;

	@Transactional(readOnly = true)
	public List<VideoClass> getVideoClassList(Map<String,Object> params) {
		return mapper.getVideoClassList(params);
	}
	@Transactional
	public int getVideoClassCount(Map<String,Object> params){
		return mapper.getVideoClassCount(params);
	}
}