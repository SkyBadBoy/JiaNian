package wtb.core.data;
import java.util.*;

import wtb.core.model.ClickList;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ClickListMapper {
	/**
	 * 查询全部
	 */
	 public List<ClickList> getClickListList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateClickList(ClickList ClickList);
	 /**
	 * 删除记录
	 */
	 public int deleteClickList(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addClickList(ClickList ClickList);
	 
	 public int CheckExistByIdAddress(Map<String,Object> params);

}