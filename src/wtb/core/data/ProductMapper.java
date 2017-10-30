package wtb.core.data;
import java.util.*;

import wtb.core.model.Product;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ProductMapper {
	/**
	 * 查询全部
	 */
	 public List<Product> getProductList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateProduct(Product Product);
	 /**
	 * 删除记录
	 */
	 public int deleteProduct(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addProduct(Product Product);

	 public int enabledProduct(Map<String,Object> params);
	 /**
	  * 排序置顶
	  * @param params
	  * @return
	  */
	 public int TopLevevProduct(Map<String,Object> params);
	 
	 public int getProductByMaxList(Map<String,Object> params);
	 
	 public int UpClickCount(Map<String,Object> params);
	 
	 public int getProductCount(Map<String,Object> params);
	 
}