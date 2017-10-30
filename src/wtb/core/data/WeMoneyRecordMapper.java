package wtb.core.data;
import java.util.*;

import wtb.core.model.WeMoneyRecord;

/**
 * wtb_WeChatPublic
 */
public interface WeMoneyRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<WeMoneyRecord> getWeMoneyRecordList(Map<String, Object> params);
	 public List<WeMoneyRecord> getWeMoneyRecordRankingList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateWeMoneyRecord(WeMoneyRecord WeMoneyRecord);
	 /**
	 * 删除记录
	 */
	 public int deleteWeMoneyRecord(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeMoneyRecord(WeMoneyRecord WeMoneyRecord);

	 public int getWeMoneyRecordCount(Map<String,Object> params);
	 public int getWeMoneyRecordRankingCount(Map<String,Object> params);
	 
	 
	 public long getWeMoneyRecordSum(Map<String,Object> params);
	 
	 
}