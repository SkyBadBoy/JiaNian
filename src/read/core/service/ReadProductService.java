package read.core.service;
import java.util.*;

import wtb.core.data.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadProductMapper;
import wtb.core.model.Product;
/**
 * wtb_Product
 */
 @Service
public class ReadProductService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadProductMapper mapper;

	@Transactional(readOnly = true)
	public List<Product> getProductList(Map<String,Object> params) {
		return mapper.getProductList(params);
	}

	@Transactional
	public int getProductCount(Map<String,Object> params){
		return mapper.getProductCount(params);
	}
	
}