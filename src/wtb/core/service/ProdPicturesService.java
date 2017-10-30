package wtb.core.service;
import java.util.*;

import wtb.core.data.PictureMapper;
import wtb.core.data.ProdPictureMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Pictures;
import wtb.core.model.ProdPictures;

/**
 * wtb_vitaes
 */
 @Service
public class ProdPicturesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ProdPictureMapper mapper;

	@Transactional(readOnly = true)
	public List<ProdPictures> getPicturesList(Map<String,Object> params) {
		return mapper.getPictureList(params);
	}
	@Transactional(readOnly = true)
	public List<ProdPictures> getPictureForList(Map<String,Object> params) {
		return mapper.getPictureForList(params);
	}
	@Transactional
	public int addPictures(ProdPictures obj) {
		return mapper.addPicture(obj);
	}

	@Transactional
	public int updatePictures(ProdPictures obj) {
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
	public int getPictureCount(Map<String,Object> params){
		return mapper.getPictureCount(params);
	}
	
	@Transactional
	public List<ProdPictures> getPictureEffList(Map<String, Object> params){
		return mapper.getPictureEffList(params);
	}
	
	@Transactional
	public int getPictureEffCount(Map<String, Object> params){
		return mapper.getPictureEffCount(params);
	}
	
	@Transactional
	public int UpdateSimplePicture(ProdPictures Picture) {
		return mapper.UpdateSimplePicture(Picture);
	}
	@Transactional
	public int DeleteAllPicByProductID(long NoticeID) {
		return mapper.DeleteAllPicByProductID(NoticeID);
	}
	
}