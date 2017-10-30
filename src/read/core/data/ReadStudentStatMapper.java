package read.core.data;
import java.util.*;

import wtb.core.model.StudentStat;

/**
 * wtb_WeChatPublic
 */
public interface ReadStudentStatMapper {
	/**
	 * 查询全部
	 */
	 public List<StudentStat> getStudentStatList(Map<String, Object> params);
	 public long getStudentStatCount(Map<String, Object> params);
	 

	 public StudentStat getStudentStatListByID(long params);
}