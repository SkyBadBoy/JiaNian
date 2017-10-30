package wtb.core.service;
import java.util.*;

import wtb.core.data.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Pictures;

/**
 * wtb_vitaes
 */
 @Service
public class PicturesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private PictureMapper mapper;

	@Transactional(readOnly = true)
	public List<Pictures> getPicturesList(Map<String,Object> params) {
		return mapper.getPictureList(params);
	}
	
	@Transactional
	public int addPictures(Pictures obj) {
		return mapper.addPicture(obj);
	}

	@Transactional
	public int updatePictures(Pictures obj) {
		return mapper.updatePicture(obj);
	}

	@Transactional
	public int deletePictures(Map<String,Object> params) {
		return mapper.deletePicture(params);
	}
	
	@Transactional
	public int enabledPictures(Map<String,Object> params) {
		return mapper.enabledPicture(params);
	}
	@Transactional
	public List<Pictures> getPictureEffList(Map<String, Object> params){
		return mapper.getPictureEffList(params);
	}
	
	@Transactional
	public int getPictureEffCount(Map<String, Object> params){
		return mapper.getPictureEffCount(params);
	}
	
	
	
	
}