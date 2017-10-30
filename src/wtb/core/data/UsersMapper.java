package wtb.core.data;
import java.util.*;

import wtb.core.model.Users;

/**
 * wtb_Users
 */
public interface UsersMapper {

	 /**
	 * 修改记录
	 */
	 public int updateUsers(Users Users);
	 /**
	 * 删除记录
	 */
	 public int deleteUsers(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addUsers(Users Users);
	 
	 public int enabledUsers(Map<String,Object> params);
	 
	 public int ChangePassword(Map<String,Object> params);
	 
}