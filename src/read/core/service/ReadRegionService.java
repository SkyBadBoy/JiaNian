package read.core.service;
import java.util.*;

import wtb.core.data.RegionMapper;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadRegionMapper;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import wtb.core.model.Region;
import java.sql.SQLException;
@SuppressWarnings("unchecked")
/**
 * wtb_region
 */
 @Service
public class ReadRegionService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadRegionMapper mapper;

	@Transactional(readOnly = true)
	public List<Region> getRegionList(Map<String,Object> params) {
		return mapper.getRegionList(params);
	}
	@Transactional
	 public List<Region> getRegionByIDList(String params)
	 {
		 return mapper.getRegionByIDList(params);
	 }
	@Transactional
	 public int getRegionCount(Map<String,Object> params)
	 {
		 return mapper.getRegionCount(params);
	 }
	
}