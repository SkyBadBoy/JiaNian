package read.core.data;


import java.util.List;
import java.util.Map;

import wtb.core.model.QuestionLimit;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface ReadQuestionLimitMapper { // 订单评价
	  //  查
    public int findQuestionLimit(Map<String, Object> param);

    //  搜
    public QuestionLimit searchQuestionLimit(QuestionLimit QuestionLimit);

    //  找
    public List<QuestionLimit> queryQuestionLimit(Map<String, Object> param);

    
}
