package read.core.data;
import java.util.*;

import wtb.core.model.LikeRecord;

/**
 * wtb_WeChatPublic
 */
public interface ReadLikeRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<LikeRecord> getLikeRecordList(Map<String, Object> params);
	 

	 public int getLikeRecordCount(Map<String,Object> params);

}