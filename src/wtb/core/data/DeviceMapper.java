package wtb.core.data;
import java.util.*;

import wtb.core.model.Device;

/**
 * wtb_WeChatPublic
 */
public interface DeviceMapper {

	 /**
	 * 修改记录
	 */
	 public int updateDevice(Device Device);
	 /**
	 * 删除记录
	 */
	 public int deleteDevice(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addDevice(Device Device);
	 
	 /**
	  * 都改为未使用状态就是说未登录状态
	  */
	 public int IsNotUsing(long UserID);
	 
}