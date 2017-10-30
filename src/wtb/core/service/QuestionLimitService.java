package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.QuestionLimitMapper;
import wtb.core.model.QuestionLimit;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class QuestionLimitService implements QuestionLimitMapper { // 在线表
    @Autowired
    private QuestionLimitMapper mapper;

    //  增
    @Transactional
    public int addQuestionLimit(QuestionLimit QuestionLimit) {
        return mapper.addQuestionLimit(QuestionLimit);
    }

    //  删
    @Transactional
    public int removeQuestionLimit(QuestionLimit QuestionLimit) {
        return mapper.removeQuestionLimit(QuestionLimit);
    }

    //  改
    @Transactional
    public int changeQuestionLimit(QuestionLimit QuestionLimit) {
        return mapper.changeQuestionLimit(QuestionLimit);
    }

   
}