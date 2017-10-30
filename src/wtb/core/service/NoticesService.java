package wtb.core.service;
import java.util.*;

import wtb.core.data.NoticesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Notices;

/**
 * wtb_vitaes
 */
 @Service
public class NoticesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private NoticesMapper mapper;

	
	@Transactional
	public int addNotices(Notices obj) {
		return mapper.addNotices(obj);
	}

	@Transactional
	public int updateNotices(Notices obj) {
		return mapper.updateNotices(obj);
	}
	
	@Transactional
	public int updateNoticesDraft(Notices obj) {
		return mapper.updateNoticesDraft(obj);
	}
	@Transactional
	public int deleteNotices(Map<String,Object> params) {
		return mapper.deleteNotices(params);
	}
	@Transactional
	public int deleteNoticesisDel(Map<String,Object> params) {
		return mapper.deleteNoticesisDel(params);
	}
	
	@Transactional
	public int enabledNotices(Map<String,Object> params) {
		return mapper.enabledNotices(params);
	}
	
	@Transactional
	public int NoPassNotices(Map<String,Object> params) {
		return mapper.NoPassNotices(params);
	}
	@Transactional
	public int ApplyNotices(Map<String,Object> params) {
		return mapper.ApplyNotices(params);
	}
	@Transactional
	public int UpClickCount(Map<String,Object> params) {
		return mapper.UpClickCount(params);
	}
	@Transactional
	public int UpDateClickCount(Map<String,Object> params) {
		return mapper.UpDateClickCount(params);
	}
	
	@Transactional
	public int updateNoticeSortByAreaId(Map<String, Object> params)
	{
		return mapper.updateNoticeSortByAreaId(params);
	}
	@Transactional
	public int UpdateForAreaLevel(Notices params)
	{
		return mapper.UpdateForAreaLevel(params);
	}
	
	
	
	@Transactional
	public int UpDateLikeCount(long nid) {
		return mapper.UpDateLikeCount(nid);
	}
	@Transactional
	public int updateNoticesActivity(Notices params) {
		return mapper.updateNoticesActivity(params);
	}
	
	@Transactional
	public int CancelLikeCount(long nid) {
		return mapper.CancelLikeCount(nid);
	}
	
	
	@Transactional
	public int UpDateClickCountTempCount(Notices params) {
		return mapper.UpDateClickCountTempCount(params);
	}
	
	@Transactional
	public int UpDateCommentCount(long nid) {
		return mapper.UpDateCommentCount(nid);
	}
	
	
	@Transactional
	public int CancelCommentCount(long nid) {
		return mapper.CancelCommentCount(nid);
	}
	
	@Transactional
	public int UpVoteCount(Map<String,Object> params){
		return mapper.UpVoteCount(params);
	}
	@Transactional
	public int UpdateAreaIDForStudents(Map<String,Object> params){
		return mapper.UpdateAreaIDForStudents(params);
	}
	@Transactional
	public int UpdateAuthorForStudents(Map<String,Object> params){
		return mapper.UpdateAuthorForStudents(params);
	}
	
	@Transactional
	public int UpShareCount(long nid) {
		return mapper.UpShareCount(nid);
	}
	
	@Transactional
	public int UpdateCaptainComment(Map<String,Object> params){
		return mapper.UpdateCaptainComment(params);
	}
	
	@Transactional
	public int UpdateTop(Map<String,Object> params){
		return mapper.UpdateTop(params);
	}
	
	
}