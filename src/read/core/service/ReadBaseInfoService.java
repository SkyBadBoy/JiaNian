package read.core.service;
import java.util.*;

import wtb.core.data.ActivityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadActivityMapper;
import read.core.data.ReadBaseInfoMapper;
import wtb.core.model.Activity;
import wtb.core.model.BaseInfo;

/**
 * wtb_vitaes
 */
 @Service
public class ReadBaseInfoService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadBaseInfoMapper mapper;

	@Transactional(readOnly = true)
	public BaseInfo getBaseInfoList(long params) {
		return mapper.getBaseInfoList(params);
	}
	
	@Transactional(readOnly = true)
	public  List<BaseInfo>  getBaseInfoListByID(Map<String,Object> params) {
		return mapper.getBaseInfoListByID(params);
	}
	
	@Transactional(readOnly = true)
	public  int getBaseInfoCount(Map<String,Object> params) {
		return mapper.getBaseInfoCount(params);
	}
	

	
}