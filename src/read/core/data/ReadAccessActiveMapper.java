package read.core.data;
import java.util.*;

import wtb.core.model.AccessActive;

/**
 * wtb_WeChatPublic
 */
public interface ReadAccessActiveMapper {
	/**
	 * 查询全部
	 */
	 public List<AccessActive> getAccessActiveList(Map<String, Object> params);
	 public long getAccessActiveCount(Map<String, Object> params);
	 
	 public long getAccessActiveCountForMinute(Map<String, Object> params);

	 public AccessActive getAccessActiveListByID(long params);
	 
	 
	 public List<AccessActive> getDayActiveCountList(Map<String, Object> params);
	 
}