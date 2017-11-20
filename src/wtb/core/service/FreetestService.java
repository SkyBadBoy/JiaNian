/*
 * 2017/11/18 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package wtb.core.service;
import java.util.*;

import wtb.core.data.FreetestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Freetest;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
 @Service
public class FreetestService {

    /**
     * <p>default constants</p>
     */
     @Autowired
	private FreetestMapper mapper;
    
  /**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public Freetest FindByFreetestId(long id) {
		return mapper.FindByFreetestId(id);
	}

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public List<Freetest> FindFreetestsByCondition(Map<String,Object> params) {
		return mapper.FindFreetestsByCondition(params);
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
    public int addFreetest(Freetest object){
		return mapper.addFreetest(object);
	}
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int UpdateFreetest(Freetest object){
		return mapper.UpdateFreetest(object);
	}
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int DeleteFreetestById(Map<String,Object> params){
		return mapper.DeleteFreetestById(params);
	}
	
	/**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int EnabledFreetestById(Map<String,Object> params){
		return mapper.EnabledFreetestById(params);
	}
}
