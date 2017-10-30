package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.CompetitionApply;

public interface ReadCompetitionApplyMapper {
	
	public List<CompetitionApply> getCompetitionList(Map<String, Object> params);
	
	public int getCompetitionCount(Map<String, Object> params);

}
