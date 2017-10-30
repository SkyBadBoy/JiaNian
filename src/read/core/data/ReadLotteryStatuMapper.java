package read.core.data;

import java.util.Map;

import wtb.core.model.LotteryStatu;

public interface ReadLotteryStatuMapper {
  

    LotteryStatu selectByPrimaryKey(Long lotteryId);

  
    
    /* by Sheryl */
//    public Map<String,Object> QueryMessage(Map<String, Object> map);
    public LotteryStatu QueryMessage(Map<String, Object> map);
    
    public int QueryExist(long ID);
}