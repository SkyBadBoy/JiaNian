package wtb.core.service;
import java.util.*;

import wtb.core.data.PaidListingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.PaidListing;

/**
 * wtb_vitaes
 */
 @Service
public class PaidListingService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private PaidListingMapper mapper;

	@Transactional(readOnly = true)
	public List<PaidListing> getPaidListingList(Map<String,Object> params) {
		return mapper.getPaidListingList(params);
	}
	
	@Transactional
	public int addPaidListing(PaidListing obj) {
		return mapper.addPaidListing(obj);
	}

	@Transactional
	public int updatePaidListing(PaidListing obj) {
		return mapper.updatePaidListing(obj);
	}

	@Transactional
	public int deletePaidListing(Map<String,Object> params) {
		return mapper.deletePaidListing(params);
	}
	@Transactional
	public int enabledPaidListing(Map<String,Object> params) {
		return mapper.enabledPaidListing(params);
	}
	@Transactional
	public int UpLevevProduct(Map<String,Object> params) {
		return mapper.UpLevevProduct(params);
	}
	@Transactional
	public int TopLevevProduct(Map<String,Object> params) {
		return mapper.TopLevevProduct(params);
	}
	@Transactional
	public int getPaidListingCount(Map<String,Object> params){
		return mapper.getPaidListingCount(params);
	}
	
}