package wtb.core.data;
import java.util.*;

import wtb.core.model.LogFile;

/**
 * wtb_WeChatPublic
 */
public interface LogFileMapper {
	 /**
	 * 修改记录
	 */
	 public int updateLogFile(LogFile LogFile);
	 /**
	 * 删除记录
	 */
	 public int deleteLogFile(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addLogFile(LogFile LogFile);
	 
}