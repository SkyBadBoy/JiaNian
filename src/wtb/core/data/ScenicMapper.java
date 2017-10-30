package wtb.core.data;
import java.util.*;

import wtb.core.model.Scenic;

public interface ScenicMapper {

	 public int updateScenic(Scenic Scenic);
	 /**
	 * 删除记录
	 */
	 public int deleteScenic(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addScenic(Scenic Scenic);
}