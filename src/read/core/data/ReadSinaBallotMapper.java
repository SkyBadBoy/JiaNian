package read.core.data;
import java.util.*;

import wtb.core.model.SinaBallot;

/**
 * wtb_SinaBallot
 */
public interface ReadSinaBallotMapper {
	/**
	 * 查询全部
	 */
	 public List<SinaBallot> getSinaBallotList(Map<String, Object> params);
	
	 public int getSinaBallotEffCount(Map<String,Object> params);

}