package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.ErrorStat;

public interface ReadErrorStatMapper {
	public List<ErrorStat> getErrorStatList(Map<String, Object> map);
	public int getErrorStatCount(Map<String, Object> map);

}
