package read.core.data;
import java.util.*;

import wtb.core.model.PayRecord;

/**
 * wtb_WeChatPublic
 */
public interface ReadPayRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<PayRecord> getPayRecordList(Map<String, Object> params);


	 public int getPayRecordCount(Map<String,Object> params);
	 

}