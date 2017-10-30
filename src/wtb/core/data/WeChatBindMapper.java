package wtb.core.data;
import java.util.*;

import wtb.core.model.Activity;
import wtb.core.model.WeChatBind;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface WeChatBindMapper {
	
	 /**
	 * 修改记录
	 */
	 public int updateWeChatBind(WeChatBind weChatBind);
	 /**
	 * 添加记录
	 */
	 public int addWeChatBind(WeChatBind weChatBind);

	 
	 public int enabledWeChatBind(long Aid);
	 public int deleteWeChatBind(long Aid);
}