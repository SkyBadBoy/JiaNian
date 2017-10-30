package wtb.core.data;

import wtb.core.model.LotteryNumber;

public interface LotteryNumberMapper {
	public int deleteByPrimaryKey(Long lotteryId);

	public int insert(LotteryNumber record);

	public int insertSelective(LotteryNumber record);


	public int updateByPrimaryKeySelective(LotteryNumber record);

	public int updateByPrimaryKey(LotteryNumber record);
    
}