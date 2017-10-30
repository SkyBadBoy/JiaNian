package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadCompetitionApplyMapper;
import wtb.core.data.CompetitionApplyMapper;
import wtb.core.model.CompetitionApply;

@Service
public class ReadCompetitionApplyService implements ReadCompetitionApplyMapper {

	@Autowired
	private ReadCompetitionApplyMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<CompetitionApply> getCompetitionList(Map<String, Object> params) {
		return mapper.getCompetitionList(params);
	}

	@Override
	@Transactional()
	public int getCompetitionCount(Map<String, Object> params) {
		return mapper.getCompetitionCount(params);
	}

}
