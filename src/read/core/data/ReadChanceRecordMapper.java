package read.core.data;

import java.util.Map;

import wtb.core.model.ChanceRecord;

public interface ReadChanceRecordMapper {
   
    ChanceRecord selectByPrimaryKey(Long recordId);
    
    /* by Sheryl */
    public int QueryCount(Map<String, Object> map);
}