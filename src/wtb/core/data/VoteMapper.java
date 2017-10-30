package wtb.core.data;
import java.util.*;

import wtb.core.model.Vote;

/**
 * wtb_WeChatPublic
 */
public interface VoteMapper {

	 
	 /**
	 * 修改记录
	 */
	 public int updateVote(Vote Vote);
	 /**
	 * 删除记录
	 */
	 public int deleteVote(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addVote(Vote Vote);
	 
	 


	 
	 
	 
	 
}