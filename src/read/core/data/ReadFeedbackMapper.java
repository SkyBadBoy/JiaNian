package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.model.Feedback;

public interface ReadFeedbackMapper {
	/**
	 * 查询全部
	 */
	 public List<Feedback> getFeedbackList(Map<String, Object> params);
	 public int getFeedbackCount(Map<String,Object> params);

	 

}
