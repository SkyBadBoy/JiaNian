package wtb.core.service;
import java.util.*;

import wtb.core.data.PermissionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Permissions;

/**
 * wtb_vitaes
 */
 @Service
public class PermissionsService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private PermissionsMapper mapper;

	@Transactional(readOnly = true)
	public List<Permissions> getPermissionsList(Map<String,Object> params) {
		return mapper.getPermissionsList(params);
	}
	
	@Transactional
	public int addPermissions(Permissions obj) {
		return mapper.addPermissions(obj);
	}

	@Transactional
	public int updatePermissions(Permissions obj) {
		return mapper.updatePermissions(obj);
	}

	@Transactional
	public int deletePermissions(Map<String,Object> params) {
		return mapper.deletePermissions(params);
	}
	
	@Transactional
	public int enabledPermissions(Map<String,Object> params) {
		return mapper.enabledPermissions(params);
	}
	@Transactional
	public int getPermissionsCount(Map<String,Object> params){
		return mapper.getPermissionsCount(params);
	}
	
}