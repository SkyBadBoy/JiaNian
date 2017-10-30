package wtb.core.data;
import java.util.*;

import wtb.core.model.PayRecord;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface PayRecordMapper {
	
	 /**
	 * 修改记录
	 */
	 public int updatePayRecord(PayRecord PayRecord);
	 /**
	 * 删除记录
	 */
	 public int deletePayRecord(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addPayRecord(PayRecord PayRecord);
	 /**
	 * 恢复记录
	 */
	 public int enabledPayRecord(Map<String,Object> params);
	 
}