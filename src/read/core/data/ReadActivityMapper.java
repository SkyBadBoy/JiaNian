package read.core.data;
import java.util.*;

import wtb.core.model.Activity;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadActivityMapper {
	/**
	 * 查询全部
	 */
	 public List<Activity> getActivityList(Map<String, Object> params);


	 public int getActivityCount(Map<String,Object> params);
	 

}