package wtb.core.data;
import java.util.*;

import wtb.core.model.StudentStat;

/**
 * wtb_WeChatPublic
 */
public interface StudentStatMapper {

	 /**
	 * 修改记录
	 */
	 public int updateStudentStat(StudentStat StudentStat);
	 /**
	 * 删除记录
	 */
	 public int deleteStudentStat(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addStudentStat(StudentStat StudentStat);

}