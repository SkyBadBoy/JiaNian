package wtb.core.data;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import wtb.core.model.Notices;
import wtb.core.model.NoticesTemp;

/**
 * wtb_WeChatPublic
 */
public interface NoticesTempMapper {
	/**
	 * 查询全部
	 */
	 public List<NoticesTemp> getNoticesList(Map<String, Object> params);
	
	 
	 /**
	 * 修改记录
	 */
	 public int updateNotices(NoticesTemp Nontices);
	 /**
	 * 删除记录
	 */
	 public int deleteNotices(Map<String,Object> params);
	
	 /**
	 * 添加记录
	 */
	 public int addNotices(NoticesTemp Notices);

	 public NoticesTemp getNoticesByID(long id);
	 


	 
	 
}