package wtb.core.service;
import java.util.*;

import wtb.core.data.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Users;

/**
 * wtb_vitaes
 */
 @Service
public class UsersService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private UsersMapper mapper;


	@Transactional
	public int addUsers(Users obj) {
		return mapper.addUsers(obj);
	}

	@Transactional
	public int updateUsers(Users obj) {
		return mapper.updateUsers(obj);
	}

	@Transactional
	public int deleteUsers(Map<String,Object> params) {
		return mapper.deleteUsers(params);
	}
	
	@Transactional
	public int enabledUsers(Map<String,Object> params) {
		return mapper.enabledUsers(params);
	}
	
	@Transactional
	public int ChangePassword(Map<String,Object> params) {
		return mapper.ChangePassword(params);
	}
	
}