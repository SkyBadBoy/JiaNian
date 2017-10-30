package read.core.service;
import java.util.*;

import wtb.core.data.OnlineCountMapper;
import wtb.core.data.WeChatBannerMapper;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadOnlineCountMapper;
import wtb.core.model.OnlineCount;

import java.sql.SQLException;
@SuppressWarnings("unchecked")
/**
 * wtb_vitaes
 */
 @Service
public class ReadOnlineCountService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadOnlineCountMapper mapper;

	@Transactional(readOnly = true)
	public List<OnlineCount> getOnlineCountList(Map<String,Object> params) {
		return mapper.getOnlineCountList(params);
	}
	@Transactional(readOnly = true)
	public long getOnlineCountCount(Map<String,Object> params) {
		return mapper.getOnlineCountCount(params);
	}

	@Transactional
	public OnlineCount getOnlineCountListByID(long params){
		return mapper.getOnlineCountListByID(params);
	}
}