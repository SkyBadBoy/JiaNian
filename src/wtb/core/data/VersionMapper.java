package wtb.core.data;
import java.util.*;

import wtb.core.model.Version;

/**
 * wtb_WeChatPublic
 */
public interface VersionMapper {

	 
	 /**
	 * 修改记录
	 */
	 public int updateVersion(Version version );
	 /**
	 * 删除记录
	 */
	 public int deleteVersion(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addVersion(Version version);
	 /**
	  * 恢复记录
	  * @param version
	  * @return
	  */
	 public int enabledVersion(Version version );
	 
	 
}