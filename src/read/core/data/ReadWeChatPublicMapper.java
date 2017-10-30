package read.core.data;
import java.util.*;

import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatPublicSimple;
import wtb.core.model.WeChatStatInfo;

/**
 * wtb_WeChatPublic
 */
public interface ReadWeChatPublicMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatPublic> getWeChatPublicList(Map<String, Object> params);

	 /**
	  * 启用
	  * @param params
	  * @return
	  */
	 public int enabledWeChatPublic(Map<String,Object> params);

	 /**
	  * 返回小于N天的总数
	  * @param params
	  * @return
	  */
	 public  WeChatStatInfo getWeChatPublicForDaysList(Map<String,Object> params);
	 /**
	  * 获取总数
	  * @param params
	  * @return
	  */
	 public WeChatStatInfo getWeChatPublicCount(Map<String,Object> params);
	 /**
	  * 获取上月的统计数据
	  * @param params
	  * @return
	  */
	 public List<WeChatLastMonthStatInfo> getWeChatLastMonthStat(Map<String,Object> params);
	 
	 public List<WeChatPublic> getWeChatPublicForEffList(Map<String,Object> params);

	 /**
	  * 配合第一个查询
	  * @param params
	  * @return
	  */
	 public int getWeChatPublicNormalCount(Map<String,Object> params);
	 
	 public WeChatPublicSimple getSimpleWeChatPublicList(long index);
}