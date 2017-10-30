package read.core.data;
import java.util.*;

import wtb.core.model.Vote;

/**
 * wtb_WeChatPublic
 */
public interface ReadVoteMapper {
	/**
	 * 查询全部
	 */
	 public List<Vote> getVoteList(Map<String, Object> params);
	 
	 public int getVoteCount(Map<String,Object> params);
	 
	 


	 
	 
	 
	 
}