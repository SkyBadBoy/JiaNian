package wtb.core.data;
import java.util.*;

import wtb.core.model.DealInfo;

/**
 * wtb_WeChatPublic
 */
public interface DealInfoMapper {
	/**
	 * 查询全部
	 */
	 public List<DealInfo> getDealInfoList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateDealInfo(DealInfo DealInfo);
	 /**
	 * 删除记录
	 */
	 public int deleteDealInfo(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addDealInfo(DealInfo DealInfo);
	 /**
	 * 恢复记录
	 */
	 public int enabledDealInfo(Map<String,Object> params);

	 public int getDealInfoCount(Map<String,Object> params);
	 
	 public DealInfo getDealInfoByID(long id);
}