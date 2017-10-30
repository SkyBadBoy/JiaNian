package wtb.core.data;
import java.util.*;

import wtb.core.model.PaidListing;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface PaidListingMapper {
	/**
	 * 查询全部
	 */
	 public List<PaidListing> getPaidListingList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updatePaidListing(PaidListing PaidListing);
	 /**
	 * 删除记录
	 */
	 public int deletePaidListing(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addPaidListing(PaidListing PaidListing);
	 /**
	 * 启用记录
	 */
	 public int enabledPaidListing(Map<String,Object> params);
	 /**
	 * 上升排名
	 */
	 public int UpLevevProduct(Map<String,Object> params);
	 /**
	 * 置顶
	 */
	 public int TopLevevProduct(Map<String,Object> params);
	 
	 public int getPaidListingCount(Map<String,Object> params);
}