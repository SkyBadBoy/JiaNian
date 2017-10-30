package read.core.data;
import java.util.*;

import wtb.core.model.Device;

/**
 * wtb_WeChatPublic
 */
public interface ReadDeviceMapper {
	/**
	 * 查询全部
	 */
	 public List<Device> getDeviceList(Map<String, Object> params);


	 public int getDeviceCount(Map<String,Object> params);
	 

}