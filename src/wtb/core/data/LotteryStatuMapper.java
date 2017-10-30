package wtb.core.data;

import wtb.core.model.LotteryStatu;

public interface LotteryStatuMapper {
    int deleteByPrimaryKey(Long lotteryId);

    int insert(LotteryStatu record);

    int insertSelective(LotteryStatu record);


    int updateByPrimaryKeySelective(LotteryStatu record);

    int updateByPrimaryKey(LotteryStatu record);
    
    public int AddNewMessage(LotteryStatu record);
    /**
	  * 增加和减少剩余抽奖次数
	  */
	 public int AddCount(long ID);
	 public int AddTotalCount(long ID);
	 public int AddTwoCount(long ID);
	 
	 public int CutCount(long ID);
	 
	 public int ChangeLimit(long ID);
    
}