package wtb.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.CompetitionMapper;
import wtb.core.model.Competition;

@Service
public class CompetitionService implements CompetitionMapper {

	@Autowired
	private CompetitionMapper mapper;
	@Override
	@Transactional()
	public int addCompetition(Competition Competition) {
		return mapper.addCompetition(Competition);
	}

	@Override
	@Transactional()
	public int updateCompetition(Competition Competition) {
		return mapper.updateCompetition(Competition);
	}

	@Override
	@Transactional()
	public int deleteCompetition(Long ID) {
		return  mapper.deleteCompetition(ID);
	}

	@Override
	@Transactional()
	public int enabledCompetition(Long ID) {
		return mapper.enabledCompetition(ID);
	}

}
