package wtb.core.service;
import java.util.*;

import wtb.core.data.WeChatBannerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeChatBanner;

/**
 * wtb_WeChatBanner
 */
 @Service
public class WeChatBannerService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeChatBannerMapper mapper;

	@Transactional(readOnly = true)
	public List<WeChatBanner> getWeChatBannerList(Map<String,Object> params) {
		return mapper.getWeChatBannerList(params);
	}
	
	@Transactional
	public int addWeChatBanner(WeChatBanner obj) {
		return mapper.addWeChatBanner(obj);
	}

	@Transactional
	public int updateWeChatBanner(WeChatBanner obj) {
		return mapper.updateWeChatBanner(obj);
	}

	@Transactional
	public int deleteWeChatBanner(Map<String,Object> params) {
		return mapper.deleteWeChatBanner(params);
	}
	
	@Transactional
	public int enabledWeChatBanner(Map<String,Object> params) {
		return mapper.enabledWeChatBanner(params);
	}
	@Transactional
	public int getWeChatBannerCount(Map<String,Object> params){
		return mapper.getWeChatBannerCount(params);
	}
	
}