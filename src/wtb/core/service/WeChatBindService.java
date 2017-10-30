package wtb.core.service;
import wtb.core.data.WeChatBindMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeChatBind;

/**
 * wtb_vitaes
 */
 @Service
public class WeChatBindService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeChatBindMapper mapper;

	
	@Transactional
	public int addWeChatBind(WeChatBind obj) {
		return mapper.addWeChatBind(obj);
	}

	@Transactional
	public int updateWeChatBind(WeChatBind obj) {
		return mapper.updateWeChatBind(obj);
	}

	@Transactional
	public int deleteWeChatBind(long params) {
		return mapper.deleteWeChatBind(params);
	}
	
	@Transactional
	public int enabledWeChatBind(long params) {
		return mapper.enabledWeChatBind(params);
	}
	
	
}