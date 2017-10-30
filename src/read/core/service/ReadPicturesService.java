package read.core.service;
import java.util.*;

import wtb.core.data.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadPictureMapper;
import wtb.core.model.Pictures;

/**
 * wtb_vitaes
 */
 @Service
public class ReadPicturesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadPictureMapper mapper;

	@Transactional(readOnly = true)
	public List<Pictures> getPicturesList(Map<String,Object> params) {
		return mapper.getPictureList(params);
	}
	
	@Transactional
	public List<Pictures> getPictureEffList(Map<String, Object> params){
		return mapper.getPictureEffList(params);
	}

}