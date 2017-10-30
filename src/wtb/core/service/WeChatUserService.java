package wtb.core.service;
import java.util.*;

import wtb.core.data.WeChatUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeChatUser;

/**
 * wtb_vitaes
 */
 @Service
public class WeChatUserService {
	/**
	 * 查询全部
	 */
	@Autowired
	private WeChatUserMapper mapper;
	
	@Transactional(readOnly = true)
	public List<WeChatUser> getWeChatUserList(Map<String,Object> params) {
		return mapper.getWeChatUserList(params);
	}
	
	@Transactional
	public int addWeChatUser(WeChatUser obj) {
		return mapper.addWeChatUser(obj);
	}

	@Transactional
	public int updateWeChatUser(WeChatUser obj) {
		return mapper.updateWeChatUser(obj);
	}

	@Transactional
	public int deleteWeChatUser(Map<String,Object> params) {
		return mapper.deleteWeChatUser(params);
	}
	
	@Transactional
	public int enabledWeChatUser(Map<String,Object> params) {
		return mapper.enabledWeChatUser(params);
	}
	
	@Transactional
	public int getWeChatUserEffCount(Map<String, Object> params){
		return mapper.getWeChatUserEffCount(params);
	}
	
	
	
	
}