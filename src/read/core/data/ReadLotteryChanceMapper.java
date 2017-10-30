package read.core.data;

import java.util.Map;

import org.springframework.stereotype.Repository;

import wtb.core.model.LotteryChance;

public interface ReadLotteryChanceMapper {

	public LotteryChance selectByPrimaryKey(Long chanceId);

    /* by Sheryl */
	public int QueryCount(Map<String, Object> map);
	
	public int QueryBelong(Map<String, Object> map);
    
}