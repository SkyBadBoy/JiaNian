package wtb.core.service;
import java.util.*;

import wtb.core.data.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.model.Product;
/**
 * wtb_Product
 */
 @Service
public class ProductService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ProductMapper mapper;

	@Transactional(readOnly = true)
	public List<Product> getProductList(Map<String,Object> params) {
		return mapper.getProductList(params);
	}
	
	@Transactional
	public int addProduct(Product obj) {
		return mapper.addProduct(obj);
	}

	@Transactional
	public int updateProduct(Product obj) {
		return mapper.updateProduct(obj);
	}

	@Transactional
	public int deleteProduct(Map<String,Object> params) {
		return mapper.deleteProduct(params);
	}
	
	@Transactional
	public int enabledProduct(Map<String,Object> params) {
		return mapper.enabledProduct(params);
	}
	
	@Transactional
	public int TopLevevProduct(Map<String,Object> params) {
		return mapper.TopLevevProduct(params);
	}
	
	@Transactional
	public int getProductByMaxList(Map<String,Object> params) {
		return mapper.TopLevevProduct(params);
	}
	@Transactional
	public int UpClickCount(Map<String,Object> params) {
		return mapper.UpClickCount(params);
	}
	@Transactional
	public int getProductCount(Map<String,Object> params){
		return mapper.getProductCount(params);
	}
	
}