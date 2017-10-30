package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadCommentMapper;

import wtb.core.data.CommentMapper;
import wtb.core.model.ClickList;
import wtb.core.model.Comment;

@Service
public class ReadCommentService {

	@Autowired
	private ReadCommentMapper mapper;
	
	/**
	 * 查询全部
	 */
	@Transactional(readOnly = true)
	 public List<Comment> getCommentList(Map<String, Object> params){
		 return mapper.getCommentList(params);
	 }
	
	 public List<Comment> getCommentListByNoticeID(Map<String, Object> params){
		 return mapper.getCommentListByNoticeID(params);
	 }
	
	@Transactional()
	 public int getCommentCount(Map<String,Object> params){
		 return mapper.getCommentCount(params);
	 }
	
	@Transactional()
	 public int getCommentUserCount(long params){
		 return mapper.getCommentUserCount(params);
	 }
	
}
