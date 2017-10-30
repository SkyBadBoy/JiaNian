package wtb.core.service;
import java.util.*;

import wtb.core.data.WeChatGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeChatGroup;
import wtb.core.model.WeChatLastMonthStatInfo;

/**
 * wtb_WeChatGroup
 */
 @Service
public class WeChatGroupService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeChatGroupMapper mapper;

	@Transactional(readOnly = true)
	public List<WeChatGroup> getWeChatGroupList(Map<String,Object> params) {
		return mapper.getWeChatGroupList(params);
	}
	
	@Transactional
	public int addWeChatGroup(WeChatGroup obj) {
		return mapper.addWeChatGroup(obj);
	}

	@Transactional
	public int updateWeChatGroup(WeChatGroup obj) {
		return mapper.updateWeChatGroup(obj);
	}

	@Transactional
	public int deleteWeChatGroup(Map<String,Object> params) {
		return mapper.deleteWeChatGroup(params);
	}
	
	@Transactional
	public int enabledWeChatGroup(Map<String,Object> params) {
		return mapper.enabledWeChatGroup(params);
	}
	
	@Transactional(readOnly=true)
	public int getWeChatGroupByDaysList(Map<String,Object> params){
		return mapper.getWeChatGroupByDaysList(params).getWeChatDayForDay();
	}
	
	@Transactional(readOnly=true)
	public int getWeChatDayForDay(Map<String,Object> params){
		return mapper.getWeChatGroupByDaysList(params).getWeChatDayForDay();
	}
	@Transactional(readOnly=true)
	public List<WeChatLastMonthStatInfo> getWeChatLastMonthStat(Map<String,Object> params){
		return mapper.getWeChatLastMonthStat(params);
	}
	@Transactional(readOnly=true)
	public int getWeChatGroupCount(Map<String,Object> params){
		return mapper.getWeChatGroupCount(params);
	}
}