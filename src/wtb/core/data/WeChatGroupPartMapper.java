package wtb.core.data;
import java.util.*;

import wtb.core.model.WeChatGroupPart;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface WeChatGroupPartMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatGroupPart> getWeChatGroupPartList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateWeChatGroupPart(WeChatGroupPart weChatGroupPart);
	 /**
	 * 删除记录
	 */
	 public int deleteWeChatGroupPart(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeChatGroupPart(WeChatGroupPart weChatGroupPart);

	 public int enabledWeChatGroupPart(Map<String,Object> params);
	 
}