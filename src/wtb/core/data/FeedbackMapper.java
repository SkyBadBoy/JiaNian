package wtb.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.Feedback;

public interface FeedbackMapper {

	 /**
	 * 删除记录
	 */
	 public int deleteFeedback(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addFeedback(Feedback Feedback);
	 

}
