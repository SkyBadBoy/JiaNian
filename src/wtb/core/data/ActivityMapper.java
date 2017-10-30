package wtb.core.data;
import java.util.*;

import wtb.core.model.Activity;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ActivityMapper {
	
	 /**
	 * 修改记录
	 */
	 public int updateActivity(Activity Activity);
	 /**
	 * 删除记录
	 */
	 public int deleteActivity(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addActivity(Activity Activity);
	 /**
	 * 恢复记录
	 */
	 public int enabledActivity(Map<String,Object> params);
	 
	 public int TopLevevActivity(Map<String,Object> params);
	 
	 public int CanCelTopLevevActivity();
	 public int CanCelApplyActivity(long Aid);
	 public int ApplyActivity(long Aid);
}