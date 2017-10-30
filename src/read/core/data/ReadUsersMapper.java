package read.core.data;
import java.util.*;

import wtb.core.model.Users;

/**
 * wtb_Users
 */
public interface ReadUsersMapper {
	/**
	 * 查询全部
	 */
	 public List<Users> getUsersList(Map<String,Object> params); 
	 public List<Users> getHomeUsersList(Map<String,Object> params); 
	 public int getUsersCount(Map<String,Object> params);
	 
}