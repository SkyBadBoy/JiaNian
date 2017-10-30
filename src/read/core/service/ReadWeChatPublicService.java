package read.core.service;
import java.util.*;

import wtb.core.data.WeChatPublicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadWeChatPublicMapper;
import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatPublicSimple;
/**
 * wtb_WeChatPublic
 */
 @Service
public class ReadWeChatPublicService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadWeChatPublicMapper mapper;

	@Transactional(readOnly = true)
	public List<WeChatPublic> getWeChatPublicList(Map<String,Object> params) {
		return mapper.getWeChatPublicList(params);
	}
	

	@Transactional(readOnly = true)
	public int getWeChatByDaysList(Map<String,Object> params){
		return (int)mapper.getWeChatPublicForDaysList(params).getWeChatDayForDay();
	}
	@Transactional(readOnly = true)
	public int getWeChatCount(Map<String,Object> params){
		return (int)mapper.getWeChatPublicCount(params).getWeChatDayForDay();
	}
	@Transactional(readOnly = true)
	public List<WeChatLastMonthStatInfo> getWeChatLastMonthStat(Map<String,Object> params){
		return mapper.getWeChatLastMonthStat(params);
	}
	@Transactional(readOnly = true)
	public List<WeChatPublic> getWeChatPublicForEffList(Map<String,Object> params){
		return mapper.getWeChatPublicForEffList(params);
	}

	@Transactional
	public int getWeChatPublicNormalCount(Map<String,Object> params){
		 return mapper.getWeChatPublicNormalCount(params);
	 }
	@Transactional
	public WeChatPublicSimple getSimpleWeChatPublicList(long index){
		return mapper.getSimpleWeChatPublicList(index);
	}
	
}