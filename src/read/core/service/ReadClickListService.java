package read.core.service;
import java.util.*;

import wtb.core.data.ClickListMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadClickListMapper;
import wtb.core.model.ClickList;

/**
 * wtb_vitaes
 */
 @Service
public class ReadClickListService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadClickListMapper mapper;

	@Transactional(readOnly = true)
	public List<ClickList> getClickListList(Map<String,Object> params) {
		return mapper.getClickListList(params);
	}
	
	
	
	@Transactional 
	public int CheckExistByIdAddress(Map<String,Object> params){
		return mapper.CheckExistByIdAddress(params);
	}
	
}