package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.WeChatContentMapper;
import wtb.core.model.WeChatContent;

@Service
public class WeChatContentService
{
	@Autowired
	private  WeChatContentMapper mapper;
	@Transactional(readOnly = true)
	public List<WeChatContent> getWeChatContentList(Map<String, Object> params) {
		return mapper.getWeChatContentList(params);
	}
	
	@Transactional
	public int addWeChatContent(WeChatContent WeChatContent) {
		return mapper.addWeChatContent(WeChatContent);
	}
	
	@Transactional
	public int getWeChatContentCount(Map<String,Object> params)
	{
		return mapper.getWeChatContentCount(params);
		
	}
	@Transactional
	public int deleteWeChatContent(Map<String,Object> params)
	{
		return mapper.deleteWeChatContent(params);
	}
}	
