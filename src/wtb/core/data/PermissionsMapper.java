package wtb.core.data;
import java.util.*;

import wtb.core.model.Permissions;

/**
 * wtb_Permission
 */
public interface PermissionsMapper {
	/**
	 * 查询全部
	 */
	 public List<Permissions> getPermissionsList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updatePermissions(Permissions Permission);
	 /**
	 * 停用
	 */
	 public int deletePermissions(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addPermissions(Permissions Permission);
	 
	 /**
	  * 启用
	  * @param params
	  * @return
	  */
	 public int enabledPermissions(Map<String,Object> params);

	 public int getPermissionsCount(Map<String,Object> params);
	 
}