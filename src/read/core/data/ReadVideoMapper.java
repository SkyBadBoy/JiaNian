package read.core.data;
import java.util.*;

import wtb.core.model.Video;
import wtb.core.model.WeChatPublic;

/**
 * wtb_WeChatPublic
 */
public interface ReadVideoMapper {
	/**
	 * 查询全部
	 */
	 public List<Video> getVideoList(Map<String, Object> params);
	

	 public int getVideoCount(Map<String,Object> params);
	 
	 
	 public List<Video> getVideoSchoolRankingList(Map<String,Object> params);
	 
	 public List<Video> getVideoRankingList(Map<String,Object> params);
	 public List<Video> getVideoReadRankingList(Map<String,Object> params);
	 
}