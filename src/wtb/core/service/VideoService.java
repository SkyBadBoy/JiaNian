package wtb.core.service;
import java.util.*;

import wtb.core.data.VideoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Video;

/**
 * wtb_vitaes
 */
 @Service
public class VideoService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private VideoMapper mapper;


	
	@Transactional
	public int addVideo(Video obj) {
		return mapper.addVideo(obj);
	}

	@Transactional
	public int updateVideo(Video obj) {
		return mapper.updateVideo(obj);
	}

	@Transactional
	public int deleteVideo(Map<String,Object> params) {
		return mapper.deleteVideo(params);
	}
	
	@Transactional
	public int enabledVideo(Map<String,Object> params) {
		return mapper.enabledVideo(params);
	}
	
	@Transactional
	public int UpClickCount(Map<String,Object> params) {
		return mapper.UpClickCount(params);
	}
	@Transactional
	public int UpLikeCount(long vid) {
		return mapper.UpLikeCount(vid);
	}
	@Transactional
	public int UpVoteCount(long params) {
		return mapper.UpVoteCount(params);
	}
	@Transactional
	public int CancelLikeCount(long vid) {
		return mapper.CancelLikeCount(vid);
	}
	@Transactional
	public int SetHotVideo(long params) {
		return mapper.SetHotVideo(params);
	}
	@Transactional
	public int CancelHotVideo(long vid) {
		return mapper.CancelHotVideo(vid);
	}
	@Transactional
	public int UpCommentCount(long params) {
		return mapper.UpCommentCount(params);
	}
	@Transactional
	public int CancelCommentCount(long vid) {
		return mapper.CancelCommentCount(vid);
	}
	@Transactional
	public int UpdateParentID(Map<String,Object> params) {
		return mapper.UpdateParentID(params);
	}
}