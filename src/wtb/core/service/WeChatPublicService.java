package wtb.core.service;
import java.util.*;

import wtb.core.data.WeChatPublicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.model.WeChatPublic;
/**
 * wtb_WeChatPublic
 */
 @Service
public class WeChatPublicService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeChatPublicMapper mapper;


	
	@Transactional
	public int addWeChatPublic(WeChatPublic obj) {
		return mapper.addWeChatPublic(obj);
	}

	@Transactional
	public int updateWeChatPublic(WeChatPublic obj) {
		return mapper.updateWeChatPublic(obj);
	}

	@Transactional
	public int deleteWeChatPublic(Map<String,Object> params) {
		return mapper.deleteWeChatPublic(params);
	}
	
	@Transactional
	public int enabledWeChatPublic(Map<String,Object> params) {
		return mapper.enabledWeChatPublic(params);
	}

	@Transactional
	public int UpClickCount(Map<String,Object> params) {
		return mapper.UpClickCount(params);
	}
	@Transactional
	public int JoinWeChatPublic(Map<String,Object> params){
		 return mapper.JoinWeChatPublic(params);
	 }

	
}