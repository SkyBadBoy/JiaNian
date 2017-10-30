package wtb.core.service;
import java.util.*;

import wtb.core.data.VoteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Vote;

/**
 * wtb_vitaes
 */
 @Service
public class VoteService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private VoteMapper mapper;

	
	@Transactional
	public int addVote(Vote obj) {
		return mapper.addVote(obj);
	}

	@Transactional
	public int updateVote(Vote obj) {
		return mapper.updateVote(obj);
	}

	@Transactional
	public int deleteVote(Map<String,Object> params) {
		return mapper.deleteVote(params);
	}


	
}