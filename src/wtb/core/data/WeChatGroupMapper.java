package wtb.core.data;
import java.util.*;
import wtb.core.model.WeChatGroup;
import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeChatStatInfo;

/**
 * wtb_WeChatGroup
 */
public interface WeChatGroupMapper {
	/**
	 * 查询全部
	 */
	 public List<WeChatGroup> getWeChatGroupList(Map<String,Object> params); 
	 /**
	 * 修改记录
	 */
	 public int updateWeChatGroup(WeChatGroup WeChatGroup);
	 /**
	 * 删除记录
	 */
	 public int deleteWeChatGroup(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addWeChatGroup(WeChatGroup WeChatGroup);
	 
	 public int enabledWeChatGroup(Map<String,Object> params);
	 /**
	  * 返回小于N天的总数
	  * @param params
	  * @return
	  */
	 public WeChatStatInfo getWeChatGroupByDaysList(Map<String,Object> params);
	 /**
	  * 总数
	  * @param params
	  * @return
	  */
	 public int getWeChatGroupCount(Map<String,Object> params);
	 
	 /**
	  * 获取上个月的统计数据
	  * @param params
	  * @return
	  */
	 public List<WeChatLastMonthStatInfo> getWeChatLastMonthStat(Map<String,Object> params);
	 
	 
}