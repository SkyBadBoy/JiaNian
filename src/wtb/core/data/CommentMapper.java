package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.ClickList;
import wtb.core.model.Comment;

public interface CommentMapper {


	 public int deleteComment(Map<String,Object> params);

	 public int addComment(Comment Comment);
	 
	 public int updateComment(Comment Comment);
	 
	 

}
