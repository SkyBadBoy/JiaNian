package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadFeedbackMapper;

import wtb.core.data.FeedbackMapper;
import wtb.core.model.Feedback;

@Service
public class ReadFeedbackService {
	@Autowired
	private ReadFeedbackMapper mapper;
	/**
	 * 查询全部
	 */
	@Transactional(readOnly = true)
	 public List<Feedback> getFeedbackList(Map<String, Object> params){
		 return mapper.getFeedbackList(params);
		 
	 }
	@Transactional
	 public int getFeedbackCount(Map<String,Object> params){
		 return mapper.getFeedbackCount(params);
	 }


}
