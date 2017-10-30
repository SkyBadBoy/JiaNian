package read.core.data;

import wtb.core.model.LotteryNumber;

public interface ReadLotteryNumberMapper {


	public LotteryNumber selectByPrimaryKey(Long lotteryId);

	
}