package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.json.ReturnRolls;
import wtb.core.model.Rolls;

public interface ReadRollsMapper {
  
    public Rolls selectByPrimaryKey(Long rollId);

 
    
    /* by Sheryl */
    public List<ReturnRolls> QueryByType(Map<String, Object> map);
    
    public int QueryCountByType(Map<String, Object> map);
    
}