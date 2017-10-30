package read.core.data;

import wtb.core.model.KeyWord;

import java.util.List;
import java.util.Map;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface ReadKeyWordMapper { // 关键词
   
    //  查
    public int findKeyWord(KeyWord keyWord);

    //  搜
    public KeyWord searchKeyWord(KeyWord keyWord);

    //  找
    public List<KeyWord> queryKeyWord(Map<String, Object> param);

    public int queryKeyWordCount(Map<String, Object> param);


}
