package read.core.data;
import java.util.*;

import wtb.core.model.NoticesContent;



public interface ReadNoticesContentMapper {
	/**
	 * 查询全部
	 */

	 public List<NoticesContent> getReadContentList(Map<String, Object> params);
	 public NoticesContent getContentByID(long ContentID);
	 
	 
}