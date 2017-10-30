package wtb.core.data;

import wtb.core.model.LotteryRecord;

public interface LotteryRecordMapper {
	public int deleteByPrimaryKey(Long recordId);

	public int insert(LotteryRecord record);

	public int insertSelective(LotteryRecord record);


	public int updateByPrimaryKeySelective(LotteryRecord record);

	public int updateByPrimaryKey(LotteryRecord record);
	
	/* by Sheryl */
    public int AddNewMessage(LotteryRecord record);
}