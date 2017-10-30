package read.core.service;
import java.util.*;

import wtb.core.data.AccessActiveMapper;
import wtb.core.data.WeChatBannerMapper;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadAccessActiveMapper;
import wtb.core.model.AccessActive;

import java.sql.SQLException;
@SuppressWarnings("unchecked")
/**
 * wtb_vitaes
 */
 @Service
public class ReadAccessActiveService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadAccessActiveMapper mapper;

	@Transactional(readOnly = true)
	public List<AccessActive> getAccessActiveList(Map<String,Object> params) {
		return mapper.getAccessActiveList(params);
	}
	
	@Transactional(readOnly = true)
	public long getAccessActiveCount(Map<String,Object> params) {
		return mapper.getAccessActiveCount(params);
	}
	
	@Transactional(readOnly = true)
	public long getAccessActiveCountForMinute(Map<String,Object> params) {
		return mapper.getAccessActiveCountForMinute(params);
	}
	
	@Transactional
	public AccessActive getAccessActiveListByID(long params){
		return mapper.getAccessActiveListByID(params);
	}
	
	@Transactional
	public List<AccessActive> getDayActiveCountList(Map<String,Object> params){
		return mapper.getDayActiveCountList(params);
	}
}