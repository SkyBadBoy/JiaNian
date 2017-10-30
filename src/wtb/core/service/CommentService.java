package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.CommentMapper;
import wtb.core.model.ClickList;
import wtb.core.model.Comment;

@Service
public class CommentService {

	@Autowired
	private CommentMapper mapper;
	

	 /**
	 * 删除记录
	 */
	@Transactional()
	 public int deleteComment(Map<String,Object> params){
		 return mapper.deleteComment(params);
	 }
	 /**
	 * 添加记录
	 */
	@Transactional()
	 public int addComment(Comment Comment){
		 return mapper.addComment(Comment);
	 }
}
