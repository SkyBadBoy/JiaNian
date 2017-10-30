package read.core.data;
import java.util.*;

import wtb.core.model.WeChatUser;

/**
 * wtb_WeChatUser
 */
public interface ReadWeChatUserMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatUser> getWeChatUserList(Map<String, Object> params);
	 public int getWeChatUserEffCount(Map<String,Object> params);

}