package wtb.core.data;
import java.util.*;

import wtb.core.model.MoneyRecord;

/**
 * wtb_WeChatPublic
 */
public interface MoneyRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<MoneyRecord> getMoneyRecordList(Map<String, Object> params);
	 
	 
	 public int getMoneyRecordCount(Map<String,Object> params);

	 /**
	 * 添加记录
	 */
	 public int addMoneyRecord(MoneyRecord MoneyRecord);

	//退款
	 public int refundMoneyRecord(MoneyRecord MoneyRecord);
	 
	 
	 
	 
}