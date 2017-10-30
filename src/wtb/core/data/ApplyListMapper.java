package wtb.core.data;
import java.util.*;

import wtb.core.model.ApplyList;

/**
 * wtb_WeChatPublic
 */
public interface ApplyListMapper {
	
	 /**
	 * 修改记录
	 */
	 public int updateApplyList(ApplyList ApplyList);
	 /**
	 * 删除记录
	 */
	 public int deleteApplyList(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addApplyList(ApplyList ApplyList);
	 /**
	 * 恢复记录
	 */
	 public int enabledApplyList(Map<String,Object> params);

	
}