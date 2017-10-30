package wtb.core.data;
import java.util.*;

import wtb.core.model.AccessActive;

/**
 * wtb_WeChatPublic
 */
public interface AccessActiveMapper {

	 /**
	 * 修改记录
	 */
	 public int updateAccessActive(AccessActive AccessActive);
	 /**
	 * 删除记录
	 */
	 public int deleteAccessActive(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addAccessActive(AccessActive AccessActive);

}