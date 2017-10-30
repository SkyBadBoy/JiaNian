package wtb.core.service;
import java.util.*;
import wtb.core.data.WeChatGroupPartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.WeChatGroupPart;

/**
 * wtb_WeChatGroupPart
 */
 @Service
public class WeChatGroupPartService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeChatGroupPartMapper mapper;

	@Transactional(readOnly = true)
	public List<WeChatGroupPart> getWeChatGroupPartList(Map<String,Object> params) {
		return mapper.getWeChatGroupPartList(params);
	}
	
	@Transactional
	public int addWeChatGroupPart(WeChatGroupPart obj) {
		return mapper.addWeChatGroupPart(obj);
	}

	@Transactional
	public int updateWeChatGroupPart(WeChatGroupPart obj) {
		return mapper.updateWeChatGroupPart(obj);
	}

	@Transactional
	public int deleteWeChatGroupPart(Map<String,Object> params) {
		return mapper.deleteWeChatGroupPart(params);
	}
	@Transactional
	public int enabledWeChatGroupPart(Map<String,Object> params) {
		return mapper.enabledWeChatGroupPart(params);
	}
	
	
}