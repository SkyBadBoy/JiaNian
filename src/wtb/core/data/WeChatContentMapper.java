package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.WeChatContent;

public interface WeChatContentMapper
{
	public List<WeChatContent> getWeChatContentList(Map<String,Object> params);
	public int  addWeChatContent(WeChatContent WeChatContent);
	public int getWeChatContentCount(Map<String,Object> params);
	public int deleteWeChatContent(Map<String,Object> params);
}
