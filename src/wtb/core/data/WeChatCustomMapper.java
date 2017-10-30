package wtb.core.data;
import java.util.*;

import wtb.core.model.WeChatCustom;


public interface WeChatCustomMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatCustom> getWeChatCustomList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateWeChatCustom(WeChatCustom WeChatCustom);
	 /**
	 * 删除记录
	 */
	 public int deleteWeChatCustom(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeChatCustom(WeChatCustom WeChatCustom);
	 
	 public int getWeChatCustomCount(Map<String,Object> params);
	
}