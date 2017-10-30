package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadRedPacketMapper;
import wtb.core.json.ReturnRedPacketList;
import wtb.core.model.RedPacket;

@Service
public class ReadRedPacketService {
	
	 @Autowired
	private ReadRedPacketMapper mapper;
	
	@Transactional
	public List<ReturnRedPacketList> QueryList(Map<String, Object> map) {
		return mapper.QueryList(map);
	}
	
	@Transactional
	public RedPacket QueryOne(Map<String, Object> map) {
		return mapper.QueryOne(map);
	}
	
}