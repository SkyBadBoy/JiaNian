package wtb.core.data;
import java.util.*;

import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatPublicSimple;
import wtb.core.model.WeChatStatInfo;

/**
 * wtb_WeChatPublic
 */
public interface WeChatPublicMapper {

	 /**
	 * 修改记录
	 */
	 public int updateWeChatPublic(WeChatPublic WeChatPublic);
	 /**
	 * 停用
	 */
	 public int deleteWeChatPublic(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeChatPublic(WeChatPublic WeChatPublic);
	 
	 /**
	  * 启用
	  * @param params
	  * @return
	  */
	 public int enabledWeChatPublic(Map<String,Object> params);
	 
	 public int UpClickCount(Map<String,Object> params);
	 
	 public int JoinWeChatPublic(Map<String,Object> params);

}