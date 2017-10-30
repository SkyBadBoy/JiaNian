package wtb.core.data;
import java.util.*;

import wtb.core.model.WeMoney;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface WeMoneyMapper {
	
	 /**
	 * 修改记录
	 */
	 public int updateWeMoney(WeMoney WeMoney);
	 /**
	 * 删除记录
	 */
	 public int deleteWeMoney(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeMoney(WeMoney WeMoney);
	 
	 
}