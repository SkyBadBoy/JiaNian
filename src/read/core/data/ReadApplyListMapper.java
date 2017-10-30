package read.core.data;
import java.util.*;

import wtb.core.model.ApplyList;

/**
 * wtb_WeChatPublic
 */
public interface ReadApplyListMapper {
	/**
	 * 查询全部
	 */
	 public List<ApplyList> getApplyListList(Map<String, Object> params);

	 public int getApplyListCount(Map<String,Object> params);
	 
	 public ApplyList getApplyListByID(long id);
}