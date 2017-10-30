package wtb.core.service;
import java.util.*;

import wtb.core.data.MessagesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Messages;

/**
 * wtb_vitaes
 */
 @Service
public class MessagesService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private MessagesMapper mapper;

	@Transactional(readOnly = true)
	public List<Messages> getMessagesList(Map<String,Object> params) {
		return mapper.getMessagesList(params);
	}
	
	@Transactional
	public int addMessages(Messages obj) {
		return mapper.addMessages(obj);
	}

	@Transactional
	public int updateMessages(Messages obj) {
		return mapper.updateMessages(obj);
	}

	@Transactional
	public int ReadedMessages(Map<String,Object> params) {
		return mapper.ReadedMessages(params);
	}
	
	@Transactional
	public int enabledMessages(Map<String,Object> params) {
		return mapper.enabledMessages(params);
	}
	@Transactional
	public int getMessagesCount(Map<String,Object> params){
		return mapper.getMessagesCount(params);
	}
	@Transactional
	public Messages getMessagesByID(long id){
		return mapper.getMessagesByID(id);
	}
	
}