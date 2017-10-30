package read.core.data;
import java.util.*;

import wtb.core.model.VideoClass;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadVideoClassMapper {
	/**
	 * 查询全部
	 */
	 public List<VideoClass> getVideoClassList(Map<String, Object> params);
	
	 public int getVideoClassCount(Map<String,Object> params);
	 
	 
}