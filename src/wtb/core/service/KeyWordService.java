package wtb.core.service;

import wtb.core.data.KeyWordMapper;
import wtb.core.model.KeyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class KeyWordService implements KeyWordMapper { // 关键词
    @Autowired
    private KeyWordMapper mapper;

    //  增
    @Transactional
    public int addKeyWord(KeyWord keyWord) {
        return mapper.addKeyWord(keyWord);
    }

    //  删
    @Transactional
    public int removeKeyWord(KeyWord keyWord) {
        return mapper.removeKeyWord(keyWord);
    }

    //  改
    @Transactional
    public int changeKeyWord(KeyWord keyWord) {
        return mapper.changeKeyWord(keyWord);
    }

    //  查
    @Transactional(readOnly = true)
    public int findKeyWord(KeyWord keyWord) {
        return mapper.findKeyWord(keyWord);
    }

    //  搜
    @Transactional(readOnly = true)
    public KeyWord searchKeyWord(KeyWord keyWord) {
        return mapper.searchKeyWord(keyWord);
    }

    //  找
    @Transactional(readOnly = true)
    public List<KeyWord> queryKeyWord(Map<String, Object> param) {
        return mapper.queryKeyWord(param);
    }

    @Transactional(readOnly = true)
    public int addKeyWordCount(long param) {
        return mapper.addKeyWordCount(param);
    }

    @Transactional(readOnly = true)
    public int queryKeyWordCount(Map<String, Object> param) {
        return mapper.queryKeyWordCount(param);
    }

	@Override
	public int deleteKeyWord(Map<String, Object> params) {
		return mapper.deleteKeyWord(params);
	}

}