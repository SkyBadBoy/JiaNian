package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadQuestionLimitMapper;
import wtb.core.data.QuestionLimitMapper;
import wtb.core.model.QuestionLimit;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class ReadQuestionLimitService implements ReadQuestionLimitMapper { // 在线表
    @Autowired
    private ReadQuestionLimitMapper mapper;

   

    //  查
    @Transactional(readOnly = true)
    public int findQuestionLimit(Map<String, Object> param) {
        return mapper.findQuestionLimit(param);
    }

    //  搜
    @Transactional(readOnly = true)
    public QuestionLimit searchQuestionLimit(QuestionLimit QuestionLimit) {
        return mapper.searchQuestionLimit(QuestionLimit);
    }

    //  找
    @Transactional(readOnly = true)
    public List<QuestionLimit> queryQuestionLimit(Map<String, Object> param) {
        return mapper.queryQuestionLimit(param);
    }

}