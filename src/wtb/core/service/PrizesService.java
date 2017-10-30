package wtb.core.service;

import wtb.core.data.PrizesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 @Service
public class PrizesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private PrizesMapper mapper;

	@Transactional
	public int CutPrizeCount(long ID) {
		return mapper.CutPrizeCount(ID);
	}
	
}