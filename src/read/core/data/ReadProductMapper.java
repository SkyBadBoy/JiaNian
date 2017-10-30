package read.core.data;
import java.util.*;

import wtb.core.model.Product;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadProductMapper {
	/**
	 * 查询全部
	 */
	 public List<Product> getProductList(Map<String, Object> params);

	 public int getProductCount(Map<String,Object> params);
	 
}