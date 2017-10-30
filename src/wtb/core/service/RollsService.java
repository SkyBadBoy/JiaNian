package wtb.core.service;

import wtb.core.data.RollsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import wtb.core.model.Rolls;
//import java.sql.SQLException;
//@SuppressWarnings("unchecked")


 @Service
public class RollsService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private RollsMapper mapper;

	@Transactional
	public int AddRollMessage(Rolls record) {
		return mapper.AddRollMessage(record);
	}
	
}