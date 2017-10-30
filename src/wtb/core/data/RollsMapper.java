package wtb.core.data;

import wtb.core.model.Rolls;

public interface RollsMapper {
	public int deleteByPrimaryKey(Long rollId);

	public int insert(Rolls record);

	public int insertSelective(Rolls record);


	public int updateByPrimaryKeySelective(Rolls record);

	public int updateByPrimaryKey(Rolls record);
    
    
    /* by Sheryl */
	public int AddRollMessage(Rolls record);
}