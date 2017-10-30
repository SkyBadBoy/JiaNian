package read.core.service;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadChanceRecordMapper;
import read.core.data.ReadClickListMapper;
import wtb.core.model.ClickList;

/**
 * wtb_vitaes
 */
 @Service
public class ReadChanceRecordService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadChanceRecordMapper mapper;

	@Transactional(readOnly = true)
	public int QueryCount(Map<String, Object> map) {
		return mapper.QueryCount(map);
	}
}