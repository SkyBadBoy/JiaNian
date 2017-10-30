package read.core.data;
import java.util.*;

import wtb.core.model.BaseInfo;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadBaseInfoMapper {
	/**
	 * 查询全部
	 */
	 public BaseInfo getBaseInfoList(long id);


	 public List<BaseInfo>  getBaseInfoListByID(Map<String, Object> params);
	 
	 public int  getBaseInfoCount(Map<String, Object> params);
}