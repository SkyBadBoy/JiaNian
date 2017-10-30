package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.GameQuestion;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface GameQuestionMapper { // 订单评价
    //  增
    public int addGameQuestion(GameQuestion GameQuestion);

    //  删
    public int removeGameQuestion(GameQuestion GameQuestion);

    //  改
    public int changeGameQuestion(GameQuestion GameQuestion);

    
    public int changeGameQuestionRightCount(GameQuestion GameQuestion);
}
