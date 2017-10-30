package wtb.core.data;
import java.util.*;
import wtb.core.model.Region;

/**
 * wtb_region
 */
public interface RegionMapper {
	/**
	 * 查询全部
	 */
	 public List<Region> getRegionList(Map<String,Object> params); 
	 public List<Region> getRegionByIDList(String params); 
	 /**
	 * 修改记录
	 */
	 public int updateRegion(Region region);
	 /**
	 * 删除记录
	 */
	 public int deleteRegion(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addRegion(Region region);
	 
	 public int getRegionCount(Map<String,Object> params);
}