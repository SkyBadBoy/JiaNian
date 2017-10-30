package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.Integration;
import wtb.core.model.Notices;

public interface IntegrationMapper 
{
	/**
	 * 查询全部
	 */
	 public List<Integration> getIntegrationList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateIntegration(Integration Integration);
	 /**
	 * 删除记录
	 */
	 public int deleteIntegration(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addIntegration(Integration obj);

	 
	 public int getIntegrationCount(Map<String,Object> params);
}
