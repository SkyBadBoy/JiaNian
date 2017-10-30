package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.QuestionStat;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface QuestionStatMapper { // 订单评价
    //  增
    public int addQuestionStat(QuestionStat QuestionStat);

    //  删
    public int removeQuestionStat(QuestionStat QuestionStat);

    //  改
    public int changeQuestionStat(QuestionStat QuestionStat);

    
    public int changeQuestionStatRightCount(QuestionStat QuestionStat);
}
