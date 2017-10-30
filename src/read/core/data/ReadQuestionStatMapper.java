package read.core.data;
import java.util.*;

import wtb.core.model.QuestionStat;

/**
 * wtb_WeChatPublic
 */
public interface ReadQuestionStatMapper {
	/**
	 * 查询全部
	 */
	 public List<QuestionStat> queryQuestionStat(Map<String, Object> params);
	 public long findQuestionStat(Map<String, Object> params);
	 

	 public QuestionStat searchQuestionStat(long params);
	 public List<QuestionStat> queryQuestionStatRank(Map<String, Object> params);
	 public List<QuestionStat> queryQuestionStatRankBySelf(Map<String, Object> params);
	 
	 
}