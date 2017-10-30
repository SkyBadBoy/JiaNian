package read.core.data;

import java.util.List;
import java.util.Map;

import wtb.core.json.ReturnRedPacketList;
import wtb.core.model.RedPacket;

public interface ReadRedPacketMapper {
	
	public RedPacket selectByPrimaryKey(Long redpacketId);

	
	/* by Sheryl */
	public List<ReturnRedPacketList> QueryList(Map<String, Object> map);
    
	public RedPacket QueryOne(Map<String, Object> map);

}