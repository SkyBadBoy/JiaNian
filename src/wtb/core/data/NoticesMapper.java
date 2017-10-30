package wtb.core.data;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import wtb.core.model.Notices;

/**
 * wtb_WeChatPublic
 */
public interface NoticesMapper {
	/**
	 * 查询全部
	 */
	 
	 
	 /**
	 * 修改记录
	 */
	 public int updateNotices(Notices Nontices);
	 public int updateNoticesDraft(Notices Notices);
	 /**
	 * 删除记录
	 */
	 public int deleteNotices(Map<String,Object> params);
	 public int deleteNoticesisDel(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addNotices(Notices Notices);
	 /**
	 * 恢复记录
	 */
	 public int enabledNotices(Map<String,Object> params);
	 public int NoPassNotices(Map<String,Object> params);
	 

	 

	public int UpClickCount(Map<String,Object> params) ;
	public int UpVoteCount(Map<String,Object> params) ;
	 public int UpDateClickCount(Map<String,Object> params);
	 
	 public int UpDateCommentCount(long nid) ;
	 public int CancelCommentCount(long nid);
	 
	 public int UpDateLikeCount(long nid) ;
	 public int CancelLikeCount(long nid);
	 
	 /**
	  * 更具AreaId，更新值为0
	  * @param params
	  * @return
	  */
	 public int updateNoticeSortByAreaId(Map<String, Object> params);
	 public int UpdateForAreaLevel(Notices params);
	 public int ApplyNotices(Map<String,Object> params);
	 
	 public int updateNoticesActivity(Notices params);
	 
	 public int UpDateClickCountTempCount(Notices nid);
	 
	 public int UpdateAreaIDForStudents(Map<String, Object> params);
	 
	 public int UpdateAuthorForStudents(Map<String, Object> params);
	 
	 public int UpShareCount(long nid);
	 
	 public int UpdateCaptainComment(Map<String, Object> params);
	 
	 public int UpdateTop(Map<String, Object> params);
}