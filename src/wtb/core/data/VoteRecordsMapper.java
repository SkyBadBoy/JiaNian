package wtb.core.data;
import java.util.*;

import wtb.core.model.VoteRecords;

/**
 * wtb_WeChatPublic
 */
public interface VoteRecordsMapper {
	/**
	 * 查询全部
	 */
	 public List<VoteRecords> getVoteRecordsList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int getVoteRecordsCount(Map<String, Object> params);
	 /**
	 * 删除记录
	 */
	 public  List<VoteRecords>  getVoteRecordsByIDList(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addVoteRecords(VoteRecords VoteRecord);
	 /**
	 * 恢复记录
	 */
	 public int updateVoteRecords(VoteRecords VoteRecord);

	 public int deleteVoteRecords(Map<String,Object> params);
	 
	 public int enabledVoteRecords(Map<String,Object> params);

}