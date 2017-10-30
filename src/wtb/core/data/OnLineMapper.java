package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.OnLine;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface OnLineMapper { // 订单评价
    //  增
    public int addOnLine(OnLine onLine);

    //  删
    public int removeOnLine(OnLine onLine);

    //  改
    public int changeOnLine(OnLine onLine);

    //  查
    public int findOnLine(Map<String, Object> onLine);

    //  搜
    public OnLine searchOnLine(OnLine onLine);

    //  找
    public List<OnLine> queryOnLine(Map<String, Object> param);
}
