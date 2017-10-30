package wtb.core.data;
import java.util.*;

import wtb.core.model.Messages;

/**
 * wtb_WeChatPublic
 */
public interface MessagesMapper {
	/**
	 * 查询全部
	 */
	 public List<Messages> getMessagesList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateMessages(Messages Messages);
	 /**
	 * 删除记录
	 */
	 public int ReadedMessages(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addMessages(Messages Messages);
	 /**
	 * 恢复记录
	 */
	 public int enabledMessages(Map<String,Object> params);

	 public int getMessagesCount(Map<String,Object> params);
	 
	 public Messages getMessagesByID(long id);
}