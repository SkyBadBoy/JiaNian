package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.QuestionStatMapper;
import wtb.core.model.QuestionStat;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class QuestionStatService implements QuestionStatMapper { // 在线表
    @Autowired
    private QuestionStatMapper mapper;

    //  增
    @Transactional
    public int addQuestionStat(QuestionStat QuestionStat) {
        return mapper.addQuestionStat(QuestionStat);
    }

    //  删
    @Transactional
    public int removeQuestionStat(QuestionStat QuestionStat) {
        return mapper.removeQuestionStat(QuestionStat);
    }

    //  改
    @Transactional
    public int changeQuestionStat(QuestionStat QuestionStat) {
        return mapper.changeQuestionStat(QuestionStat);
    }

	@Override
	public int changeQuestionStatRightCount(QuestionStat QuestionStat) {
		return mapper.changeQuestionStatRightCount(QuestionStat);
	}

   
}