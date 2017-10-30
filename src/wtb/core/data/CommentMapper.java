package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.ClickList;
import wtb.core.model.Comment;

public interface CommentMapper {

	 /**
	 * ɾ���¼
	 */
	 public int deleteComment(Map<String,Object> params);
	 /**
	 * ��Ӽ�¼
	 */
	 public int addComment(Comment Comment);
	 

}
