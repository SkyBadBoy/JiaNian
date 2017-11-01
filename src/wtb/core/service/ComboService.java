/*
 * 2017/10/31 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package wtb.core.service;
import java.util.*;

import wtb.core.data.ComboMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Combo;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
 @Service
public class ComboService {

    /**
     * <p>default constants</p>
     */
     @Autowired
	private ComboMapper mapper;
    
  /**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public Combo FindByComboId(long id) {
		return mapper.FindByComboId(id);
	}

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public List<Combo> FindCombosByCondition(Map<String,Object> params) {
		return mapper.FindCombosByCondition(params);
	}

	/**
	 * <p>根据条件查询记录数</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public int getCountByCondition(Map<String,Object> params) {
		return mapper.getCountByCondition(params);
	}

	/**
	 * <p>添加记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int addCombo(Combo object){
		return mapper.addCombo(object);
	}
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int UpdateCombo(Combo object){
		return mapper.UpdateCombo(object);
	}
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int DeleteComboById(Map<String,Object> params){
		return mapper.DeleteComboById(params);
	}
	
	/**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int EnabledComboById(Map<String,Object> params){
		return mapper.EnabledComboById(params);
	}
}
