package read.core.data;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import wtb.core.model.Prizes;

public interface ReadPrizesMapper {
  

    Prizes selectByPrimaryKey(Long prizeId);

   
    /* by Sheryl */
    List<Prizes> QueryAll();
    
    int QueryOne(long ID);
    
    List<Prizes> QuertAllBykind();
    
    Prizes QueryByType(int type);
    
    int QueryOneByType(int type);
    
    Prizes QueryPrize(Map<String, Object> map);
    
}