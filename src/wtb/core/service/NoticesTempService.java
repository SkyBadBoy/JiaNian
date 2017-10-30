package wtb.core.service;
import java.util.*;

import wtb.core.data.NoticesMapper;
import wtb.core.data.NoticesTempMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Notices;
import wtb.core.model.NoticesTemp;

/**
 * wtb_vitaes
 */
 @Service
public class NoticesTempService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private NoticesTempMapper mapper;

	@Transactional(readOnly = true)
	public List<NoticesTemp> getNoticesList(Map<String,Object> params) {
		return mapper.getNoticesList(params);
	}
	
	@Transactional
	public int addNotices(NoticesTemp obj) {
		return mapper.addNotices(obj);
	}

	@Transactional
	public int updateNotices(NoticesTemp obj) {
		return mapper.updateNotices(obj);
	}

	@Transactional
	public int deleteNotices(Map<String,Object> params) {
		return mapper.deleteNotices(params);
	}
	
	@Transactional
	public NoticesTemp getNoticesByID(long id){
		return mapper.getNoticesByID(id);
	}
	
	
}