package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.StudentsLog;

public interface StudentsLogMapper 
{


	 /**
	 * 修改记录
	 */
	 public int updateStudentsLog(StudentsLog StudentsLog);
	 /**
	 * 删除记录
	 */
	 public int deleteStudentsLog(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addStudentsLog(StudentsLog obj);

}
