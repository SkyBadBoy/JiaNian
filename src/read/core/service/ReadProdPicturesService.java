package read.core.service;
import java.util.*;

import wtb.core.data.PictureMapper;
import wtb.core.data.ProdPictureMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadProdPictureMapper;
import wtb.core.model.Pictures;
import wtb.core.model.ProdPictures;

/**
 * wtb_vitaes
 */
 @Service
public class ReadProdPicturesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadProdPictureMapper mapper;

	@Transactional(readOnly = true)
	public List<ProdPictures> getPictureByIDList(Map<String,Object> params) {
		return mapper.getPictureByIDList(params);
	}
	
	@Transactional(readOnly = true)
	public List<ProdPictures> getPictureList(Map<String,Object> params) {
		return mapper.getPictureList(params);
	}
	
	@Transactional(readOnly = true)
	public int getPictureCount(Map<String,Object> params) {
		return mapper.getPictureCount(params);
	}
	
	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年11月14日 下午5:38:06 
		 * @Details 获取广告位
	 */
	@Transactional(readOnly = true)
	public List<ProdPictures> getPicturePhoneList() {
		return mapper.getPicturePhoneList();
	}
	
	
	
}