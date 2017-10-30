package read.core.data;
import java.util.*;

import wtb.core.model.OnlineCount;

/**
 * wtb_WeChatPublic
 */
public interface ReadOnlineCountMapper {
	/**
	 * 查询全部
	 */
	 public List<OnlineCount> getOnlineCountList(Map<String, Object> params);
	 public long getOnlineCountCount(Map<String, Object> params);

	 public OnlineCount getOnlineCountListByID(long params);
}