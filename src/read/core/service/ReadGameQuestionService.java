package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadGameQuestionMapper;
import wtb.core.data.GameQuestionMapper;
import wtb.core.model.GameQuestion;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class ReadGameQuestionService implements ReadGameQuestionMapper { // 在线表
    @Autowired
    private ReadGameQuestionMapper mapper;

   

    //  查
    @Transactional(readOnly = true)
    public int findGameQuestion(Map<String, Object> param) {
        return mapper.findGameQuestion(param);
    }

    //  搜
    @Transactional(readOnly = true)
    public GameQuestion searchGameQuestion(GameQuestion GameQuestion) {
        return mapper.searchGameQuestion(GameQuestion);
    }

    //  找
    @Transactional(readOnly = true)
    public List<GameQuestion> queryGameQuestion(Map<String, Object> param) {
        return mapper.queryGameQuestion(param);
    }

}