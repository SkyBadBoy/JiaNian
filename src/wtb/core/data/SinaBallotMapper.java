package wtb.core.data;
import java.util.*;

import wtb.core.model.SinaBallot;

/**
 * wtb_SinaBallot
 */
public interface SinaBallotMapper {
	/**
	 * 查询全部
	 */
	 public List<SinaBallot> getSinaBallotList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updateSinaBallot(SinaBallot SinaBallot);
	 /**
	 * 停用
	 */
	 public int deleteSinaBallot(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addSinaBallot(SinaBallot SinaBallot);
	 
	 /**
	  * 启用
	  * @param params
	  * @return
	  */
	 public int enabledSinaBallot(Map<String,Object> params);
	 

	 public int getSinaBallotEffCount(Map<String,Object> params);

}