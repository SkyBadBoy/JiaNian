package read.core.service;
import java.util.*;

import wtb.core.data.UsersMapper;
import wtb.core.data.WeChatBannerMapper;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadUsersMapper;
import wtb.core.model.Users;

import java.sql.SQLException;
@SuppressWarnings("unchecked")
/**
 * wtb_vitaes
 */
 @Service
public class ReadUsersService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadUsersMapper mapper;

	@Transactional(readOnly = true)
	public List<Users> getUsersList(Map<String,Object> params) {
		return mapper.getUsersList(params);
	}
	
	@Transactional
	public List<Users> getHomeUsersList(Map<String,Object> params) {
		return mapper.getHomeUsersList(params);
	}
	
	@Transactional
	public int getUsersCount(Map<String,Object> params) {
		return mapper.getUsersCount(params);
	}
	
	
}