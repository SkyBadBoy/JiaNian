package read.core.service;
import java.util.*;

import wtb.core.data.VoteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadVoteMapper;
import wtb.core.model.Vote;

/**
 * wtb_vitaes
 */
 @Service
public class ReadVoteService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadVoteMapper mapper;

	@Transactional(readOnly = true)
	public List<Vote> getVoteList(Map<String,Object> params) {
		return mapper.getVoteList(params);
	}

	@Transactional
	public int getVoteCount(Map<String,Object> params){
		return mapper.getVoteCount(params);
	}


	
}