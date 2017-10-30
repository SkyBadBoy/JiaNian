package wtb.core.service;

import wtb.core.data.RedPacketMapper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.model.RedPacket;
//import java.sql.SQLException;
//@SuppressWarnings("unchecked")


 @Service
public class RedPacketService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private RedPacketMapper mapper;

	@Transactional
	public int AddRedPacketMessage(RedPacket record) {
		return mapper.AddRedPacketMessage(record);
	}
	
	@Transactional
	public int ChangeBelongUser(Map<String, Object> map) {
		return mapper.ChangeBelongUser(map);
	}
	
	@Transactional
	public int ChangeStatus(long rpID) {
		return mapper.ChangeStatus(rpID);
	}
	
	
	
}