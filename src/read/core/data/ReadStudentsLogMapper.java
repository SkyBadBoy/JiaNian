package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.StudentsLog;

public interface ReadStudentsLogMapper 
{

	/**
	 * 查询全部
	 */
	 public List<StudentsLog> getStudentsLogList(Map<String, Object> params);
	 
	 public int getStudentsLogCount(Map<String,Object> params);
}
