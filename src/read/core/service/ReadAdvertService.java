package read.core.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadAdvertMapper;
import wtb.core.model.Advert;
/**
 * wtb_vitaes
 */
 @Service
public class ReadAdvertService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadAdvertMapper mapper;

	@Transactional(readOnly = true)
	public List<Advert> getAdvertList(Map<String,Object> params) {
		return mapper.getAdvertList(params);
	}
	
	@Transactional(readOnly = true)
	public long getAdvertCount(Map<String,Object> params) {
		return mapper.getAdvertCount(params);
	}
	@Transactional
	public Advert getAdvertListByID(long params){
		return mapper.getAdvertListByID(params);
	}
}