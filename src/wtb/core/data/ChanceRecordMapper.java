package wtb.core.data;

import wtb.core.model.ChanceRecord;

public interface ChanceRecordMapper {
    int deleteByPrimaryKey(Long recordId);

    int insert(ChanceRecord record);

    int insertSelective(ChanceRecord record);


    int updateByPrimaryKeySelective(ChanceRecord record);

    int updateByPrimaryKey(ChanceRecord record);
    
    /* by Sheryl */
    public int AddNewMessage(ChanceRecord record);
}