package read.core.data;
import java.util.*;

import wtb.core.model.WeMoney;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadWeMoneyMapper {
	/**
	 * 查询全部
	 */
	 public List<WeMoney> getWeMoneyList(Map<String, Object> params);
	
	 public int getWeMoneyCount(Map<String,Object> params);
	 
	 public List<WeMoney> getWeMoneyByIDList(Map<String, Object> params);
	 
	 
}