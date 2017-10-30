package read.core.service;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadDeviceMapper;
import wtb.core.model.Device;

/**
 * wtb_vitaes
 */
 @Service
public class ReadDeviceService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadDeviceMapper mapper;

	@Transactional(readOnly = true)
	public List<Device> getDeviceList(Map<String,Object> params) {
		return mapper.getDeviceList(params);
	}
	

	@Transactional
	public int getDeviceCount(Map<String,Object> params){
		return mapper.getDeviceCount(params);
	}

	
}