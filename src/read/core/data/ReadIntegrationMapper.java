package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.Integration;
import wtb.core.model.Notices;

public interface ReadIntegrationMapper 
{
	/**
	 * 查询全部
	 */
	 public List<Integration> getIntegrationList(Map<String, Object> params);
	
	 public int getIntegrationCount(Map<String,Object> params);
	 
	 public List<Integration> getIntegrationRecordRankingList(Map<String, Object> params);
	 
	 public List<Integration> getNoticesIntegrationRankingList(Map<String, Object> params);
	 
	 public int getIntegrationRecordRankingCount(Map<String,Object> params);
	 
	 
}
