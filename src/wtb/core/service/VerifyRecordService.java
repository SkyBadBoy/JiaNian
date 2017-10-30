package wtb.core.service;

import wtb.core.data.VerifyRecordMapper;
import wtb.core.model.VerifyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service
public class VerifyRecordService implements VerifyRecordMapper { // 登录验证
    @Autowired
    private VerifyRecordMapper mapper;

    //  增
    @Transactional
    public int addVerifyRecord(VerifyRecord verifyRecord) {
        return mapper.addVerifyRecord(verifyRecord);
    }

    //  删
    @Transactional
    public int removeVerifyRecord(VerifyRecord verifyRecord) {
        return mapper.removeVerifyRecord(verifyRecord);
    }

    //  改
    @Transactional
    public int changeVerifyRecord(VerifyRecord verifyRecord) {
        return mapper.changeVerifyRecord(verifyRecord);
    }

    //  查
    @Transactional(readOnly = true)
    public int findVerifyRecord(VerifyRecord verifyRecord) {
        return mapper.findVerifyRecord(verifyRecord);
    }

    //  搜
    @Transactional(readOnly = true)
    public VerifyRecord searchVerifyRecord(VerifyRecord verifyRecord) {
        return mapper.searchVerifyRecord(verifyRecord);
    }

    //  找
    @Transactional(readOnly = true)
    public List<VerifyRecord> queryVerifyRecord(Map<String, Object> param) {
        return mapper.queryVerifyRecord(param);
    }

    @Transactional
    public int closeVerifyRecord(VerifyRecord verifyRecord) {
        return mapper.closeVerifyRecord(verifyRecord);
    }
}