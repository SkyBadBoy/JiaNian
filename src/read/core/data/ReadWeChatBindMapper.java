package read.core.data;
import java.util.*;

import wtb.core.model.Activity;
import wtb.core.model.ActivityPart;
import wtb.core.model.WeChatBind;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadWeChatBindMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatBind> getWeChatBindList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int getWeChatBindCount(Map<String, Object> params);
	 /**
	 * 删除记录
	 */
	 public  WeChatBind  getWeChatBindByIDList(long params);
	 
}