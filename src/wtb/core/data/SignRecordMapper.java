package wtb.core.data;
import java.util.*;
import wtb.core.model.SignRecord;

/**
 * wtb_SignRecord
 */
public interface SignRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<SignRecord> getSignRecordList(Map<String,Object> params); 
	 public List<SignRecord> getSignRecordByIDList(String params); 
	 /**
	 * 修改记录
	 */
	 public int updateSignRecord(SignRecord SignRecord);
	 /**
	 * 删除记录
	 */
	 public int deleteSignRecord(Map<String,Object> params);
	 
	 public int enabledSignRecord(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addSignRecord(SignRecord SignRecord);
	 
	 public int getSignRecordCount(Map<String,Object> params);
}