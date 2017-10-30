package read.core.data;
import java.util.*;

import wtb.core.model.Activity;
import wtb.core.model.ActivityPart;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadActivityPartMapper {
	/**
	 * 查询全部
	 */
	 public List<ActivityPart> getActivityPartList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int getActivityPartCount(Map<String, Object> params);
	 /**
	 * 删除记录
	 */
	 public  List<ActivityPart>  getActivityPartByIDList(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addActivityPart(ActivityPart Activity);
	 /**
	 * 恢复记录
	 */
	 public int updateActivityPart(ActivityPart Activity);

	 public int deleteActivityPart(Map<String,Object> params);
	 
	 public int enabledActivityPart(Map<String,Object> params);
	 /**
	  * 确认参赛
	  * @param params
	  * @return
	  */
	 public int confirmApplyActivityPart(Map<String,Object> params);
	 
}