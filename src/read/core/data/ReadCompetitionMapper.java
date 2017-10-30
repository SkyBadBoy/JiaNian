package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.Competition;

public interface ReadCompetitionMapper {
	
	public List<Competition> getCompetitionList(Map<String, Object> params);
	
	public int getCompetitionCount(Map<String, Object> params);

}
