package read.core.data;
import java.util.*;

import wtb.core.model.Activity;
import wtb.core.model.HonorRecord;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadHonorRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<HonorRecord> getHonorRecordList(Map<String, Object> params);
	 
	 public HonorRecord getHonorRecordListByID(long params);

	 public int getHonorRecordCount(Map<String,Object> params);
	 

}