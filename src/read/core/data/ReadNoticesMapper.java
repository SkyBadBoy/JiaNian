package read.core.data;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import wtb.core.model.Notices;

/**
 * wtb_WeChatPublic
 */
public interface ReadNoticesMapper {
	/**
	 * 查询全部
	 */

	 public List<Notices> getReadNoticesList(Map<String, Object> params);
	 public Notices getNoticesByID(long params);
	 public Notices getNoticesHtmlByID(long params);
	 public int getNoticesCount(Map<String, Object> params);
	 public List<Notices> getNoticesRankingList(Map<String, Object> params);
	 public List<Notices> getNoticesSchoolRankingList(Map<String, Object> params);

	 public List<Notices> getWantData(Map<String, Object> params);
	 public List<Notices> getAreaIDList(Map<String, Object> params);
	 public List<Notices> getNoticesTimeList(Map<String, Object> params);
	 public int getNoticesRankingCount(Map<String,Object> params);
	 public int  getNoticesSchoolRankingCount(Map<String,Object> params);
	 
	 public List<Notices>  getSchoolNoticesList(Map<String,Object> params);
	 
	 public List<Notices>  getNoticesVoteRankingList(Map<String,Object> params);
	 
	 public List<Notices>  getAreaNoticesList(Map<String,Object> params);
	 
	 public List<Notices>  getStudentNoticesList(Map<String,Object> params);
	 
	 public List<Notices>  getNoticesReadRankingList(Map<String,Object> params);
	 
	 public List<Notices>  getNoticesChiefKindRankingList(Map<String,Object> params);
	 
	 public int  getSchoolActiveCount(Map<String,Object> params);
	 
	 public List<Notices>  getDayNoticesCountList(Map<String,Object> params);
	 
	 public int  getNoticesGlamourCount(Map<String,Object> params);
	 
	 
	 
	 
	 
}