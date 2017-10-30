package read.core.data;
import java.util.*;

import wtb.core.model.WeMoneyRecord;

/**
 * wtb_WeChatPublic
 */
public interface ReadWeMoneyRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<WeMoneyRecord> getWeMoneyRecordList(Map<String, Object> params);
	 public List<WeMoneyRecord> getWeMoneyRecordRankingList(Map<String, Object> params);

	 public int getWeMoneyRecordCount(Map<String,Object> params);
	 public int getWeMoneyRecordRankingCount(Map<String,Object> params);
	 
	 
	 public long getWeMoneyRecordSum(Map<String,Object> params);
	 
	 
	 public int getWeMoneyRecordUserCount(long param);
	 
	 public int getWeMoneyRecordUserSum(long param);
	 
	 public List<WeMoneyRecord> getWeMoneyRecordSumRankingList(Map<String, Object> params);
	 
	 public List<WeMoneyRecord> getWeMoneyRewardRankingList(Map<String, Object> params);
	 
	 
}