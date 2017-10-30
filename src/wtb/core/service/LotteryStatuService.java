package wtb.core.service;

import wtb.core.data.LotteryStatuMapper;
import wtb.core.model.LotteryStatu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * wtb_vitaes
 */
 @Service
public class LotteryStatuService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private LotteryStatuMapper mapper;
	
	/* by Sheryl */
	 @Transactional
	 public int AddNewMessage(LotteryStatu record) {
		 return mapper.AddNewMessage(record);
	 }
	 
	@Transactional
	public int AddCount(long ID) {
		return mapper.AddCount(ID);
	}
	
	@Transactional
	public int CutCount(long ID) {
		return mapper.CutCount(ID);
	}
	
	@Transactional
	public int AddTotalCount(long ID) {
		return mapper.AddTotalCount(ID);
	}
	
	@Transactional
	public int AddTwoCount(long ID) {
		return mapper.AddTwoCount(ID);
	}
	
	@Transactional
	public int ChangeLimit(long ID) {
		return mapper.ChangeLimit(ID);
	}
}