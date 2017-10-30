package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.data.WeChatInfoMapper;
import wtb.core.model.WeChatInfo;

@Service
public class WeChatInfoServices 
{
	@Autowired
	WeChatInfoMapper mapper;
	
	@Transactional(readOnly = true)
	public List<WeChatInfo> getWeChatInfo(Map<String, Object> params)
	{
		return mapper.getWeChatInfo(params);
	}
	@Transactional
	public int addWeChatInfo(WeChatInfo WeChatInfo)
	{
		return mapper.addWeChatInfo(WeChatInfo);
	}
	@Transactional
	public List<WeChatInfo> getWeChatInfoHome(Map<String, Object> params)
	{
		return mapper.getWeChatInfoHome(params);
	}
	@Transactional
	public int getWeChatInfoHomeCount(Map<String, Object> params)
	{
		return mapper.getWeChatInfoHomeCount(params);
	}
	@Transactional
	public int updateWeChatInfo(WeChatInfo WeChatInfo)
	{
		return mapper.updateWeChatInfo(WeChatInfo);
	}
	@Transactional
	public int deleteWeChatInfo(WeChatInfo weChatInfo )
	{
		return mapper.deleteWeChatInfo(weChatInfo); 
	}
	@Transactional
	public WeChatInfo getDefWeChat(){
		return mapper.getDefWeChat();
	}
	@Transactional
	public WeChatInfo getWeightWeChat(){
		return mapper.getWeightWeChat();
		
	}
}
