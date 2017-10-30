package read.core.service;
import java.util.*;

import wtb.core.data.ActivityPartMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadActivityPartMapper;
import read.core.data.ReadWeChatBindMapper;
import wtb.core.model.ActivityPart;
import wtb.core.model.WeChatBind;

/**
 * wtb_vitaes
 */
 @Service
public class ReadWeChatBindService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadWeChatBindMapper mapper;

	@Transactional(readOnly = true)
	public List<WeChatBind> getWeChatBindList(Map<String,Object> params) {
		return mapper.getWeChatBindList(params);
	}
	

	@Transactional
	public int getWeChatBindCount(Map<String,Object> params){
		return mapper.getWeChatBindCount(params);
	}

	@Transactional
	public WeChatBind getWeChatBindByIDList(long params){
		return mapper.getWeChatBindByIDList(params);
	}
	
	
}