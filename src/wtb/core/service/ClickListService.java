package wtb.core.service;
import java.util.*;

import wtb.core.data.ClickListMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.ClickList;

/**
 * wtb_vitaes
 */
 @Service
public class ClickListService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ClickListMapper mapper;

	@Transactional(readOnly = true)
	public List<ClickList> getClickListList(Map<String,Object> params) {
		return mapper.getClickListList(params);
	}
	
	@Transactional
	public int addClickList(ClickList obj) {
		return mapper.addClickList(obj);
	}

	@Transactional
	public int updateClickList(ClickList obj) {
		return mapper.updateClickList(obj);
	}

	@Transactional
	public int deleteClickList(Map<String,Object> params) {
		return mapper.deleteClickList(params);
	}
	
	@Transactional
	public int CheckExistByIdAddress(Map<String,Object> params){
		return mapper.CheckExistByIdAddress(params);
	}
	
}