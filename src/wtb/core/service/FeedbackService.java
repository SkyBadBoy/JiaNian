package wtb.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.FeedbackMapper;
import wtb.core.model.Feedback;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackMapper mapper;

	 /**
	 * 删除记录
	 */
	@Transactional
	 public int deleteFeedback(Map<String,Object> params){
		 return mapper.deleteFeedback(params);
	 }
	 /**
	 * 添加记录
	 */
	@Transactional
	 public int addFeedback(Feedback Feedback){
		 return mapper.addFeedback(Feedback);
	 }

}
