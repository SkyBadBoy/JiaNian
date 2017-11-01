/*
 * 2017/10/31 huban Creating
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package wtb.core.data;
import java.util.*;
import wtb.core.model.Combo;

/**
 * <p> Mapper Class</p>
 *
 * @author DX
 * @version 1.00
 */
public interface ComboMapper {


	/**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public Combo FindByComboId(long id);

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public List<Combo> FindCombosByCondition(Map<String,Object> params);

	/**
	 * <p>根据条件查询记录数</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int getCountByCondition(Map<String,Object> params) ;

	/**
	 * <p>添加记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int addCombo(Combo object);
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int UpdateCombo(Combo object);
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int DeleteComboById(Map<String,Object> params);
    
     /**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int EnabledComboById(Map<String,Object> params);

   

}
