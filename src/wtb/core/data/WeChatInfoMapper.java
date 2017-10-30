package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.Notices;
import wtb.core.model.WeChatInfo;

public interface WeChatInfoMapper {
	public List<WeChatInfo> getWeChatInfo(Map<String, Object> params);
	public int addWeChatInfo(WeChatInfo WeChatInfo);
	public List<WeChatInfo> getWeChatInfoHome(Map<String, Object> params);
	public int getWeChatInfoHomeCount(Map<String, Object> params);
	public int updateWeChatInfo(WeChatInfo WeChatInfo);
	public int deleteWeChatInfo(WeChatInfo weChatInfo );

	public WeChatInfo getDefWeChat();//查询默认的
	public WeChatInfo getWeightWeChat();//查询权值最小的

}
