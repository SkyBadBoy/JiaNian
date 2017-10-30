package read.core.data;
import java.util.*;

import wtb.core.model.Pictures;

/**
 * wtb_Picture
 */
public interface ReadPictureMapper {
	/**
	 * 查询全部
	 */
	 public List<Pictures> getPictureList(Map<String, Object> params);
	 public List<Pictures> getPictureEffList(Map<String, Object> params);

}