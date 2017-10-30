package wtb.core.data;

import wtb.core.model.Prizes;

public interface PrizesMapper {
	public int deleteByPrimaryKey(Long prizeId);

	public int insert(Prizes record);

	public int insertSelective(Prizes record);

	public int updateByPrimaryKeySelective(Prizes record);

	public int updateByPrimaryKey(Prizes record);
    
    
    /* by Sheryl */
	public int CutPrizeCount(long ID);
    
}