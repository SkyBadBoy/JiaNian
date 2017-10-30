package wtb.core.service;
import java.util.*;

import wtb.core.data.AdvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Advert;

/**
 * wtb_vitaes
 */
 @Service
public class AdvertService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private AdvertMapper mapper;

	@Transactional
	public int addAdvert(Advert obj) {
		return mapper.addAdvert(obj);
	}

	@Transactional
	public int updateAdvert(Advert obj) {
		return mapper.updateAdvert(obj);
	}

	@Transactional
	public int deleteAdvert(Map<String,Object> params) {
		return mapper.deleteAdvert(params);
	}
}