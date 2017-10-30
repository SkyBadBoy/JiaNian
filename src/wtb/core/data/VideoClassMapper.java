package wtb.core.data;
import java.util.*;

import wtb.core.model.VideoClass;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface VideoClassMapper {
	
	 /**
	 * 修改记录
	 */
	 public int updateVideoClass(VideoClass VideoClass);
	 /**
	 * 删除记录
	 */
	 public int deleteVideoClass(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addVideoClass(VideoClass VideoClass);
	 /**
	 * 恢复记录
	 */
	 public int enabledVideoClass(Map<String,Object> params);


	 public int UpdateToEnd(long params);
	 
	 public int CancelToEnd(long params);
	 
	 public int UpdateToType(Map<String,Object> params);
	 
	 
}