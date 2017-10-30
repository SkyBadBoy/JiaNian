package read.core.data;
import java.util.*;

import wtb.core.model.Scenic;

/**
 * wtb_WeChatPublic
 */
public interface ReadScenicMapper {
	/**
	 * 查询全部
	 */
	 public List<Scenic> getScenicList(Map<String, Object> params);


	 public int getScenicCount(Map<String,Object> params);
	 

}