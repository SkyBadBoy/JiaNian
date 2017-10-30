package read.core.service;

import wtb.core.data.KeyWordMapper;
import wtb.core.model.KeyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadKeyWordMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class ReadKeyWordService implements ReadKeyWordMapper { // 关键词
    @Autowired
    private ReadKeyWordMapper mapper;


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
    public int queryKeyWordCount(Map<String, Object> param) {
        return mapper.queryKeyWordCount(param);
    }

}