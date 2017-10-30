package read.core.service;
import java.util.*;

import read.core.data.ReadWeChatUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeChatUser;

/**
 * wtb_vitaes
 */
 @Service
public class ReadWeChatUserService {
	/**
	 * 查询全部
	 */
	@Autowired
	private ReadWeChatUserMapper mapper;
	
	@Transactional(readOnly = true)
	public List<WeChatUser> getWeChatUserList(Map<String,Object> params) {
		return mapper.getWeChatUserList(params);
	}
	@Transactional
	public int getWeChatUserEffCount(Map<String, Object> params){
		return mapper.getWeChatUserEffCount(params);
	}
	
	
	
	
}