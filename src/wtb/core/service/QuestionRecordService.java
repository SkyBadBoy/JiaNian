package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.QuestionRecordMapper;
import wtb.core.model.QuestionRecord;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class QuestionRecordService implements QuestionRecordMapper { // 在线表
    @Autowired
    private QuestionRecordMapper mapper;

    //  增
    @Transactional
    public int addQuestionRecord(QuestionRecord QuestionRecord) {
        return mapper.addQuestionRecord(QuestionRecord);
    }

    //  删
    @Transactional
    public int removeQuestionRecord(QuestionRecord QuestionRecord) {
        return mapper.removeQuestionRecord(QuestionRecord);
    }

    //  改
    @Transactional
    public int changeQuestionRecord(QuestionRecord QuestionRecord) {
        return mapper.changeQuestionRecord(QuestionRecord);
    }

   
}