package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.GameQuestionMapper;
import wtb.core.model.GameQuestion;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class GameQuestionService implements GameQuestionMapper { // 在线表
    @Autowired
    private GameQuestionMapper mapper;

    //  增
    @Transactional
    public int addGameQuestion(GameQuestion GameQuestion) {
        return mapper.addGameQuestion(GameQuestion);
    }

    //  删
    @Transactional
    public int removeGameQuestion(GameQuestion GameQuestion) {
        return mapper.removeGameQuestion(GameQuestion);
    }

    //  改
    @Transactional
    public int changeGameQuestion(GameQuestion GameQuestion) {
        return mapper.changeGameQuestion(GameQuestion);
    }

   

	@Override
	public int changeGameQuestionRightCount(GameQuestion GameQuestion) {
		return mapper.changeGameQuestionRightCount(GameQuestion);
	}
}