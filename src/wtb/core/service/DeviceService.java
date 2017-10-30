package wtb.core.service;
import java.util.*;

import wtb.core.data.DeviceMapper;
import wtb.core.data.DeviceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Device;

/**
 * wtb_vitaes
 */
 @Service
public class DeviceService implements DeviceMapper{
	/**
	 * 查询全部
	 */
	 @Autowired
	private DeviceMapper mapper;


	
	@Transactional
	public int addDevice(Device obj) {
		return mapper.addDevice(obj);
	}

	@Transactional
	public int updateDevice(Device obj) {
		return mapper.updateDevice(obj);
	}

	@Transactional
	public int deleteDevice(Map<String,Object> params) {
		return mapper.deleteDevice(params);
	}
	
	@Transactional
	public int IsNotUsing(long UserID) {
		return mapper.IsNotUsing(UserID);
	}
	
	
	 
}