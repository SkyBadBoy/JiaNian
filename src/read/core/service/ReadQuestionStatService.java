package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadQuestionStatMapper;
import wtb.core.data.QuestionStatMapper;
import wtb.core.model.QuestionStat;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class ReadQuestionStatService implements ReadQuestionStatMapper { // 在线表
    @Autowired
    private ReadQuestionStatMapper mapper;

   

    //  查
    @Transactional(readOnly = true)
    public long findQuestionStat(Map<String, Object> param) {
        return mapper.findQuestionStat(param);
    }

    //  搜
    @Transactional(readOnly = true)
    public QuestionStat searchQuestionStat(long param) {
        return mapper.searchQuestionStat(param);
    }

    //  找
    @Transactional(readOnly = true)
    public List<QuestionStat> queryQuestionStat(Map<String, Object> param) {
        return mapper.queryQuestionStat(param);
    }
    
    @Transactional(readOnly = true)
    public List<QuestionStat> queryQuestionStatRank(Map<String, Object> param) {
        return mapper.queryQuestionStatRank(param);
    }
    
    @Transactional(readOnly = true)
    public List<QuestionStat> queryQuestionStatRankBySelf(Map<String, Object> param) {
        return mapper.queryQuestionStatRankBySelf(param);
    }


}