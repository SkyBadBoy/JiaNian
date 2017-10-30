package read.core.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadPrizesMapper;
import wtb.core.model.Prizes;

/**
 * wtb_vitaes
 */
@Service
public class ReadPrizesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadPrizesMapper mapper;

	@Transactional
	public List<Prizes> QueryAll(){
		return mapper.QueryAll();
	}
	
	@Transactional
	public int QueryOne(long ID) {
		return mapper.QueryOne(ID);
	}
	
	@Transactional
	public List<Prizes> QuertAllBykind() {
		return mapper.QuertAllBykind();
	}
	
	@Transactional
	public Prizes QueryByType(int type){
		return mapper.QueryByType(type);
	}
	
	@Transactional
	public int QueryOneByType(int type) {
		return mapper.QueryOneByType(type);
	}
	@Transactional
	public Prizes QueryPrize(Map<String, Object> map) {
		return mapper.QueryPrize(map);
	}
	
}