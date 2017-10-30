package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.OnLineMapper;
import wtb.core.model.OnLine;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
@SuppressWarnings("unchecked")
public class OnLineService implements OnLineMapper { // 在线表
    @Autowired
    private OnLineMapper mapper;

    //  增
    @Transactional
    public int addOnLine(OnLine onLine) {
        return mapper.addOnLine(onLine);
    }

    //  删
    @Transactional
    public int removeOnLine(OnLine onLine) {
        return mapper.removeOnLine(onLine);
    }

    //  改
    @Transactional
    public int changeOnLine(OnLine onLine) {
        return mapper.changeOnLine(onLine);
    }

    //  查
    @Transactional(readOnly = true)
    public int findOnLine(Map<String, Object> onLine) {
        return mapper.findOnLine(onLine);
    }

    //  搜
    @Transactional(readOnly = true)
    public OnLine searchOnLine(OnLine onLine) {
        return mapper.searchOnLine(onLine);
    }

    //  找
    @Transactional(readOnly = true)
    public List<OnLine> queryOnLine(Map<String, Object> param) {
        return mapper.queryOnLine(param);
    }
}