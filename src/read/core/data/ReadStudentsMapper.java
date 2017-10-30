package read.core.data;
import java.util.*;

import wtb.core.model.Students;
import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeChatStatInfo;

/**
 * wtb_WeChatPublic
 */
public interface ReadStudentsMapper {
	/**
	 * 查询全部
	 */
	 public List<Students> getStudentsList(Map<String, Object> params);
	 public int getStudentsCount(Map<String,Object> params);
	 
	 /**
	  * 返回小于N天的总数
	  * @param params
	  * @return
	  */
	 public WeChatStatInfo getStudentsByDaysList(Map<String,Object> params);

	 
	 public List<WeChatLastMonthStatInfo> getStudentsMonthStat(Map<String,Object> params);
	 
	 public List<Students> getStudentsAllNoticesList(Map<String, Object> params);
	 
	 public List<WeChatLastMonthStatInfo> getStudentsForDayStat(Map<String, Object> params);
	 
}