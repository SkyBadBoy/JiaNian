package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.QuestionRecord;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface ReadQuestionRecordMapper { // 订单评价
   

    //  查
    public int findQuestionRecord(QuestionRecord QuestionRecord);

    //  搜
    public QuestionRecord searchQuestionRecord(QuestionRecord QuestionRecord);

    //  找
    public List<QuestionRecord> queryQuestionRecord(Map<String, Object> param);
    
}
