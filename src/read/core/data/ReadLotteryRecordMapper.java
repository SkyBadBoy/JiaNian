package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.LotteryRecord;

public interface ReadLotteryRecordMapper {
	

	public LotteryRecord selectByPrimaryKey(Long recordId);

    
    /* by Sheryl */
	public List<LotteryRecord> QueryRecordAll(Map<String, Object> map);
	
	public List<LotteryRecord> QueryMyAll(Map<String, Object> map);
    
}