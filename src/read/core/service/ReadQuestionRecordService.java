package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadQuestionRecordMapper;
import wtb.core.data.QuestionRecordMapper;
import wtb.core.model.QuestionRecord;
import wtb.core.model.QuestionRecord;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class ReadQuestionRecordService implements ReadQuestionRecordMapper { // 在线表
    @Autowired
    private ReadQuestionRecordMapper mapper;


    //  查
    @Transactional(readOnly = true)
    public int findQuestionRecord(QuestionRecord QuestionRecord) {
        return mapper.findQuestionRecord(QuestionRecord);
    }

    //  搜
    @Transactional(readOnly = true)
    public QuestionRecord searchQuestionRecord(QuestionRecord QuestionRecord) {
        return mapper.searchQuestionRecord(QuestionRecord);
    }

    //  找
    @Transactional(readOnly = true)
    public List<QuestionRecord> queryQuestionRecord(Map<String, Object> param) {
        return mapper.queryQuestionRecord(param);
    }

   
}