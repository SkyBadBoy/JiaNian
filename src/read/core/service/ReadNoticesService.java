package read.core.service;
import java.util.*;

import read.core.data.ReadNoticesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Notices;

/**
 * wtb_vitaes
 */
 @Service
public class ReadNoticesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadNoticesMapper mapper;

	@Transactional
	 public List<Notices> getReadNoticesList(Map<String, Object> params){
		return mapper.getReadNoticesList(params);
	 }

	@Transactional
	 public Notices getNoticesByID(long params){
		return mapper.getNoticesByID(params);
	 }
	@Transactional
	public Notices getNoticesHtmlByID(long params){
		return mapper.getNoticesHtmlByID(params);
	}
	@Transactional
	 public int getNoticesCount(Map<String, Object> params){
		return mapper.getNoticesCount(params);
	 }
	@Transactional
	 public List<Notices> getNoticesRankingList(Map<String, Object> params){
		return mapper.getNoticesRankingList(params);
	 }
	
	@Transactional
	 public List<Notices> getNoticesVoteRankingList(Map<String, Object> params){
		return mapper.getNoticesVoteRankingList(params);
	 }
	
	
	@Transactional
	 public List<Notices> getWantData(Map<String, Object> params){
		return mapper.getWantData(params);
	 }
	@Transactional
	 public List<Notices> getAreaIDList(Map<String, Object> params){
		return mapper.getAreaIDList(params);
	 }
	@Transactional
	 public List<Notices> getNoticesTimeList(Map<String, Object> params){
		return mapper.getNoticesTimeList(params);
	 }
	@Transactional
	 public int getNoticesSchoolRankingCount(Map<String, Object> params){
		return mapper.getNoticesSchoolRankingCount(params);
	 }
	@Transactional
	 public int getNoticesRankingCount(Map<String, Object> params){
		return mapper.getNoticesRankingCount(params);
	 }
	@Transactional
	 public List<Notices> getNoticesSchoolRankingList(Map<String, Object> params){
		return mapper.getNoticesSchoolRankingList(params);
	 }
	
	@Transactional
	 public List<Notices> getSchoolNoticesList(Map<String, Object> params){
		return mapper.getSchoolNoticesList(params);
	 }
	
	@Transactional
	 public List<Notices> getAreaNoticesList(Map<String, Object> params){
		return mapper.getAreaNoticesList(params);
	 }
	
	@Transactional
	 public List<Notices> getStudentNoticesList(Map<String, Object> params){
		return mapper.getStudentNoticesList(params);
	 }
	@Transactional
	 public List<Notices> getNoticesReadRankingList(Map<String, Object> params){
		return mapper.getNoticesReadRankingList(params);
	 }
	
	@Transactional
	 public List<Notices> getNoticesChiefKindRankingList(Map<String, Object> params){
		return mapper.getNoticesChiefKindRankingList(params);
	 }
	
	@Transactional
	 public int getSchoolActiveCount(Map<String, Object> params){
		return mapper.getSchoolActiveCount(params);
	 }
	
	@Transactional
	 public List<Notices> getDayNoticesCountList(Map<String, Object> params){
		return mapper.getDayNoticesCountList(params);
	 }
	
	@Transactional
	 public int getNoticesGlamourCount(Map<String, Object> params){
		return mapper.getNoticesGlamourCount(params);
	 }
	
}