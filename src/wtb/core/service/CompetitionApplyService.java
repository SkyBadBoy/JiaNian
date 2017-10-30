package wtb.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.CompetitionApplyMapper;
import wtb.core.model.CompetitionApply;

@Service
public class CompetitionApplyService implements CompetitionApplyMapper {

	@Autowired
	private CompetitionApplyMapper mapper;
	@Override
	@Transactional()
	public int addCompetition(CompetitionApply CompetitionApply) {
		return mapper.addCompetition(CompetitionApply);
	}

	@Override
	@Transactional()
	public int updateCompetition(CompetitionApply CompetitionApply) {
		return mapper.updateCompetition(CompetitionApply);
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
