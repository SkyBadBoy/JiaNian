package read.core.data;
import java.util.*;

import wtb.core.model.Version;

/**
 * wtb_WeChatPublic
 */
public interface ReadVersionMapper {

	 

	 public List<Version> getVersionList(Map<String,Object> params );

	 public int getVersionCount(Map<String,Object> params );
	 
	 public List<Version> getVersionByIDList(Map<String,Object> params );
	 
	 public int getVersionMaxNumber(Map<String,Object> params );
	 
}