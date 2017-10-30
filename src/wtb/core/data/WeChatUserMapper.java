package wtb.core.data;
import java.util.*;

import wtb.core.model.WeChatUser;

/**
 * wtb_WeChatUser
 */
public interface WeChatUserMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatUser> getWeChatUserList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateWeChatUser(WeChatUser WeChatUser);
	 /**
	 * 停用
	 */
	 public int deleteWeChatUser(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeChatUser(WeChatUser WeChatUser);
	 
	 /**
	  * 启用
	  * @param params
	  * @return
	  */
	 public int enabledWeChatUser(Map<String,Object> params);
	 

	 public int getWeChatUserEffCount(Map<String,Object> params);

}