package wtb.core.data;

import wtb.core.model.KeyWord;

import java.util.List;
import java.util.Map;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface KeyWordMapper { // 关键词
    //  增
    public int addKeyWord(KeyWord keyWord);

    //  删
    public int removeKeyWord(KeyWord keyWord);

    //  改
    public int changeKeyWord(KeyWord keyWord);

    //  查
    public int findKeyWord(KeyWord keyWord);

    //  搜
    public KeyWord searchKeyWord(KeyWord keyWord);

    //  找
    public List<KeyWord> queryKeyWord(Map<String, Object> param);

    public int addKeyWordCount(long param);

    public int queryKeyWordCount(Map<String, Object> param);

	 /**
	 * 删除记录
	 */
	 public int deleteKeyWord(Map<String,Object> params);
}
