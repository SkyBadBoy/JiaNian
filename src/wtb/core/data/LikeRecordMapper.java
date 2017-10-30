package wtb.core.data;
import java.util.*;

import wtb.core.model.LikeRecord;

/**
 * wtb_WeChatPublic
 */
public interface LikeRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<LikeRecord> getLikeRecordList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateLikeRecord(LikeRecord LikeRecord);
	 /**
	 * 删除记录
	 */
	 public int deleteLikeRecord(long params);
	 /**
	 * 添加记录
	 */
	 public int addLikeRecord(LikeRecord LikeRecord);
	 /**
	 * 恢复记录
	 */
	 public int enabledLikeRecord(Map<String,Object> params);

	 public int getLikeRecordCount(Map<String,Object> params);

}