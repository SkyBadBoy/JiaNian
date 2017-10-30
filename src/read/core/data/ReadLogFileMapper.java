package read.core.data;
import java.util.*;

import wtb.core.model.LogFile;

/**
 * wtb_WeChatPublic
 */
public interface ReadLogFileMapper {
	/**
	 * 查询全部
	 */
	 public List<LogFile> getLogFileList(Map<String, Object> params);


	 public int getLogFileCount(Map<String,Object> params);
	 

}