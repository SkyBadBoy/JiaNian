package wtb.core.data;


import wtb.core.model.QuestionLimit;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface QuestionLimitMapper { // 订单评价
    //  增
    public int addQuestionLimit(QuestionLimit QuestionLimit);

    //  删
    public int removeQuestionLimit(QuestionLimit QuestionLimit);

    //  改
    public int changeQuestionLimit(QuestionLimit QuestionLimit);

    
}
