package read.core.service;
import java.util.*;

import wtb.core.json.ReturnRolls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadRollsMapper;
/**
 * wtb_region
 */
 @Service
public class ReadRollsService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadRollsMapper mapper;

	@Transactional(readOnly = true)
	public List<ReturnRolls> QueryByType(Map<String, Object> map) {
		return mapper.QueryByType(map);
	}
	
	@Transactional(readOnly = true)
	public int QueryCountByType(Map<String, Object> map) {
		return mapper.QueryCountByType(map);
	}
	
}