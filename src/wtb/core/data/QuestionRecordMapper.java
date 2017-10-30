package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.QuestionRecord;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface QuestionRecordMapper { // 订单评价
    //  增
    public int addQuestionRecord(QuestionRecord QuestionRecord);

    //  删
    public int removeQuestionRecord(QuestionRecord QuestionRecord);

    //  改
    public int changeQuestionRecord(QuestionRecord QuestionRecord);

    
}
