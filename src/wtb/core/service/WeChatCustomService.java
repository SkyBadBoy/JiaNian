package wtb.core.service;
import java.util.*;

import wtb.core.data.WeChatCustomMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.model.WeChatCustom;

/**
 * wtb_vitaes
 */
 @Service
public class WeChatCustomService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private WeChatCustomMapper mapper;

	/**
	 * 查询全部
	 */
	 @Transactional(readOnly = true)
	 public List<WeChatCustom> getWeChatCustomList(Map<String, Object> params)
	 {
		 return mapper.getWeChatCustomList(params);
	 }
	 /**
	 * 修改记录
	 */
	 @Transactional
	 public int updateWeChatCustom(WeChatCustom WeChatCustom)
	 {
		 return mapper.updateWeChatCustom(WeChatCustom);
	 }
	 /**
	 * 删除记录
	 */
	 @Transactional
	 public int deleteWeChatCustom(Map<String,Object> params)
	 {
		 return mapper.deleteWeChatCustom(params);
	 }
	 /**
	 * 添加记录
	 */
	 @Transactional
	 public int addWeChatCustom(WeChatCustom WeChatCustom)
	 {
		 return mapper.addWeChatCustom(WeChatCustom);
	 }
	 @Transactional
	 public int getWeChatCustomCount(Map<String,Object> params)
	 {
		 return mapper.getWeChatCustomCount(params);
	 }
	
}