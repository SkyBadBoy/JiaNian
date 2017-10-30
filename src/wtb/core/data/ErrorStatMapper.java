package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.ErrorStat;

public interface ErrorStatMapper {
	
	public int addErrorStat(ErrorStat ErrorStat);
	public int deleteErrorStat(Map<String , Object> map);
	public int updateErrorStat(ErrorStat ErrorStat);
	public int updateErrorStatByClassName(long ErrorStat);
	
}
