package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.ClickList;
import wtb.core.model.Comment;

public interface ReadCommentMapper {
	/**
	 * ��ѯȫ��
	 */
	 public List<Comment> getCommentList(Map<String, Object> params);
	 public List<Comment> getCommentListByNoticeID(Map<String, Object> params);
	 
	 public int getCommentCount(Map<String,Object> params);
	 //获取评论的用户数量
	 public int getCommentUserCount(long params);
	 
}
