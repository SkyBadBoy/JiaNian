package wtb.core.service;
import java.util.*;

import wtb.core.data.VideoClassMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.VideoClass;

/**
 * wtb_vitaes
 */
 @Service
public class VideoClassService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private VideoClassMapper mapper;

	
	@Transactional
	public int addVideoClass(VideoClass obj) {
		return mapper.addVideoClass(obj);
	}

	@Transactional
	public int updateVideoClass(VideoClass obj) {
		return mapper.updateVideoClass(obj);
	}

	@Transactional
	public int deleteVideoClass(Map<String,Object> params) {
		return mapper.deleteVideoClass(params);
	}
	
	@Transactional
	public int enabledVideoClass(Map<String,Object> params) {
		return mapper.enabledVideoClass(params);
	}
	
	@Transactional
	public int UpdateToEnd(long vid) {
		return mapper.UpdateToEnd(vid);
	}
	@Transactional
	public int CancelToEnd(long vid) {
		return mapper.CancelToEnd(vid);
	}
	
	@Transactional
	public int UpdateToType(Map<String,Object> params) {
		return mapper.UpdateToType(params);
	}
	
	
}