package wtb.core.data;

import wtb.core.model.Competition;

public interface CompetitionMapper {
	
	public int addCompetition(Competition Competition);
	
	public int updateCompetition(Competition Competition);
	
	public int deleteCompetition(Long ID);
	
	public int enabledCompetition(Long ID);

}
