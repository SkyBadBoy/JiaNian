package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.GameQuestion;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface ReadGameQuestionMapper { // 订单评价
   

    //  查
    public int findGameQuestion(Map<String, Object> param);

    //  搜
    public GameQuestion searchGameQuestion(GameQuestion GameQuestion);

    //  找
    public List<GameQuestion> queryGameQuestion(Map<String, Object> param);
    
}
