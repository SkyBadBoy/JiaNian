package wtb.core.data;
import java.util.*;
import wtb.core.model.WeChatBanner;

/**
 * wtb_WeChatBanner
 */
public interface WeChatBannerMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatBanner> getWeChatBannerList(Map<String,Object> params); 
	 /**
	 * 修改记录
	 */
	 public int updateWeChatBanner(WeChatBanner WeChatBanner);
	 /**
	 * 删除记录
	 */
	 public int deleteWeChatBanner(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeChatBanner(WeChatBanner WeChatBanner);
	 
	 public int enabledWeChatBanner(Map<String,Object> params);
	 
	 public int getWeChatBannerCount(Map<String,Object> params);
}