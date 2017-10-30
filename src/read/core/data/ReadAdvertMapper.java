package read.core.data;
import java.util.*;

import wtb.core.model.Advert;

/**
 * wtb_WeChatPublic
 */
public interface ReadAdvertMapper {
	/**
	 * 查询全部
	 */
	 public List<Advert> getAdvertList(Map<String, Object> params);
	 public long getAdvertCount(Map<String, Object> params);
	
	 public Advert getAdvertListByID(long params);
	 
	 
	 
}