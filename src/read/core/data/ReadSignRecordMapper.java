package read.core.data;
import java.util.*;
import wtb.core.model.SignRecord;

/**
 * wtb_SignRecord
 */
public interface ReadSignRecordMapper {
	/**
	 * 查询全部
	 */
	 public List<SignRecord> getSignRecordList(Map<String,Object> params); 
	 public List<SignRecord> getSignRecordByIDList(String params); 

	
	 public int getSignRecordCount(Map<String,Object> params);
}