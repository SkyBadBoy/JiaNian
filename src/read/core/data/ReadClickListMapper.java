package read.core.data;
import java.util.*;

import wtb.core.model.ClickList;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadClickListMapper {
	/**
	 * 查询全部
	 */
	 public List<ClickList> getClickListList(Map<String, Object> params);
	 public int CheckExistByIdAddress(Map<String,Object> params);

}