package wtb.core.data;
import java.util.*;

import wtb.core.model.Video;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface VideoMapper {
	
	 /**
	 * 修改记录
	 */
	 public int updateVideo(Video Video);
	 /**
	 * 删除记录
	 */
	 public int deleteVideo(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addVideo(Video Video);
	 /**
	 * 恢复记录
	 */
	 public int enabledVideo(Map<String,Object> params);

	
	 
	 public int UpClickCount(Map<String,Object> params);
	 public int UpVoteCount(long params);
	 
	 
	 public int UpLikeCount(long vid);
	 public int CancelLikeCount(long vid);
	 
	 public int SetHotVideo(long vid);
	 public int CancelHotVideo(long vid);
	 
	 public int UpCommentCount(long vid);
	 public int CancelCommentCount(long vid);
	 
	 public int UpdateParentID(Map<String,Object> params);
	 
	 
}