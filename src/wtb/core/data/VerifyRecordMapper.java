package wtb.core.data;

import wtb.core.model.VerifyRecord;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface VerifyRecordMapper { // 登录验证
    //  增
    public int addVerifyRecord(VerifyRecord verifyRecord);

    //  删
    public int removeVerifyRecord(VerifyRecord verifyRecord);

    //  改
    public int changeVerifyRecord(VerifyRecord verifyRecord);

    //  查
    public int findVerifyRecord(VerifyRecord verifyRecord);

    //  搜
    public VerifyRecord searchVerifyRecord(VerifyRecord verifyRecord);

    //  找
    public List<VerifyRecord> queryVerifyRecord(Map<String, Object> param);

    //  注销账户

    /***
     * 注销验证码记录
     * @param verifyRecord
     * @return 处理结果
     */
    public int closeVerifyRecord(VerifyRecord verifyRecord);
}
