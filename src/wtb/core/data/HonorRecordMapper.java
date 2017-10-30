package wtb.core.data;
import java.util.*;

import wtb.core.model.Activity;
import wtb.core.model.HonorRecord;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface HonorRecordMapper {
	
	 /**
	 * 修改记录
	 */
	 public int addHonorRecord(HonorRecord HonorRecord);
	 /**
	 * 删除记录
	 */
	 public int updateHonorRecord(HonorRecord params);
	 /**
	 * 添加记录
	 */
	 public int deleteHonorRecord(Map<String,Object> params);

}