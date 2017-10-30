package wtb.core.data;
import java.util.*;

import wtb.core.model.Advert;

/**
 * wtb_WeChatPublic
 */
public interface AdvertMapper {

	 /**
	 * 修改记录
	 */
	 public int updateAdvert(Advert Advert);
	 /**
	 * 删除记录
	 */
	 public int deleteAdvert(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addAdvert(Advert Advert);

}