package wtb.core.service;
import java.util.*;

import wtb.core.data.BaseInfoMapper;
import wtb.core.data.WeChatBannerMapper;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.BaseInfo;

import java.sql.SQLException;
@SuppressWarnings("unchecked")
/**
 * wtb_vitaes
 */
 @Service
public class BaseInfoService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private BaseInfoMapper mapper;

	@Transactional(readOnly = true)
	public List<BaseInfo> getBaseInfoList(Map<String,Object> params) {
		return mapper.getBaseInfoList(params);
	}
	
	@Transactional
	public int addBaseInfo(BaseInfo obj) {
		return mapper.addBaseInfo(obj);
	}

	@Transactional
	public int updateBaseInfo(BaseInfo obj) {
		return mapper.updateBaseINfo(obj);
	}

	@Transactional
	public int deleteBaseInfo(Map<String,Object> params) {
		return mapper.deleteBaseInfo(params);
	}
	@Transactional
	public List<BaseInfo> getBaseInfoListByID(Map<String, Object> params){
		return mapper.getBaseInfoListByID(params);
	}
}