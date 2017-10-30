package wtb.core.data;

import java.util.Map;

import wtb.core.model.LotteryChance;

public interface LotteryChanceMapper {
	public int deleteByPrimaryKey(Long chanceId);

	public int insert(LotteryChance record);

	public int insertSelective(LotteryChance record);


	public int updateByPrimaryKeySelective(LotteryChance record);

	public int updateByPrimaryKey(LotteryChance record);
    
    
    /*by Sheryl*/
	public int AddNewMessage(LotteryChance record);
    
	public int AddChanceCount(Map<String, Object> map);
    
}