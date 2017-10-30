package read.core.data;
import java.util.*;

import wtb.core.model.Region;

/**
 * wtb_region
 */
public interface ReadRegionMapper {
	/**
	 * 查询全部
	 */
	 public List<Region> getRegionList(Map<String,Object> params); 
	 public List<Region> getRegionByIDList(String params); 
	 
	 public int getRegionCount(Map<String,Object> params);
}