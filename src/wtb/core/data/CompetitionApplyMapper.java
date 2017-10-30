package wtb.core.data;

import wtb.core.model.CompetitionApply;

public interface CompetitionApplyMapper {
	
	public int addCompetition(CompetitionApply CompetitionApply);
	
	public int updateCompetition(CompetitionApply CompetitionApply);
	
	public int deleteCompetition(Long ID);
	
	public int enabledCompetition(Long ID);

}
