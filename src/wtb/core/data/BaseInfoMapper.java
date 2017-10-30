package wtb.core.data;
import java.util.*;

import wtb.core.model.BaseInfo;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface BaseInfoMapper {
	/**
	 * 查询全部
	 */
	 public List<BaseInfo> getBaseInfoList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateBaseINfo(BaseInfo BaseInfo);
	 /**
	 * 删除记录
	 */
	 public int deleteBaseInfo(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addBaseInfo(BaseInfo BaseInfo);

	 public List<BaseInfo> getBaseInfoListByID(Map<String, Object> params);
}