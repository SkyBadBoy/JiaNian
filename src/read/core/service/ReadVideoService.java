package read.core.service;
import java.util.*;

import wtb.core.data.VideoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadVideoMapper;
import wtb.core.model.Video;

/**
 * wtb_vitaes
 */
 @Service
public class ReadVideoService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadVideoMapper mapper;

	@Transactional(readOnly = true)
	public List<Video> getVideoList(Map<String,Object> params) {
		return mapper.getVideoList(params);
	}
	
	
	@Transactional
	public int getVideoCount(Map<String,Object> params){
		return mapper.getVideoCount(params);
	}
	
	@Transactional(readOnly = true)
	public List<Video> getVideoSchoolRankingList(Map<String,Object> params) {
		return mapper.getVideoSchoolRankingList(params);
	}
	
	@Transactional(readOnly = true)
	public List<Video> getVideoRankingList(Map<String,Object> params) {
		return mapper.getVideoRankingList(params);
	}
	
	@Transactional(readOnly = true)
	public List<Video> getVideoReadRankingList(Map<String,Object> params) {
		return mapper.getVideoReadRankingList(params);
	}
}