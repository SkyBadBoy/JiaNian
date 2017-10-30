package wtb.core.data;
import java.util.*;

import wtb.core.model.OnlineCount;

/**
 * wtb_WeChatPublic
 */
public interface OnlineCountMapper {

	 /**
	 * 修改记录
	 */
	 public int updateOnlineCount(OnlineCount OnlineCount);
	 /**
	 * 删除记录
	 */
	 public int deleteOnlineCount(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addOnlineCount(OnlineCount OnlineCount);
	 
	 public int updateOnlineCountByToDay(OnlineCount OnlineCount);
	 

}