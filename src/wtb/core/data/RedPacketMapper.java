package wtb.core.data;

import java.util.Map;

import wtb.core.model.RedPacket;

public interface RedPacketMapper {
    int deleteByPrimaryKey(Long redpacketId);

    int insert(RedPacket record);

    int insertSelective(RedPacket record);


    int updateByPrimaryKeySelective(RedPacket record);

    int updateByPrimaryKey(RedPacket record);
    
    /* by Sheryl */
    int AddRedPacketMessage(RedPacket record);
    
    int ChangeBelongUser(Map<String, Object> map);
    
    int ChangeStatus(long rpID);
    
}